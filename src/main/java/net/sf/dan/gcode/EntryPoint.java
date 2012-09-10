package net.sf.dan.gcode;

import java.io.*;
import java.text.ParseException;

/**
 * Entry point of the program for the command line interface.
 * Daneel Yaitskov
 */
public class EntryPoint {
    /**
     * How to run:
     * <pre>
     * counter  ( file | - )
     * </pre>
     *
     * @param args path to file or "-" (dash) if read data from standard input
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage: gcode ( <path to gcode file> | - )");
            System.exit(1);
        }
        try {
            InputStream input = getReader(args[0]);
            analyze(input);
        } catch (ParseException e) {
            error("cannot parse line " + e.getErrorOffset()
                    + " cause " + e.getMessage());
        } catch (IOException e) {
            error("error '" + e.getMessage() + "'; file '" + args[0] + "'");
        }
    }

    public static InputStream getReader(String file)
            throws FileNotFoundException {
        if (file.equals("-")) {
            return System.in;
        } else {
            return new FileInputStream(file);
        }
    }

    public static void error(String msg) {
        System.err.println(msg);
        System.exit(1);
    }

    public static void analyze(InputStream input)
            throws ParseException, IOException {
        Processor processor = new Processor();
        MaterialCounter mc = new MaterialCounter();
        processor.addFilter(mc);
        processor.processFile(input);
        System.out.println("Total length of extruder " + mc.getTotal() + " mm");
    }
}
