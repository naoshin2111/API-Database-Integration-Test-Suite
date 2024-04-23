package utils;

import lombok.experimental.UtilityClass;
import java.util.UUID;

@UtilityClass
public class RandomUtils {

    public static String generateRandomString() {
        return UUID.randomUUID().toString();
    }
}
