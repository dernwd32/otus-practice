package ParsingFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParseSdelki {
    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader (file));
        String         line = null;
        StringBuilder  newEntry = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        while( ( line = reader.readLine() ) != null ) {
            String[] columns = line.split("\\t");
            newEntry.append("[" + ls
                    + "\t" + "'ulid' => '" + "???" + "'," + ls
                    + "\t" + "'external_id' => '" +  columns[3] +  "'," + ls
                    + "\t" + "'type' => '" + columns[1].replaceFirst("HTML:","")
                    .replaceFirst("###","").replaceFirst("TEXT:","")
                    .replaceAll("<[^>]*>", "").trim() + "'," + ls
                    + "\t" + "'subject' => '" + columns[0].replaceFirst("HTML:","")
                    .replaceFirst("###","").replaceFirst("TEXT:","")
                    .replaceAll("<[^>]*>", "").trim() + "'," + ls
                    + "\t" + "'category' => '" + columns[2].replaceFirst("HTML:","")
                    .replaceFirst("###","").replaceFirst("TEXT:","")
                    .replaceAll("<[^>]*>", "").trim() + "'," + ls
                    + "]," + ls);

        }
        return newEntry.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println( readFile("e:/sdelki.txt")        );
    }
}
