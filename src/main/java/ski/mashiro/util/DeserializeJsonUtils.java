package ski.mashiro.util;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author FeczIne
 */
public class DeserializeJsonUtils {
    public static String deserializeJson(HttpServletRequest req) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
        String len;
        StringBuilder sb = new StringBuilder();
        while ((len = bufferedReader.readLine()) != null) {
            sb.append(len);
        }
        return sb.toString();
    }
}
