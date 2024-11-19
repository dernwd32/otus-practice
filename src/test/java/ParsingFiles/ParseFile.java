package ParsingFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParseFile {
    public static String readFile(String file, String sep) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader (file));
        String         line = null;
        //StringBuilder  newEntry = new StringBuilder();
        String         ls = System.getProperty("line.separator");
        int i=0;

        line = reader.readLine();
        //( ( line = reader.readLine() ) != null ) {
           // String[] columns = line.split(sep);
//            newEntry.append("[" + ls
//                    + "'ulid' => '" + "???" + "'," + ls
//                    + "'name' => '" + columns[0] + "'," + ls
//                    + "'sort' => '" + "0" + "'," + ls
//                    + "'external_id' => '" + columns[1] + "'," + ls
//                    + "]," + ls);
            ///String[] arOut = columns;

       // }

        return line;
    }

}
