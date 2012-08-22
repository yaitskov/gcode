package net.sf.dan.gcode;

import java.math.BigDecimal;

/**
 * Count total length of used extruder.
 * Daneel Yaitskov
 */
public class MaterialCounter implements CmdFilter {

    public BigDecimal total;

    public MaterialCounter() {
        total = BigDecimal.ZERO;

    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public void process(Command cmd) {
        if (cmd.getLetter() == CmdCode.G && cmd.getNumber() == 1) {
            int coords = 0;
            BigDecimal length = null;
            for (CmdArg arg : cmd.getArgs()) {
                if (arg.getType().isCoord()) {
                    coords += 1;
                } else if (arg.getType() == CmdArgType.E) {
                    length = arg.getValue();
                }
            }
            if (coords > 0 && length != null) {
                total = total.add(length);
            }
        }
    }
}
