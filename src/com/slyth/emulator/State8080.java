package com.slyth.emulator;

public class State8080 {
    static class ConditionCodes {
        boolean z = false;
        boolean s = false;
        boolean p = false;
        boolean cy = false;
        boolean ac = false;
        boolean[] pad = new boolean[]{false, false, false}; // 3 bits
    }

    public byte a;
    public byte b;
    public byte c;
    public byte d;
    public byte e;
    public byte h;
    public byte l;
    public short sp;
    public short pc;
    public byte[] memory;
    public ConditionCodes cc;
    public byte int_enable;
}
