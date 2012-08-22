package net.sf.dan.gcode;

/**
 * Daneel Yaitskov
 */
public enum CmdArgType {
    E, F, X {
        @Override
        public boolean isCoord() {
            return true;
        }
    }, Y {
        @Override
        public boolean isCoord() {
            return true;
        }

    }, Z {
        @Override
        public boolean isCoord() {
            return true;
        }
    }, S;

    public boolean isCoord() {
        return false;
    }
}
