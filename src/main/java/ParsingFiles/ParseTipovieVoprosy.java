package ParsingFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParseTipovieVoprosy {
    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader (file));
        String         line = null;
        StringBuilder  newEntry = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        int i = 0;

        while( ( line = reader.readLine() ) != null ) {
            String[] columns = line.split("\\t");


            String competence = "";
            switch(columns[11].trim()) {
                case "Совет директоров ПАО «НК «Роснефть» [2149633]":
                    competence = "Competence::BOD";
                    break;
                case "Правление ПАО «НК «Роснефть» [2136137]":
                    competence = "Competence::BOM";
                    break;
                default:
                    competence = "null";
                    break;
            }
            String committee = "";
            switch(columns[8].trim()) {
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

            String linked_with_deals = "";
            switch(columns[14].trim()) {
                case "Да":
                    linked_with_deals = "true";
                    break;
                case "Нет":
                    linked_with_deals = "false";
                    break;
                default :
                    linked_with_deals = "null";
                    break;
            }

            String linked_with_projects = "";
            switch(columns[13].trim()) {
                case "Да":
                    linked_with_projects = "true";
                    break;
                case "Нет":
                    linked_with_projects = "false";
                    break;
                default :
                    linked_with_projects = "null";
                    break;
            }

/*
            String resolution_project = columns[6];
            if (resolution_project.equals("###")) resolution_project = "";
            String resolution_project_committee = columns[9];
            if (resolution_project.equals("###")) resolution_project = "";

            String part_num_text = columns[4];
            if (part_num_text.equals("###")) part_num_text = "";

            String review_grounds_text = columns[3];
            if (review_grounds_text.equals("###")) review_grounds_text = "";
            //if  (review_grounds_text.equals("###")) review_grounds_text = "";
*/
            //if (columns[1].equals("Да") && !resolution_project.isEmpty() && !review_grounds_text.isEmpty() && !competence.equals("null")){
            if (columns[1].equals("Да") ){
                    i++;
                    newEntry.append("[" + ls
                            + "\t" + "'ulid' => '" + "???" + "'," + ls
                            + "\t" + "'type' => QuestionType::STANDARD," + ls
                            + "\t" + "'text' => [" + ls
                            + "\t\t"   + "'ru' => '" + columns[5].replaceFirst("HTML:","")
                            .replaceFirst("###","").replaceFirst("TEXT:","")
                            .replaceAll("<[^>]*>", "").trim() + "'," + ls
                            + "\t\t" + "]," + ls
                            + "\t" + "'review_grounds_text' => [" + ls
                            + "\t\t"   + "'ru' => '" + columns[3].replaceFirst("HTML:","")
                            .replaceFirst("###","").replaceFirst("TEXT:","")
                            .replaceAll("<[^>]*>", "").trim() + "'," + ls
                            + "\t\t" + "]," + ls
                            + "\t" + "'resolution_project' => [" + ls
                            + "\t\t"   + "'ru' => '" + columns[6].replaceFirst("HTML:","")
                            .replaceFirst("###","").replaceFirst("TEXT:","")
                            .replaceAll("<[^>]*>", "").trim() + "'" + ls
                            + "\t\t" + "]," + ls
                            + "\t" + "'resolution_project_committee' => [" + ls
                            + "\t\t"   + "'ru' => '" + columns[9].replaceFirst("HTML:","")
                            .replaceFirst("###","").replaceFirst("TEXT:","")
                            .replaceAll("<[^>]*>", "").trim() + "'," + ls
                            + "\t\t" + "]," + ls
                            + "\t" + "'part_num_text' => '" + columns[4].replaceFirst("HTML:","")
                            .replaceFirst("###","").replaceFirst("TEXT:","")
                            .replaceAll("<[^>]*>", "").trim() + "'," + ls
                            + "\t" + "'competence' => " + competence + "," + ls
                            + "\t" + "'external_id' => '" + columns[2] + "'," + ls
                            + "\t" + "'linked_with_deals' => " + linked_with_deals + "," + ls
                            + "\t" + "'linked_with_projects' => " + linked_with_projects + "," + ls
                            + "\t" + "'comittee' => '" + committee + "'," + ls
                            + "]," + ls);

            }
        }
        System.out.println("i="+i);
        return newEntry.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println( readFile("e:/voprosy-new.txt")        );
    }
}
