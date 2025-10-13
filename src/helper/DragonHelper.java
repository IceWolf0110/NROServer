package helper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class DragonHelper {
    public static String optionGetValue(String section, String key) {
        String result = "";

        if (section == null || key == null) {
            return result;
        }

        var parser = new JSONParser();

        try (FileReader reader = new FileReader("options.json")) {

            var obj = parser.parse(reader);
            var converted = (JSONObject) obj;

            var convertedJson = (JSONObject) converted.get(section);
            result = (String) convertedJson.get(key);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
