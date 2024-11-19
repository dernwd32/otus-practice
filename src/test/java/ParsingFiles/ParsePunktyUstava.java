package ParsingFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParsePunktyUstava {
    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader (file));
        String         line = null;
        StringBuilder  newEntry = new StringBuilder();
        String         ls = System.getProperty("line.separator");


        while( ( line = reader.readLine() ) != null ) {

            String[] columns = line.split("\\t");

            StringBuilder substitution = new StringBuilder("QuestionSubstitution::");
            switch(columns[9].trim()) {
                case "по-умолчанию":
                    substitution.append("STANDART");
                    break;
                case "для нетипового вопроса":
                    substitution.append("MANUAL");
                    break;
                case "по-умолчанию для нетипового вопроса":
                    substitution.append("ALL");
                    break;
                default :
                    substitution.append("NONE");
                    break;
            }

            String committee = "";
            switch(columns[6].trim()) {
                case "Комитет по стратегии и устойчивому развитию [3084]":
                    committee = "ksur";
                    break;
                case "Комитет по кадрам и вознаграждениям [3086]":
                    committee = "kpkv";
                    break;
                case "Комитет по аудиту [3085]":
                    committee = "kpa";
                    break;
                default :
                    committee = "";
                    break;
            }

            String detail = columns[2].replaceFirst("HTML:","")
                    .replaceFirst("TEXT:","");
            String footnote = columns[5].replaceFirst("HTML:","")
                    .replaceFirst("TEXT:","");

            //System.out.println(columns[3]);

            if (columns[3].trim().equals("Да")) {
                newEntry.append("[" + ls
                        + "\t" + "'ulid' => '" + "???" + "'," + ls
                        + "\t" + "'part_num' => '" + columns[0] + "'," + ls
                        + "\t" + "'detail' => [" + ls + "\t\t" + "'ru' => '" + detail
                            + "'," + ls + "\t" + "]," + ls
                        + "\t" + "'footnote' => '" + footnote + "'," + ls
                        + "\t" + "'substitution' => " + substitution + "," + ls
                        + "\t" + "'external_id' => '" + columns[11] + "'," + ls
                        + "\t" + "'committee' => '" + committee + "'," + ls
                        + "]," + ls);
            }

        }
        return newEntry.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println( readFile("e:/ustavy-new.txt")        );
    }
}
