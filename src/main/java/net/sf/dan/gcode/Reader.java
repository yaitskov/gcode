package net.sf.dan.gcode;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Daneel Yaitskov
 */
public class Reader {

    private List<CmdFilter> filters = new ArrayList<CmdFilter>();

    public void addFilter(CmdFilter filter) {
        filters.add(filter);
    }

    public void process(String line) throws ParseException {
        line = line.trim();
        line = line.replaceAll(";.*$", "");
        if (line.isEmpty()) {
            return;
        }

        String[] pairs = line.split(" +");

        Command cmd = new Command(getLetter(pairs[0]), getNumber(pairs[0]), getArgs(pairs));
        for (CmdFilter filter : filters) {
            filter.process(cmd);
        }
    }

    protected CmdCode getLetter(String cmd) throws ParseException {
        return CmdCode.valueOf(cmd.substring(0, 1).toUpperCase());
    }

    protected int getNumber(String cmd) throws ParseException {
        return Integer.parseInt(cmd.substring(1));
    }

    protected CmdArg[] getArgs(String[] pairs) throws ParseException {
        CmdArg[] result = new CmdArg[pairs.length - 1];
        for (int i = 1; i < pairs.length; ++i) {
            CmdArg arg = new CmdArg();
            arg.setType(CmdArgType.valueOf(pairs[i].substring(0,1).toUpperCase()));
            arg.setValue(new BigDecimal(pairs[i].substring(1)));
            result[i - 1] = arg;
        }
        return result;
    }


}
