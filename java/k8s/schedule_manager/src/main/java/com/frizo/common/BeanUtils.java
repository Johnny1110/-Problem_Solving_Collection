package com.frizo.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {

  // 檢查 JavaBean 的 Field 是否恰當填值 (不為空字串或 null)
  public static boolean isBeanFilledProperly(Object object)
      throws InvocationTargetException, IllegalAccessException {
    Field[] fields = object.getClass().getDeclaredFields();
    Method[] methods = object.getClass().getDeclaredMethods();
    for (Method m : methods) {
      if (m.getName().startsWith("get") || m.getName().startsWith("is")) {
        Object value = m.invoke(object);
        if (value == null) {
          return false;
        }
        if (value instanceof String) {
          if (value.equals("")) {
            return false;
          }
        }
      }
    }
    return true;
  }

  // 暫不支援 boolean
  public static boolean isBeanFilledProperly(Object object, List<String> nonEssentialFieldNames)
      throws InvocationTargetException, IllegalAccessException {
    List<String> skipMathodNames = new ArrayList<>();
    for (String fieldName : nonEssentialFieldNames) {
      skipMathodNames.add("get" + upperCaseFirst(fieldName));
    }

    Method[] methods = object.getClass().getDeclaredMethods();
    for (Method m : methods) {
      if (m.getName().startsWith("get")) {
        if (skipMathodNames.contains(m.getName())) {
          continue;
        }
        Object value = m.invoke(object);
        if (value == null) {
          return false;
        }
        if (value instanceof String) {
          if (value.equals("")) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public static String upperCaseFirst(String val) {
    char[] arr = val.toCharArray();
    arr[0] = Character.toUpperCase(arr[0]);
    return new String(arr);
  }

  public static <T> T copyBean(T original) {
    Class<?> clazz = original.getClass();
    T cloned = null;
    try {
      cloned = (T) clazz.newInstance();
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }

    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      Object value = null;
      try {
        value = field.get(original);
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
      try {
        field.set(cloned, value);
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
    return cloned;
  }
}
