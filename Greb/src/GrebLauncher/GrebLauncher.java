package GrebLauncher;

import Greb.*;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class GrebLauncher {

    @Option(name = "-v", usage = "Inverse the filter condition")
    private boolean flagV;

    @Option(name = "-i", usage = "Ignore case")
    private boolean flagI;

    @Option(name = "-r", usage = "Set regular expression to search")
    private boolean flagR;

    @Argument(usage = "Filter and input file")
    private String[] flag;

    public static void main(String[] args) {
        new GrebLauncher().launch(args);
    }

    private void launch(String[] args) {
        String inputFiles = "";
        String regex = "";
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            inputFiles = args[args.length - 1];
            new FileReader(inputFiles);
            if (flagR) {
                regex = Arrays.toString(Arrays.copyOf(flag, flag.length - 1));
            }
            else {
                regex = flag[0];
            }
            if (!flagR && flag.length > 2) System.err.println("greb [-v] [-i] [-r] [word] [file]");
        }
        catch (CmdLineException e) {
            System.err.println("greb [-v] [-i] [-r] [word] [file]");
            parser.printUsage(System.err);
            return;
        }
        catch (FileNotFoundException e) {
            System.err.println("Not found input files");
            return;
        }
        Greb greb = new Greb(flagV, flagI, flagR, regex, inputFiles);
        try {
            greb.greb();
        } catch (IOException e) {
            System.err.println("File error");
            e.printStackTrace();
        }

    }

}
