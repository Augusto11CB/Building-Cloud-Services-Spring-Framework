package org.magnum.dataup.utils;

public class IdCalculator {

    private long nextId = 0;

    public long getNextId() {
        long returnValue = nextId;
        nextId = +1;

        return returnValue;
    }
}
