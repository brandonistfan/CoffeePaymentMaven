import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomerData {
    public void saveCustomersToFile(List<Customer> customers, String filename) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            File file = new File(filename);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            mapper.writeValue(new File(filename), customers);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Customer> loadCustomersFromFile(String filename) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filename);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            return mapper.readValue(new File(filename), new TypeReference<List<Customer>>(){});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
