package com.frizo.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0.0
 * @author ivenchou
 */
@Component
@Slf4j
@SuppressWarnings({"unchecked","unused","rawtypes"})
public class RedisUtils {
  /** 默認過期時長，單位：秒 */
  public static final long DEFAULT_EXPIRE = 60 * 60 * 24;
  /** 不設置過期時長 */
  public static final long NOT_EXPIRE = -1;
  /** 表示SET IF NOT EXIST */
  private static final String NX = "NX";
  /** 表示SET WITH EXPIRE_TIME */
  private static final String EX = "EX";
  /** 加鎖成功 */
  private static final String LOCK_OK = "OK";
  /** 解鎖成功 */
  private static final Long UNLOCK_OK = 1L;
  /** 解鎖的腳本 */
  private static final String UNLOCK_SCRIPT =
      "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

  /** 請求標識 */
  private static final ThreadLocal<String> LOCK_VALUE =
      new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
          return UUID.randomUUID().toString();
        }
      };

  private final RedisTemplate redisTemplate;

  
  public RedisUtils(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * @param key
   * @param value
   * @return
   */
  public boolean set(final String key, Object value) {
    boolean result = false;
    try {
      ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
      operations.set(key, value);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * @param key
   * @param value
   * @return
   */
  public boolean setEx(final String key, Object value, Long expireTime) {
    boolean result = false;
    try {
      ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
      operations.set(key, value);
      redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * @param key
   * @return
   */
  public boolean exists(final String key) {
    return redisTemplate.hasKey(key);
  }

  /**
   * @param key
   * @return
   */
  public Object get(final String key) {
    Object result = null;
    ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
    result = operations.get(key);
    return result;
  }

  /**
   * 删除value by key
   *
   * @param key
   */
  public boolean remove(final String key) {
    if (exists(key)) {
      Boolean delete = redisTemplate.delete(key);
      return delete;
    }
    return false;
  }

  //  /**
  //   * @MethodName lock @Description 加鎖
  //   *
  //   * @param key
  //   * @param expireSeconds 過期時間
  //   * @return
  //   */
  //  public boolean lock(String key, int expireSeconds) {
  //    return redisTemplate.execute(
  //        new RedisCallback<Boolean>() {
  //          @Override
  //          public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
  //            Object nativeConnection = connection.getNativeConnection();
  //            if (nativeConnection instanceof JedisCluster) {
  //              JedisCluster jedisCluster = (JedisCluster) nativeConnection;
  //              String result = jedisCluster.set(key, LOCK_VALUE.get(), NX, EX, expireSeconds);
  //              return LOCK_OK.equals(result);
  //            }
  //            if (nativeConnection instanceof RedisProperties.Jedis) {
  //              RedisProperties.Jedis jedis = (RedisProperties.Jedis) nativeConnection;
  //              String result = jedis.set(key, LOCK_VALUE.get(), NX, EX, expireSeconds);
  //              return LOCK_OK.equals(result);
  //            }
  //            return false;
  //          }
  //        });
  //  }
  //
  //  /**
  //   * @MethodName unlock @Description 解鎖
  //   *
  //   * @param key @Return boolean
  //   */
  //  public boolean unlock(String key) {
  //    return redisTemplate.execute(
  //        new RedisCallback<Boolean>() {
  //          @Override
  //          public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
  //            Object nativeConnection = connection.getNativeConnection();
  //            if (nativeConnection instanceof JedisCluster) {
  //              JedisCluster jedisCluster = (JedisCluster) nativeConnection;
  //              Object unlock =
  //                  jedisCluster.eval(
  //                      UNLOCK_SCRIPT,
  //                      Collections.singletonList(key),
  //                      Collections.singletonList(LOCK_VALUE.get()));
  //              return UNLOCK_OK.equals(unlock);
  //            }
  //            if (nativeConnection instanceof Jedis) {
  //              Jedis jedis = (Jedis) nativeConnection;
  //              Object unlock =
  //                  jedis.eval(
  //                      UNLOCK_SCRIPT,
  //                      Collections.singletonList(key),
  //                      Collections.singletonList(LOCK_VALUE.get()));
  //              return UNLOCK_OK.equals(unlock);
  //            }
  //            return false;
  //          }
  //        });
  //  }

  /**
   * 刪除key
   *
   * @param key
   */
  public void delete(String key) {
    redisTemplate.delete(key);
  }

  /**
   * 指定緩存失效時間
   *
   * @param key 鍵
   * @param time 時間(秒)
   * @return
   */
  public boolean expire(String key, long time) {
    try {
      if (time > 0) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 根據key獲取過期時間
   *
   * @param key 鍵不能為null
   * @return 時間(秒) 返回0代表為永久有效
   */
  public long getExpire(String key) {
    return redisTemplate.getExpire(key, TimeUnit.SECONDS);
  }

  /**
   * 判斷key是否存在
   *
   * @param key 鍵
   * @return true 存在false不存在
   */
  public boolean hasKey(String key) {
    try {
      return redisTemplate.hasKey(key);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 刪除緩存
   *
   * @param key 可以傳一個值或多個
   */
  public void del(String... key) {
    if (key != null && key.length > 0) {
      if (key.length == 1) {
        redisTemplate.delete(key[0]);
      } else {
        redisTemplate.delete(CollectionUtils.arrayToList(key));
      }
    }
  }

  /**
   * 添加緩存並設置過期時間
   *
   * @param key 鍵
   * @param value 值
   * @param time 時間(秒) time要大於0 如果time小於等於0 將設置無限期
   * @return true成功false 失敗
   */
  public boolean set(String key, Object value, long time) {
    try {
      if (time > 0) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
      } else {
        set(key, value);
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 遞增
   *
   * @param key 鍵
   * @return
   */
  public long incr(String key, long delta) {
    if (delta < 0) {
      throw new RuntimeException("遞增因子必須大於0");
    }
    return redisTemplate.opsForValue().increment(key, delta);
  }

  /**
   * 添加一個遞增值並設置過期時間
   *
   * @param key 鍵
   * @param delta 遞增值
   * @param secondTime 時間(秒)
   * @return true成功false失敗
   */
  public long incrSecond(String key, long delta, long secondTime) {
    if (delta < 0) {
      throw new RuntimeException("遞增因子必須大於0");
    }

    long result;
    if (redisTemplate.hasKey(key)) {
      result = redisTemplate.opsForValue().increment(key, delta);
    } else {
      result = redisTemplate.opsForValue().increment(key, delta);
      redisTemplate.expire(key, secondTime, TimeUnit.SECONDS);
    }
    return result;
  }

  /**
   * 遞減
   *
   * @param key 鍵
   * @return
   */
  public long decr(String key, long delta) {
    if (delta < 0) {
      throw new RuntimeException("遞減因子必須大於0");
    }
    return redisTemplate.opsForValue().increment(key, -delta);
  }

  /**
   * 設置一組Map的鍵值對
   *
   * @param key 鍵不能為null
   * @param item 項不能為null
   * @return 值
   */
  public Object hGet(String key, String item) {
    return redisTemplate.opsForHash().get(key, item);
  }

  /**
   * 獲取hashKey對應的所有鍵值
   *
   * @param key 鍵
   * @return 對應的多個鍵值
   */
  public Map<Object, Object> hmGet(String key) {
    return redisTemplate.opsForHash().entries(key);
  }

  /**
   * 添加一個Map類型值
   *
   * @param key 鍵
   * @param map 對應多個鍵值
   * @return true 成功false 失敗
   */
  public boolean hmSet(String key, Map<String, Object> map) {
    try {
      redisTemplate.opsForHash().putAll(key, map);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 添加一個Map類型值並設置過期時間
   *
   * @param key 鍵
   * @param map 對應多個鍵值
   * @param time 時間(秒)
   * @return true成功false失敗
   */
  public boolean hmSet(String key, Map<String, Object> map, long time) {
    try {
      redisTemplate.opsForHash().putAll(key, map);
      if (time > 0) {
        expire(key, time);
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 向一張hash表中放入數據,如果不存在將創建
   *
   * @param key 鍵
   * @param item 項
   * @param value 值
   * @return true 成功false失敗
   */
  public boolean hSet(String key, String item, Object value) {
    try {
      redisTemplate.opsForHash().put(key, item, value);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 向一張hash表中放入數據,如果不存在將創建
   *
   * @param key 鍵
   * @param item 項
   * @param value 值
   * @param time 時間(秒) 注意:如果已存在的hash表有時間,這裡將會替換原有的時間
   * @return true 成功false失敗
   */
  public boolean hSet(String key, String item, Object value, long time) {
    try {
      redisTemplate.opsForHash().put(key, item, value);
      if (time > 0) {
        expire(key, time);
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 刪除hash表中的值
   *
   * @param key 鍵不能為null
   * @param item 項可以使多個不能為null
   */
  public void hDel(String key, Object... item) {
    redisTemplate.opsForHash().delete(key, item);
  }

  /**
   * 判斷hash表中是否有該項的值
   *
   * @param key 鍵不能為null
   * @param item 項不能為null
   * @return true 存在false不存在
   */
  public boolean hHasKey(String key, String item) {
    return redisTemplate.opsForHash().hasKey(key, item);
  }

  /**
   * hash遞增如果不存在,就會創建一個並把新增後的值返回
   *
   * @param key 鍵
   * @param item 項
   * @param by 要增加幾(大於0)
   * @return
   */
  public double hIncr(String key, String item, double by) {
    return redisTemplate.opsForHash().increment(key, item, by);
  }

  /**
   * hash遞減
   *
   * @param key 鍵
   * @param item 項
   * @param by 要減少記(小於0)
   * @return
   */
  public double hDecr(String key, String item, double by) {
    return redisTemplate.opsForHash().increment(key, item, -by);
  }

  /**
   * 根據key獲取Set中的所有值
   *
   * @param key 鍵
   * @return
   */
  public Set<Object> sGet(String key) {
    try {
      return redisTemplate.opsForSet().members(key);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 根據value從一個set中查詢,是否存在
   *
   * @param key 鍵
   * @param value 值
   * @return true 存在false不存在
   */
  public boolean sHasKey(String key, Object value) {
    try {
      return redisTemplate.opsForSet().isMember(key, value);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 將數據放入set緩存
   *
   * @param key 鍵
   * @param values 值可以是多個
   * @return 成功個數
   */
  public long sSet(String key, Object... values) {
    try {
      return redisTemplate.opsForSet().add(key, values);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  /**
   * 將set數據放入緩存
   *
   * @param key 鍵
   * @param time 時間(秒)
   * @param values 值可以是多個
   * @return 成功個數
   */
  public long sSetAndTime(String key, long time, Object... values) {
    try {
      Long count = redisTemplate.opsForSet().add(key, values);
      if (time > 0) expire(key, time);
      return count;
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  /**
   * 獲取set緩存的長度
   *
   * @param key 鍵
   * @return
   */
  public long sGetSetSize(String key) {
    try {
      return redisTemplate.opsForSet().size(key);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  /**
   * 移除值為value的
   *
   * @param key 鍵
   * @param values 值可以是多個
   * @return 移除的個數
   */
  public long setRemove(String key, Object... values) {
    try {
      Long count = redisTemplate.opsForSet().remove(key, values);
      return count;
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  /**
   * 獲取list緩存的內容
   *
   * @param key 鍵
   * @param start 開始
   * @param end 結束0 到-1代表所有值
   * @return
   */
  public List<Object> lGet(String key, long start, long end) {
    try {
      return redisTemplate.opsForList().range(key, start, end);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 獲取list緩存的長度
   *
   * @param key 鍵
   * @return
   */
  public long lGetListSize(String key) {
    try {
      return redisTemplate.opsForList().size(key);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  /**
   * 通過索引獲取list中的值
   *
   * @param key 鍵
   * @param index 索引index>=0時， 0 表頭，1 第二個元素，依次類推；index<0時，-1，表尾，-2倒數第二個元素，依次類推
   * @return
   */
  public Object lGetIndex(String key, long index) {
    try {
      return redisTemplate.opsForList().index(key, index);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 將list放入緩存
   *
   * @param key 鍵
   * @param value 值
   * @return
   */
  public boolean lSet(String key, Object value) {
    try {
      redisTemplate.opsForList().rightPush(key, value);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 將list放入緩存
   *
   * @param key 鍵
   * @param value 值
   * @param time 時間(秒)
   * @return
   */
  public boolean lSet(String key, Object value, long time) {
    try {
      redisTemplate.opsForList().rightPush(key, value);
      if (time > 0) expire(key, time);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 將list放入緩存
   *
   * @param key 鍵
   * @param value 值
   * @return
   */
  public boolean lSet(String key, List<Object> value) {
    try {
      redisTemplate.opsForList().rightPushAll(key, value);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 將list放入緩存
   *
   * @param key 鍵
   * @param value 值
   * @param time 時間(秒)
   * @return
   */
  public boolean lSet(String key, List<Object> value, long time) {
    try {
      redisTemplate.opsForList().rightPushAll(key, value);
      if (time > 0) expire(key, time);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 根據索引修改list中的某條數據
   *
   * @param key 鍵
   * @param index 索引
   * @param value 值
   * @return
   */
  public boolean lUpdateIndex(String key, long index, Object value) {
    try {
      redisTemplate.opsForList().set(key, index, value);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 移除N個值為value
   *
   * @param key 鍵
   * @param count 移除多少個
   * @param value 值
   * @return 移除的個數
   */
  public long lRemove(String key, long count, Object value) {
    try {
      Long remove = redisTemplate.opsForList().remove(key, count, value);
      return remove;
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  
  /**
   * 驗證碼失敗次數,達上限則刪除
   * 
   * @param verifyRecordId redisKey
   * @param maxTimes 嘗試次數上限
   * @param remainSeconds 保留秒數
   * @return redis計數
   */
  public int isVerifiable(String verifyRecordId, Integer maxTimes, Integer remainSeconds) {
	  int time = get(verifyRecordId) == null ? 
			  0 : (int) get(verifyRecordId);
	  time++;
	  if (time < maxTimes) {
		  setEx(verifyRecordId, time, (long) remainSeconds);
		  return time;
	  } else if (time == maxTimes) {
		  remove(verifyRecordId);
		  return maxTimes;
	  } else {
		  remove(verifyRecordId);
		  return 0;
	  }
  }
}
