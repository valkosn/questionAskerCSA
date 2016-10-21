package utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import enteties.QA;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Valko Serhii on 02-Sep-16.
 */
public class JsonFileConverter {

    public void toJSONFile(List<QA> qaList, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filePath), qaList);
    }

    public String toJSONString(List<QA> qaList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(qaList);
    }

    @SuppressWarnings("unchecked")
    public List<QA> toJavaObjectViaJackson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MappingIterator<QA> objectMappingIterator = mapper.readerFor(QA.class).readValues(this.getClass().getClassLoader().getResourceAsStream(filePath));
        return objectMappingIterator.readAll();
    }
}
