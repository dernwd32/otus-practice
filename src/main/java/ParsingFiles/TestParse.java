package ParsingFiles;//package ParsingFiles;

import java.io.IOException;

//import java.io.IOException;

public class TestParse{
    public static void main(String[] args) throws IOException {
        String file = "e:/tmp_groups1.txt";
        String sep = "\\t";
        System.out.println(ParseFile.readFile(file, sep));
    }
}