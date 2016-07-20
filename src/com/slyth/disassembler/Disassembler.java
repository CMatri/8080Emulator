package com.slyth.disassembler;

import java.util.ArrayList;

public class Disassembler {

    private ArrayList<OPEntry> opcodes;

    class OPEntry {
        int bytes;
        int code;
        String name;

        public OPEntry(String name, int code, int bytes) {
            this.name = name;
            this.code = code;
            this.bytes = bytes;
        }
    }

    public Disassembler() {
        opcodes = new ArrayList<OPEntry>();
        opcodes.add(new OPEntry("NOP", 0x00, 1));
        opcodes.add(new OPEntry("LXI", 0x01, 3));
        opcodes.add(new OPEntry("STAX", 0x02, 1));
        opcodes.add(new OPEntry("INX", 0x03, 1));
        opcodes.add(new OPEntry("INR", 0x04, 1));
        opcodes.add(new OPEntry("DCR", 0x05, 1));
        opcodes.add(new OPEntry("MVI", 0x06, 2));
        opcodes.add(new OPEntry("RLC", 0x07, 1));
        opcodes.add(new OPEntry("NOP", 0x08, 1));
    }

    private OPEntry getOPDataFromCode(int code) {
        for(OPEntry o : opcodes)
            if(o.code == code) return o;
        return null;
    }

    public void disassemble8080p(char[] codeBuffer, int pc) {
        while(pc < codeBuffer.length) {
            char c = codeBuffer[pc];

            OPEntry op = getOPDataFromCode((int) c);
            System.out.print(op.name);
            if(op.bytes == 2)
                System.out.print(" 0x" + (int) codeBuffer[pc + 1]);
            else if(op.bytes == 3)
                System.out.print(" 0x" + (int) codeBuffer[pc + 2] + ", 0x" + (int) codeBuffer[pc + 1]);
            System.out.println();

            pc += op.bytes;
        }
    }
}
