package helpers;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Utils {

    public static JsonNode getJsonFile(String fileName) throws Exception {
        try {
            Properties prop = new Properties();
            FileReader reader = new FileReader(Constants.configProperties);
            prop.load(reader);
            prop.getProperty(fileName);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(new File(prop.getProperty(fileName)));
        }
        catch (Exception e)
        {
            throw new Exception("unable to read json file");
        }
    }

    public static Map<String,Object> getJson(String type) throws Exception {
        JsonNode jsonNode=getJsonFile("payload");
        System.out.println(jsonNode.at("/payload/"+type));
        ObjectMapper mapper = new ObjectMapper();
        String x="/payload/"+type;
        Map<String, Object> map =  mapper.readValue(jsonNode.at("/payload/"+type).toString(), Map.class);
        return map;
    }

    public static Map<String,String> getPayload()
    {
        return null;
    }

}
