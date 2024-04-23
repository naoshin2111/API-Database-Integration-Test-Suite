package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonMapperUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T fromJsonFile(String filePath, Class<T> tClass) {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
            return OBJECT_MAPPER.readValue(jsonData, tClass);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
}
