package com.fag.infra.utils;

    import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonUtils {

    public static Map<String, Object> srtToMap(String strJson) {

        Pattern pattern = Pattern.compile("\"(\\w+)\"\\s*:\\s*(\"[^\"]*\"|\\d+|true|false|null)");
        Matcher matcher = pattern.matcher(strJson);

        Map<String, Object> jsonData = new HashMap<>();

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);

            jsonData.put(key, value.replace("\"", ""));
        }

        return jsonData;
    }

    public static Map<String, Object> getChaveValor(String JSONBody) {
        Pattern pattern = Pattern.compile("\"(\\w+)\":\\s*\"([^\"]*)\"|\"(\\w+)\":\\s*(\\d+|true|false|null)");
        Matcher matcher = pattern.matcher(JSONBody);
    
        Map<String, Object> jsonData = new HashMap<>();
    
        while (matcher.find()) {
            if (matcher.group(1) != null && matcher.group(2) != null) {
                jsonData.put(matcher.group(1), matcher.group(2));
            } else if (matcher.group(3) != null && matcher.group(4) != null) {
                String key = matcher.group(3);
                String rawValue = matcher.group(4);
                Object value;
    
                if ("true".equals(rawValue) || "false".equals(rawValue)) {
                    value = Boolean.parseBoolean(rawValue);
                } else if ("null".equals(rawValue)) {
                    value = null;
                } else {
                    value = Integer.parseInt(rawValue);
                }
    
                jsonData.put(key, value);
            }
        }
    
        return jsonData;
    }

    public static String getField(String json, String field) {
        Map<String, Object> jsonMap = srtToMap(json);

        String fieldValue = jsonMap.get(field).toString();

        return fieldValue;
    }

}
    
