# 指定路徑 JSON 解析器

<br>

--------------------------------

<br>

情境假設；寫一個 func，參數1 是任意的 json 資料 (String)，參數 2 是目標資料於 json 中的路徑。需要回傳目標資料，舉例:

參數 1:

```json
{
  "data": "{
    'date': '2023-11-11',
     'user_list': [
        {'name': 'Johnny', 'age': 24, 'skills': ['java', 'python']},
        {'name': 'Jerry', 'age': 25, 'skills': ['js', 'asp.net']},
        {'name': 'Monica', 'age': 26, 'skills': ['SQL']},
        {'name': 'Ray', 'age': 27, 'skills': ['無敵1', '無敵2']}]}"
}
```

<br>

參數 2:

```
"data/user_list/3/skills/1"
```

<br>

回傳結果

```
"無敵2"
```

<br>
<br>
<br>
<br>

實作:

<br>

依賴:

```xml
<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20160810</version>
</dependency>
```

<br>

code:

```java
import org.json.*;

import java.util.Arrays;


public class JsonParser {


    public static Object getValue(String json, String path) throws JSONException {
        Object obj = new JSONTokener(json).nextValue();
        return getValue(obj, path.split("/"));
    }

    private static Object getValue(Object obj, String[] path) throws JSONException {
        if (obj instanceof String) {
            String jsonStr = (String) obj;
            obj = new JSONTokener(jsonStr).nextValue();
        }

        if (obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            int index = Integer.parseInt(path[0]);
            if (path.length == 1) {
                return arr.get(index);
            } else {
                return getValue(arr.get(index), Arrays.copyOfRange(path, 1, path.length));
            }
        } else if (obj instanceof JSONObject) {
            JSONObject jsonObj = (JSONObject) obj;
            if (path.length == 1) {
                return jsonObj.get(path[0]);
            } else {
                return getValue(jsonObj.get(path[0]), Arrays.copyOfRange(path, 1, path.length));
            }
        } else {
            throw new JSONException(String.format("Invalid JSON object, obj:%s, key:%s", obj, path[0]));
        }
    }

    public static void main(String[] args) {
        String jsonStr = "{\n" +
                "  \"data\": \"{'date': '2023-11-11', 'user_list': [{'name': 'Johnny', 'age': 24, 'skills': ['java', 'python']}, {'name': 'Jerry', 'age': 24, 'skills': ['js', 'asp.net']}, {'name': 'Monica', 'age': 26, 'skills': ['SQL']}, {'name': 'Ray', 'age': 27, 'skills': ['無敵1', '無敵2']}]}]}\"\n" +
                "}";

        String name_path = "data/user_list/3/name";
        String age_path = "data/user_list/3/age";
        String skill_path = "data/user_list/3/skills/1";
        
        System.out.println("name: " + getValue(jsonStr, name_path));
        System.out.println("age: " + getValue(jsonStr, age_path));
        System.out.println("skill_1: " + getValue(jsonStr, skill_path));
    }

}
```

<br>

印出結果:

```
name: Ray
age: 27
skill_1: 無敵2
```