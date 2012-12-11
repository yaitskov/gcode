package net.sf.dan.gcode;

/**
 * Daneel Yaitskov
 */
public abstract class AbstractCounterState implements CounterState {

    protected MaterialCounter counter;

    protected AbstractCounterState(MaterialCounter counter) {
        this.counter = counter;
    }
}
