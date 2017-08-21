import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

public class Entry {

    public static final TypeReference<List<Entry>> typeReference = new TypeReference<List<Entry>>() {};
    public final String date;
    public final String body;

    @JsonCreator
    public Entry (
            @JsonProperty ("date") String date,
            @JsonProperty ("body") String body) {
        this.date = date;
        this.body = body;
    }

    public boolean equals (Object o) {

        if (o == null) {
            return false;
        }
        if (!Entry.class.isAssignableFrom(o.getClass())) {
            return false;
        }

        final Entry e = (Entry) o;

        return this.date.equals(e.date) && this.body.equals(e.body);

    }
}
