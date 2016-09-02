package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import enteties.QA;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Valko Serhii on 02-Sep-16.
 */
public class JsonFileConverter {
    private final static String baseFile = "user.json";

    public static void toJSONFile(List<QA> qaList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(baseFile), qaList);
    }

    public static String toJSONString(List<QA> qaList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(qaList);
    }

    @SuppressWarnings("unchecked")
    public static List<QA> toJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(baseFile), List.class);
    }
}
