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
        return mc.getTotal();
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
        double[] weights = new double[files.length];
        double  ksum = 0.0;
        for (int i = 0; i < files.length; ++i) {
            lengths[i] = getLength(files[i]);
            weights[i] = getWeight(files[i]);
            ks[i] = lengths[i] / weights[i];
            ksum += ks[i];
        }
        logger.debug("calculation of the factor");

        double avr = ksum / ks.length;
        logger.debug("average " + avr);
        logger.debug(String.format("file %-28s = %11s / w(g)   %11s; avr           fl      wdelta  wpercent", "", "length", "k"));
        double devSum = 0.0;
        double sumWeight = 0.0;
        for (int i = 0; i < ks.length; ++i) {
            double predictedWeight = lengths[i] / avr;
            double weightDevi = Math.abs(weights[i] - predictedWeight);
            devSum += weightDevi;
            double percent = weightDevi / weights[i] * 100.0;
            sumWeight += weights[i];
            logger.debug(String.format("file %-28s = %11.4f / %4.0f = %11.4f; %8.4f, %8.4f, %7.4f %5.2f%%",
                    files[i].getName(), lengths[i], weights[i], ks[i], avr, predictedWeight,
                    weightDevi, percent));
//            logger.debug(String.format("deviation %11.4f => %5.2f",
//                    deviation, Math.abs(ks[i] - avr)/ks[i] * 100.0));
        }

        logger.debug(String.format("**** TOTAL DEVIATION  %.2fg and weight %.2fg *******",
                devSum, sumWeight));
    }

    double getWeight(File f) {
        String w = f.getName().replace(".gcode", "")
                .replaceAll("[A-Za-z_ -]+", "");
        return Double.parseDouble(w);
    }
}
