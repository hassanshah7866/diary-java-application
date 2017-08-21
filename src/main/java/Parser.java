import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Parser {

    private final File json;

    public Parser (String name) {

        json = new File(name + ".json");
    }

    public List<Entry> getAllEntries () throws IOException {

        if(json.exists()) {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, Entry.typeReference);
        }else{
            throw new IOException("File does not exist");
        }
    }

    public void add (Entry entry) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Entry> entryList = mapper.readValue(json, Entry.typeReference);
        entryList.add(entry);
        FileWriter fw = new FileWriter(json);
        fw.flush();
        BufferedWriter bw = new BufferedWriter(fw);
        mapper.writeValue(bw, entryList);
        bw.close();
    }
}