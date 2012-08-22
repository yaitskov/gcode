package net.sf.dan.gcode;

import java.math.BigDecimal;

/**
 * Daneel Yaitskov
 */
public class CmdArg {
    private CmdArgType type;
    private BigDecimal value;

    public CmdArgType getType() {
        return type;
    }

    public void setType(CmdArgType type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
