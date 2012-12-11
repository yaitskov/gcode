package net.sf.dan.gcode;

import java.awt.*;
import java.math.BigDecimal;

/**
 * Daneel Yaitskov
 */
public class SumDryMoveCounterState extends AbstractCounterState {

    private Position last;

    public SumDryMoveCounterState(Position last, MaterialCounter counter) {
        super(counter);
        this.last = last;
    }

    @Override
    public void processLine(Command cmd) {
        Position current = new Position(last);
        for (CmdArg arg : cmd.getArgs()) {
            switch (arg.getType()) {
                case X:
                    current.setX(arg.getValue());
                    break;
                case Y:
                    current.setY(arg.getValue());
                    break;
                case Z:
                    current.setZ(arg.getValue());
                    break;
                case F:
                    counter.setState(new InitCounterState(counter));
                    return;
            }
        }
        double d = current.distance(last) * 0.06;
        //counter.addTotal(d);
        last = current;
    }
}
