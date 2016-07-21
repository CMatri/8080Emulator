package com.slyth;

import com.slyth.disassembler.Disassembler;
import com.slyth.emulator.Emulator;
import com.slyth.emulator.State8080;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;


public class Main {

    public Main() {
        File f = new File("./res/invaders.bin");
        byte[] code = new byte[Math.toIntExact(f.length())];
        byte[] data = new byte[code.length + (1024 * 8)];

        try {
            DataInputStream dataIs = new DataInputStream(new FileInputStream(f));
            dataIs.readFully(code);

            for(int i = 0; i < code.length; i++) {
                data[i] = code[i];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Disassembler d = new Disassembler();
        String s = d.disassemble8080p(data, 0x1a5f, 0x1a70, true);
        System.out.println(s);

        State8080 state = new State8080();
        state.memory = data;

        Emulator e = new Emulator();

        for (int i = 0; i < 35400; i++) {
            System.out.print(i + " : ");
            e.emulate8080Op(state, d);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
