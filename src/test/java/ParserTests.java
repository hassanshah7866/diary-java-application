import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserTests {

    @Test
    public void simpleJsonStringEntryMapsIntoClass () {

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"date\":\"2017-06-02\",\"body\":\"Idag ska jag tvätta\"}";
        Entry entry =  new Entry("2017-06-02", "Idag ska jag tvätta");

        try{
            Entry entryFromString = mapper.readValue(jsonString, Entry.class);
            Assert.assertEquals(entry,entryFromString);
        }catch(IOException e){}
    }

    @Test
    public void simpleJsonStringArrayEntryMapsIntoArray () {

        ObjectMapper mapper = new ObjectMapper();
        String jsonArrayString = "[{\"date\":\"2017-06-02\",\"body\":\"Idag ska jag tvätta\"},\n" +
                "{\"date\":\"2017-06-03\",\"body\":\"Idag ska jag stryka\"}]";

        Entry[] entries = new Entry[2];
        entries[0] = new Entry("2017-06-02", "Idag ska jag tvätta");
        entries[1] = new Entry("2017-06-03","Idag ska jag stryka");
        try {
            Entry[] mapperEntries = mapper.readValue(jsonArrayString, Entry[].class);
            Assert.assertArrayEquals(entries, mapperEntries);
        }catch(IOException e){}
    }

    @Test
    public void jsonStringArrayListEntryMapsIntoArrayList () {

        ObjectMapper mapper = new ObjectMapper();
        String jsonArrayString = "[{\"date\":\"2017-06-02\",\"body\":\"Idag ska jag tvätta\"},\n" +
                "{\"date\":\"2017-06-03\",\"body\":\"Idag ska jag stryka\"}]";

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry("2017-06-02", "Idag ska jag tvätta"));
        entries.add(new Entry("2017-06-03","Idag ska jag stryka"));

        try {

            List<Entry> mapperEntries = mapper.readValue(jsonArrayString, Entry.typeReference);
            Assert.assertArrayEquals(entries.toArray(), mapperEntries.toArray());
        }catch(IOException e){}
    }
}
