package net.sf.dan.gcode;

/**
 * Daneel Yaitskov
 */
public interface CmdFilter {
    void process(Command cmd);
}
