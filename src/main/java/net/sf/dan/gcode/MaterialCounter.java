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

    }
}
