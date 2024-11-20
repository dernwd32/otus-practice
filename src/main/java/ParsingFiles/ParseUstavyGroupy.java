package ParsingFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static ParsingFiles.RegexExtractor.extractIds;


public class ParseUstavyGroupy {
    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(file));
        String         line = null;
        StringBuilder  newEntry = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        while( ( line = reader.readLine() ) != null ) {

            String[] columns = line.split("\\t");

            String[] groupTsauk = columns[4].split(" / ");
            String[] groupExpert = columns[3].split(" / ");

            StringBuilder groupTsaukIds = new StringBuilder();
            for (String str : groupTsauk) {
                List<Integer> extractedIntegers = extractIds(str);
                for (int thisid : extractedIntegers)
                    groupTsaukIds.append("\t'").append(thisid).append("',").append(ls);
            }
            StringBuilder groupExpertIds = new StringBuilder();
            for (String str : groupExpert) {
                List<Integer> extractedIntegers = extractIds(str);
                for (int thisid : extractedIntegers)
                    groupExpertIds.append("\t'").append(thisid).append("',").append(ls);
            }


            if (columns[1].equals("Да"))
            newEntry.append("[" + ls
                    +  "'" + columns[2] + "' => [" + ls
                    + groupTsaukIds + groupExpertIds
                    + "\t],"  + ls
                    + "]," + ls);

        }
        return newEntry.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println( readFile("e:/ustavy-groupy.txt"));
    }
}
