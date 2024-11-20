package ParsingFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static ParsingFiles.RegexExtractor.extractIds;

public class ParseVoprosOsnovanie {
    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader (file));
        String         line = null;
        StringBuilder  newEntryComm = new StringBuilder();
        StringBuilder  newEntryMain = new StringBuilder();
        StringBuilder  newEntry = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        int i = 0;


        while( ( line = reader.readLine() ) != null ) {
            String[] columns = line.split("\\t");

            //String ground1 = "", ground2 = "";
            StringBuilder groundMain = new StringBuilder();
            StringBuilder groundComm = new StringBuilder();
            if (!columns[3].equals("###"))
                for (int id : extractIds(columns[3]) )
                    groundMain.append("\t'" + id + "'," + ls);
            if (!columns[1].equals("###"))
                for (int id : extractIds(columns[1]) )
                    groundComm.append("\t'" + id + "'," + ls);



          //  if (!columns[3].equals("###"))  ground2 = extractIds(columns[3]).toString();

            if (!groundMain.isEmpty()) {
                newEntryMain.append("'" + columns[0] + "' => [" + ls
                        + groundMain
                        + "\t]," + ls
                );
            }


            if (!groundComm.isEmpty()) {
                newEntryComm.append("'" + columns[0] + "' => [" + ls
                        + groundComm
                        + "\t]," + ls
                );
            }
        }
        newEntry.append(ls+ls+ "Committee" +ls+ls+ "[" + ls).append(newEntryComm)
                .append("]," +ls+ls+ "hometask1.Main" +ls+ls+ "[" + ls).append(newEntryMain)
                .append("]," + ls);
        //System.out.println("i="+i);
        return newEntry.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println( readFile("e:/vopros-osnovanie.txt")        );
    }
}
