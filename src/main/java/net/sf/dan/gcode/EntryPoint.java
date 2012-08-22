package net.sf.dan.gcode;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Daneel Yaitskov
 */
public class EntryPoint {
    /**
     * counter  ( file | - )
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage: gcode ( <path to gcode file> | - )");
            System.exit(1);
        }

        String file = args[0];
        InputStream is = null;
        if (file.equals("-")) {
            is = System.in;
        } else {
            try {
                is = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                System.out.println("cannot open file " + file);
                System.exit(1);
            }
        }
        Reader parser = new Reader();
        MaterialCounter mc = new MaterialCounter();
        parser.addFilter(mc);

        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);

        long lineNumber = 0;
        String line = null;
        try {
            while (true) {
                lineNumber += 1;
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                parser.process(line);
            }
        } catch (Throwable e) {
            System.out.println("cannot parse line number: " + lineNumber
                    + "\nbody: " + line
                    + "\nexception: " + e.getMessage());
            System.exit(1);
        }

        System.out.println("Total length of extruder " + mc.getTotal() + " mm");
    }
}
