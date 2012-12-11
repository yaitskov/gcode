package net.sf.dan.gcode;

/**
 * Daneel Yaitskov
 */
public class InitCounterState extends AbstractCounterState {

    public InitCounterState(MaterialCounter counter) {
        super(counter);
    }

    @Override
    public void processLine(Command cmd) {
        if (cmd.getArgument(CmdArgType.X) != null
                || cmd.getArgument(CmdArgType.Y) != null
                || cmd.getArgument(CmdArgType.Z) != null) {
            if (cmd.getArgument(CmdArgType.E) != null) {
                CounterState cs = new SumExtruderCounterState(counter);
                counter.setState(cs);
                cs.processLine(cmd);
            } else if (cmd.getArgument(CmdArgType.F) != null) {
                CmdArg x = cmd.getArgument(CmdArgType.X);
                CmdArg y = cmd.getArgument(CmdArgType.Y);
                CmdArg z = cmd.getArgument(CmdArgType.Z);
                Position init = new Position(x == null ? 0 : x.getValue(),
                        y == null ? 0 : y.getValue(),
                        z == null ? 0 : z.getValue());
                CounterState cs = new SumDryMoveCounterState(init, counter);
                counter.setState(cs);
                //cs.processLine(cmd);
            }
        }
    }
}
