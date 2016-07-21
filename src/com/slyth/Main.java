package com.slyth;

import com.slyth.disassembler.Disassembler;
import com.slyth.emulator.Emulator;
import com.slyth.emulator.State8080;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;


public class Main {

    byte[] loadMemFromFile(String name) {
        File f = new File(name);
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

        return data;
    }

    byte[] loadMemFromString(String mem) {
        byte[] data = new byte[mem.length() + (1024 * 8)];

        for(int i = 0; i < mem.length(); i++) {
            if(mem.charAt(i) == ':' || mem.charAt(i) == ' ' || mem.charAt(i) == '\n') continue;
            data[i] = (byte) mem.charAt(i);
        }

        return data;
    }

    public Main() {
        byte[] data = loadMemFromFile("./res/invaders.bin");

        data = loadMemFromString(":1D0000002110007EB7CA0E00D30123C30300760048656C6C6F20576F726C64210035\n" +
                                ":00000001FF");

        Disassembler d = new Disassembler();
        String s = d.disassemble8080p(data, 0, 0x20, true);
        System.out.println(s);

        State8080 state = new State8080();
        state.memory = data;

        Emulator e = new Emulator();

        for (int i = 0; i < 35400; i++) {
            //System.out.print(i + " : ");
            //e.emulate8080Op(state, d);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
