package net.sf.dan.gcode;

import java.math.BigDecimal;

/**
 * Count total length of used extruder.
 * Daneel Yaitskov
 */
public class MaterialCounter implements CmdFilter {

    private double total;
    private CounterState state;

    public MaterialCounter() {
        state = new InitCounterState(this);
    }

    public double getTotal() {
        return total;
    }

    public void addTotal(double v) {
        total += v;
    }

    public CounterState getState() {
        return state;
    }

    public void setState(CounterState state) {
        this.state = state;
    }

    @Override
    public void process(Command cmd) {
        if (cmd.getLetter() == CmdCode.G && cmd.getNumber() == 1) {
            state.processLine(cmd);
        }
    }
}
