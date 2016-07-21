package com.slyth;

import com.slyth.disassembler.Disassembler;
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
        d.disassemble8080p(data, 0x18d4, 0x18e4);
    }

    public static void main(String[] args) {
        new Main();
    }
}
