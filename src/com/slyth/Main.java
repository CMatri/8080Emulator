package com.slyth;

import com.slyth.disassembler.Disassembler;

public class Main {

    public static void main(String[] args) {
        Disassembler d = new Disassembler();
        d.disassemble8080p(new char[] {0x00, 0x00, 0x01, 0xe4, 0xe5, 0x06, 0x32, 0x00, 0x00, 0x07}, 0);
    }
}
