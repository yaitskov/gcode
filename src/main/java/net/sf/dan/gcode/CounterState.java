package net.sf.dan.gcode;

/**
 * Daneel Yaitskov
 */
public interface CounterState {

    void processLine(Command cmd);
}
