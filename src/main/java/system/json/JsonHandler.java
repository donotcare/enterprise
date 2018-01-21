package system.json;

import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedWriter;
import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonHandler {

    public String xmlFileToJsonString(File file) {
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(new String(Files.readAllBytes(Paths.get(file.toURI()))));
            return xmlJSONObj.toString(4);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveJsonToFile(String json, URI file) {
        Path path = Paths.get(file);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
