package Greb;

import java.io.*;

import org.kohsuke.args4j.Argument;

public class Greb {

    private final boolean flagV;
    private final boolean flagI;
    private final boolean flagR;
    private final String regex;
    private final String inputFiles;

    public Greb(boolean flagV, boolean flagI, boolean flagR, String regex, String inputFiles) {
        this.flagV = flagV;
        this.flagI = flagI;
        this.flagR = flagR;
        this.regex = regex;
        this.inputFiles = inputFiles;
    }

    public void greb() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        File file = new File(inputFiles);
        BufferedReader fin = new BufferedReader(new FileReader(file));
        StringBuilder text = new StringBuilder();
        String line;
        while ((line = fin.readLine()) != null) {
            text.append(line + "\n");
        }
        System.out.println(text);
    }
}
