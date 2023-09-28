package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

//import com.fasterxml.jackson.core.JsonToken;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
//import org.yaml.snakeyaml.Yaml;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

//import java.io.File;
//import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class Parser {

/*
    public static Map<String, String> readMapFromYmlFile(String filePath) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        Map<String, Object> value;
        value = mapper.readValue(filePath, new TypeReference<>() { });
        //Map<String, String> map2 = new HashMap<>(value);
        System.out.println(value);
        return null;
    }
 */
/*
    public static Map<String, String> readMapFromYmlFile(String filePath) throws IOException, ParseException {
        //System.out.println("Reading YAML file: " + filePath);
        File file = new File(filePath);
        YAMLFactory yamlFactory = new YAMLFactory();
        YAMLParser yamlParser = yamlFactory.createParser(file);
        JsonToken jsonToken = yamlParser.nextToken();
        Map<String, String> yamlMap = new HashMap<>();
        String fieldName = "";
        while (jsonToken != null) {
            switch (jsonToken) {
                case FIELD_NAME: fieldName = yamlParser.getText();
                    //System.out.println("Key field: " + yamlParser.getText());
                    break;
                case START_OBJECT: //System.out.println("Object Started");
                    break;
                case END_OBJECT: //System.out.println("Object Ended");
                    break;
                case START_ARRAY: //System.out.println("Array Started");
                    break;
                case END_ARRAY: //System.out.println("Array Ended");
                    break;
                //case VALUE_FALSE: //case VALUE_NULL: //case VALUE_NUMBER_FLOAT:
                //case VALUE_NUMBER_INT: //case VALUE_STRING: //case VALUE_TRUE:
                default:
                    if (!fieldName.equals("")) {
                        yamlMap.put(fieldName, yamlParser.getText());
                        fieldName = "";
                    }
                    break;
            }
            jsonToken = yamlParser.nextToken();
        }
        yamlParser.close();
        return yamlMap;
    }
 */


    public static TreeMap<String, Object> readTreeMapFromFile(String filePath) throws IOException {
        TreeMap<String, Object> rezTreeMap;
        ObjectMapper mapper = null;
        if (filePath.endsWith("json")) {
            mapper = new ObjectMapper();
        } else if (filePath.endsWith("yml") || filePath.endsWith("yaml")) {
            mapper = new YAMLMapper();
        }

        String strFromFile = new String(Files.readAllBytes(Paths.get(filePath)));
        System.out.println(strFromFile);

        //rezTreeMap = mapper.readValue(filePath, new TypeReference<>() { });
        rezTreeMap = mapper.readValue(strFromFile, new TypeReference<>() { });

        ///***
        for (Map.Entry<String, Object> entry : rezTreeMap.entrySet()) {
            //System.out.println("" + entry.getKey() + " - " + entry.getValue());
            if (entry.getValue() == null) {
                entry.setValue("null");
            }
            //System.out.println("" + entry.getKey() + " - " + entry.getValue());
        }
        ///***

        return rezTreeMap;
    }

/*
    public static TreeMap<String, Object> readTreeMapFromYmlFile(String filePath) throws IOException, ParseException {

        final ObjectMapper yamlMapper = new YAMLMapper();
        final TreeMap<String, Object> yamlTreeMap;
        yamlTreeMap = yamlMapper.readValue(filePath, new TypeReference<>() { });
        return yamlTreeMap;

    }

    public static TreeMap<String, Object> readTreeMapFromJsonFile(String filePath) throws IOException, ParseException {

        final ObjectMapper jsonMapper = new ObjectMapper();
        final TreeMap<String, Object> jsonTreeMap;
        jsonTreeMap = jsonMapper.readValue(filePath, new TypeReference<>() { });
        return jsonTreeMap;

    }
*/

    /*

    public static Map<String, String> readMapFromJsonFile(String filePath) throws IOException, ParseException {
        //System.out.println("Reading JSON file: " + filePath);

        Object jObj = new JSONParser().parse(new FileReader(filePath));
        JSONObject jsonObject = (JSONObject) jObj;
        Map<String, String> jsonMap = new HashMap<>();

        for (Object key : jsonObject.keySet()) {
            String keyStr = (String) key;
            if (jsonObject.get(keyStr) != null) {
                Object keyValue = jsonObject.get(keyStr);
                //System.out.println("" + keyStr + " - " + keyValue.toString()); ///***
                //jsonMap.put(keyStr, keyValue.toString());
                jsonMap.put(keyStr, keyValue.toString());
            } else {
                jsonMap.put(keyStr, "null");
            }
        }

        return jsonMap;
    }
    */

}
