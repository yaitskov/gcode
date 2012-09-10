package net.sf.dan.gcode;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.ParseException;

/**
 * Daneel Yaitskov
 */

public class ConstFactorTest {

    private static final Logger logger = LoggerFactory.getLogger(ConstFactorTest.class);

    public double getLength(File file) throws IOException, ParseException {
        Processor p = new Processor();
        MaterialCounter mc = new MaterialCounter();
        p.addFilter(mc);
        p.processFile(new FileInputStream(file));
        return mc.getTotal().doubleValue();
    }

    @Test
    public void test() throws Exception {
        String path = Object.class.getResource("/input").getPath();
        logger.debug("path {}", path);
        File dirOfTests = new File(path);
        File[] files = dirOfTests.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return s.endsWith(".gcode");
            }
        });
        double[] lengths = new double[files.length];
        double[] ks = new double[files.length];
        double  ksum = 0.0;
        for (int i = 0; i < files.length; ++i) {
            lengths[i] = getLength(files[i]);
            double weight = getWeight(files[i]);
            ks[i] = lengths[i] / weight;
            ksum += ks[i];
            logger.debug(String.format("file %-30s => %11.4f / %4.0f = %11.4f",
                    files[i].getName(), lengths[i], weight, ks[i]));

        }
        logger.debug("calculation of the factor");

        double avr = ksum / ks.length;
        logger.debug("average " + avr);
        for (double k : ks) {
            double deviation = k - avr;
            logger.debug("deviation " + deviation);
        }
    }

    double getWeight(File f) {
        String w = f.getName().replace(".gcode", "")
                .replaceAll("[A-Za-z_ -]+", "");
        return Double.parseDouble(w);
    }
}
