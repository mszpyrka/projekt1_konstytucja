package agh.po.mszpyrka.projekt1;

import java.io.*;
import java.util.LinkedList;

public class MainSystem {
    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(
                        new FileReader(
                                new File(args[0] + "uokik.txt")))){

            SourceParser sourceParser = new SourceParser();
            LinkedList<DocLine> list = sourceParser.parseFromReader(reader);

            Contents document = new Contents(list.getFirst());
            document.parse(list, 0);

            DocSearcher d = new DocSearcher(document);
            CommandLineParser cliParser = new CommandLineParser(args);
            String[][] paths = cliParser.getAllPaths();

            LinkedList<Contents> list2 = d.getRange(paths[0], paths[1]);
            //System.out.println(d.showContetns(paths[0], cliParser.getMode()));
            DocPrinter printer = new DocPrinter();
            LinkedList<String> st = printer.showFullContents(list2);

            for(String s : st)
                System.out.println(s);

        }
        catch (Exception ex) {
            System.out.println(ex.getMessage()); //TODO
        }





    }

}
