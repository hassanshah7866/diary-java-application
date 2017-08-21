import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.List;

public class Diary {

    public static void main (String[] args) {

        System.setProperty("log4j.configurationFile", "resources/log4j2.xml");
        Logger logger = LogManager.getLogger();
        Parser parser = new Parser("Diary");
        try {
            Action action = Action.valueOf(args[0].toUpperCase());

            switch (action) {

                case ADD:
                    String date = args[1];
                    StringBuilder sb = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {
                        sb.append(args[i]);
                        sb.append(" ");
                    }
                    String body = sb.toString().trim();
                    Entry entry = new Entry(date, body);
                    try {
                        parser.add(entry);
                        logger.info("Added entry: " + "\"" + entry.body + "\"");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        logger.error(e.getMessage());
                    }
                    break;

                case LIST:
                    try {
                        List<Entry> entries = parser.getAllEntries();
                        for (Entry thisEntry : entries) {
                            System.out.println(thisEntry.date + '\n' + thisEntry.body);
                        }
                        logger.info("Listed entries");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        logger.error(e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid parameters");
                    logger.error("Invalid parameters");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error("Invalid parameters - caused\n" + "\t" + e.toString());
        }
    }
}
