package net.sf.dan.gcode;

import java.math.BigDecimal;

/**
 * Daneel Yaitskov
 */
public class CmdArg {
    private CmdArgType type;
    private double value;

    public CmdArgType getType() {
        return type;
    }

    public void setType(CmdArgType type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
