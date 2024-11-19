package ParsingFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParseSoglasGroups {
    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader (file));
        String         line = null;
        StringBuilder  newEntry = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        while( ( line = reader.readLine() ) != null ) {
            String[] columns = line.split("\\t");
            newEntry.append("[" + ls
                    + "'ulid' => '" + "???" + "'," + ls
                    + "'name' => '" + columns[0] + "'," + ls
                    + "'sort' => '" + "0" + "'," + ls
                    + "'external_id' => '" + columns[1] + "'," + ls
                    + "]," + ls);

        }
        return newEntry.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println( readFile("e:/tmp_groups1.txt")        );
    }
}
