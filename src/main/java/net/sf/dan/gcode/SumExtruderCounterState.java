package net.sf.dan.gcode;

import java.math.BigDecimal;

/**
 * Daneel Yaitskov
 */
public class SumExtruderCounterState extends AbstractCounterState {

    public SumExtruderCounterState(MaterialCounter counter) {
        super(counter);
    }

    @Override
    public void processLine(Command cmd) {
        int coords = 0;
        boolean f = false;
        Double length = null;
        for (CmdArg arg : cmd.getArgs()) {
            if (arg.getType().isCoord()) {
                coords += 1;
            } else if (arg.getType() == CmdArgType.E) {
                length = arg.getValue();
            } else if (arg.getType() == CmdArgType.F) {
                f = true;
            }
        }
        if (coords > 0 && length != null) {
            counter.addTotal(length);
        } else if (f) {
            counter.setState(new InitCounterState(counter));
        }
    }
}
