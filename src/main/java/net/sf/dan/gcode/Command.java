package net.sf.dan.gcode;

/**
 * Daneel Yaitskov
 */
public class Command {
    private CmdCode letter;
    private int number;

    private CmdArg[] args;

    public Command(CmdCode letter, int number, CmdArg[] args) {
        this.letter = letter;
        this.number = number;
        this.args = args;
    }

    CmdArg getArgument(CmdArgType type) {
        for (CmdArg arg : args) {
            if (arg.getType() == type) {
                return arg;
            }
        }
        return null;
    }

    public CmdCode getLetter() {
        return letter;
    }

    public void setLetter(CmdCode letter) {
        this.letter = letter;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public CmdArg[] getArgs() {
        return args;
    }

    public void setArgs(CmdArg[] args) {
        this.args = args;
    }
}
