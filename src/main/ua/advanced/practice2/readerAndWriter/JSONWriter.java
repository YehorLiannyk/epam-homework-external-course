package main.ua.advanced.practice2.readerAndWriter;

import main.ua.advanced.practice2.Container;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

import static main.ua.advanced.practice2.readerAndWriter.Defaults.*;

public class JSONWriter {
    public static void writeCitiesToFile(Container container, String filename) throws IOException {
        Object[] objects = container.reformatInObjectArray();
        File file = new File(PATH_DIR + filename + JSON_TYPE);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, objects);
    }
}
