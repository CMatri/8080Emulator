package com.slyth;

import com.slyth.disassembler.Disassembler;
import com.slyth.emulator.Emulator;
import com.slyth.emulator.State8080;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public Main() {
        Path path = Paths.get("Z:\\Programming\\Java\\8080Emulator\\res\\invaders.bin");
        byte[] data = null;

        try {
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Disassembler d = new Disassembler();
        d.disassemble8080p(data, 0, 0x18d4, false);

        State8080 state = new State8080();
        state.memory = data;

        Emulator e = new Emulator();

        for(int i = 0; i < 0x10; i++) {
            e.emulate8080Op(state);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
