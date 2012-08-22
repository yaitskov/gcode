package net.sf.dan.gcode;

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

    public void process(String line) {

    }
}
