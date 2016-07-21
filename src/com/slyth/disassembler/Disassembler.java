package com.slyth.disassembler;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;

public class Disassembler {

    private ArrayList<OPEntry> opcodes;

    public class OPEntry {
        public int bytes;
        public byte code;
        public String name;

        public OPEntry(String name, int code, int bytes) {
            this.name = name;
            this.code = (byte) code;
            this.bytes = bytes;
        }
    }

    public Disassembler() {
        opcodes = new ArrayList<OPEntry>();
        opcodes.add(new OPEntry("NOP    ", 0x00, 1));
        opcodes.add(new OPEntry("LXI    B,", 0x01, 3));
        opcodes.add(new OPEntry("STAX   B", 0x02, 1));
        opcodes.add(new OPEntry("INX    B", 0x03, 1));
        opcodes.add(new OPEntry("INR    B", 0x04, 1));
        opcodes.add(new OPEntry("DCR    B", 0x05, 1));
        opcodes.add(new OPEntry("MVI    B,", 0x06, 2));
        opcodes.add(new OPEntry("RLC    ", 0x07, 1));
        opcodes.add(new OPEntry("NOP    ", 0x08, 1));
        opcodes.add(new OPEntry("DAD    B", 0x09, 1));
        opcodes.add(new OPEntry("LDAX   B", 0x0a, 1));
        opcodes.add(new OPEntry("DCX    B", 0x0b, 1));
        opcodes.add(new OPEntry("INR    C", 0x0c, 1));
        opcodes.add(new OPEntry("DCR    C", 0x0d, 1));
        opcodes.add(new OPEntry("MVI    C,", 0x0e, 2));
        opcodes.add(new OPEntry("RRC    ", 0x0f, 1));
        opcodes.add(new OPEntry("NOP    ", 0x10, 1));
        opcodes.add(new OPEntry("LXI    D,", 0x11, 3));
        opcodes.add(new OPEntry("STAX   D", 0x12, 1));
        opcodes.add(new OPEntry("INX    D", 0x13, 1));
        opcodes.add(new OPEntry("INR    D", 0x14, 1));
        opcodes.add(new OPEntry("DCR    D", 0x15, 1));
        opcodes.add(new OPEntry("MVI    D,", 0x16, 2));
        opcodes.add(new OPEntry("RAL    ", 0x17, 1));
        opcodes.add(new OPEntry("NOP    ", 0x18, 1));
        opcodes.add(new OPEntry("DAD    D", 0x19, 1));
        opcodes.add(new OPEntry("LDAX   D", 0x1a, 1));
        opcodes.add(new OPEntry("DCX    D", 0x1b, 1));
        opcodes.add(new OPEntry("INR    E", 0x1c, 1));
        opcodes.add(new OPEntry("DCR    E", 0x1d, 1));
        opcodes.add(new OPEntry("MVI    E,", 0x1e, 2));
        opcodes.add(new OPEntry("RAR    ", 0x1f, 1));
        opcodes.add(new OPEntry("RIM    ", 0x20, 1));
        opcodes.add(new OPEntry("LXI    H,", 0x21, 3));
        opcodes.add(new OPEntry("SHLD   ", 0x22, 3));
        opcodes.add(new OPEntry("INX    H", 0x23, 1));
        opcodes.add(new OPEntry("INR    H", 0x24, 1));
        opcodes.add(new OPEntry("DCR    H", 0x25, 1));
        opcodes.add(new OPEntry("MVI    H,", 0x26, 2));
        opcodes.add(new OPEntry("DAA    ", 0x27, 1));
        opcodes.add(new OPEntry("NOP    H", 0x28, 1));
        opcodes.add(new OPEntry("DAD    ", 0x29, 1));
        opcodes.add(new OPEntry("LHLD   ", 0x2a, 3));
        opcodes.add(new OPEntry("DCX    H", 0x2b, 1));
        opcodes.add(new OPEntry("INR    L", 0x2c, 1));
        opcodes.add(new OPEntry("DCR    L", 0x2d, 1));
        opcodes.add(new OPEntry("MVI    L,", 0x2e, 2));
        opcodes.add(new OPEntry("CMA    ", 0x2f, 1));
        opcodes.add(new OPEntry("SIM    ", 0x30, 1));
        opcodes.add(new OPEntry("LXI    SP,", 0x31, 3));
        opcodes.add(new OPEntry("STA    ", 0x32, 3));
        opcodes.add(new OPEntry("INX    SP", 0x33, 1));
        opcodes.add(new OPEntry("INR    M", 0x34, 1));
        opcodes.add(new OPEntry("DCR    M", 0x35, 1));
        opcodes.add(new OPEntry("MVI    M,", 0x36, 2));
        opcodes.add(new OPEntry("STC    ", 0x37, 1));
        opcodes.add(new OPEntry("NOP    ", 0x38, 1));
        opcodes.add(new OPEntry("DAD    SP", 0x39, 1));
        opcodes.add(new OPEntry("LDA    ", 0x3a, 3));
        opcodes.add(new OPEntry("DCX    SP", 0x3b, 1));
        opcodes.add(new OPEntry("INR    A", 0x3c, 1));
        opcodes.add(new OPEntry("DCR    A", 0x3d, 1));
        opcodes.add(new OPEntry("MVI    A,", 0x3e, 2));
        opcodes.add(new OPEntry("CMC    ", 0x3f, 1));
        opcodes.add(new OPEntry("MOV    B, B", 0x40, 1));
        opcodes.add(new OPEntry("MOV    B, C", 0x41, 1));
        opcodes.add(new OPEntry("MOV    B, D", 0x42, 1));
        opcodes.add(new OPEntry("MOV    B, E", 0x43, 1));
        opcodes.add(new OPEntry("MOV    B, H", 0x44, 1));
        opcodes.add(new OPEntry("MOV    B, L", 0x45, 1));
        opcodes.add(new OPEntry("MOV    B, M", 0x46, 1));
        opcodes.add(new OPEntry("MOV    B, A", 0x47, 1));
        opcodes.add(new OPEntry("MOV    C, B", 0x48, 1));
        opcodes.add(new OPEntry("MOV    C, C", 0x49, 1));
        opcodes.add(new OPEntry("MOV    C, D", 0x4a, 1));
        opcodes.add(new OPEntry("MOV    C, E", 0x4b, 1));
        opcodes.add(new OPEntry("MOV    C, H", 0x4c, 1));
        opcodes.add(new OPEntry("MOV    C, L", 0x4d, 1));
        opcodes.add(new OPEntry("MOV    C, M", 0x4e, 1));
        opcodes.add(new OPEntry("MOV    C, A", 0x4f, 1));
        opcodes.add(new OPEntry("MOV    D, B", 0x50, 1));
        opcodes.add(new OPEntry("MOV    D, C", 0x51, 1));
        opcodes.add(new OPEntry("MOV    D, D", 0x52, 1));
        opcodes.add(new OPEntry("MOV    D, E", 0x53, 1));
        opcodes.add(new OPEntry("MOV    D, H", 0x54, 1));
        opcodes.add(new OPEntry("MOV    D, L", 0x55, 1));
        opcodes.add(new OPEntry("MOV    D, M", 0x56, 1));
        opcodes.add(new OPEntry("MOV    D, A", 0x57, 1));
        opcodes.add(new OPEntry("MOV    E, B", 0x58, 1));
        opcodes.add(new OPEntry("MOV    E, C", 0x59, 1));
        opcodes.add(new OPEntry("MOV    E, D", 0x5a, 1));
        opcodes.add(new OPEntry("MOV    E, E", 0x5b, 1));
        opcodes.add(new OPEntry("MOV    E, H", 0x5c, 1));
        opcodes.add(new OPEntry("MOV    E, L", 0x5d, 1));
        opcodes.add(new OPEntry("MOV    E, M", 0x5e, 1));
        opcodes.add(new OPEntry("MOV    E, A", 0x5f, 1));
        opcodes.add(new OPEntry("MOV    H, B", 0x60, 1));
        opcodes.add(new OPEntry("MOV    H, C", 0x61, 1));
        opcodes.add(new OPEntry("MOV    H, D", 0x62, 1));
        opcodes.add(new OPEntry("MOV    H, E", 0x63, 1));
        opcodes.add(new OPEntry("MOV    H, H", 0x64, 1));
        opcodes.add(new OPEntry("MOV    H, L", 0x65, 1));
        opcodes.add(new OPEntry("MOV    H, M", 0x66, 1));
        opcodes.add(new OPEntry("MOV    H, A", 0x67, 1));
        opcodes.add(new OPEntry("MOV    L, B", 0x68, 1));
        opcodes.add(new OPEntry("MOV    L, C", 0x69, 1));
        opcodes.add(new OPEntry("MOV    L, D", 0x6a, 1));
        opcodes.add(new OPEntry("MOV    L, E", 0x6b, 1));
        opcodes.add(new OPEntry("MOV    L, H", 0x6c, 1));
        opcodes.add(new OPEntry("MOV    L, L", 0x6d, 1));
        opcodes.add(new OPEntry("MOV    L, M", 0x6e, 1));
        opcodes.add(new OPEntry("MOV    L, A", 0x6f, 1));
        opcodes.add(new OPEntry("MOV    M, B", 0x70, 1));
        opcodes.add(new OPEntry("MOV    M, C", 0x71, 1));
        opcodes.add(new OPEntry("MOV    M, D", 0x72, 1));
        opcodes.add(new OPEntry("MOV    M, E", 0x73, 1));
        opcodes.add(new OPEntry("MOV    M, H", 0x74, 1));
        opcodes.add(new OPEntry("MOV    M, L", 0x75, 1));
        opcodes.add(new OPEntry("HLT    ", 0x76, 1));
        opcodes.add(new OPEntry("MOV    M, A", 0x77, 1));
        opcodes.add(new OPEntry("MOV    A, B", 0x78, 1));
        opcodes.add(new OPEntry("MOV    A, C", 0x79, 1));
        opcodes.add(new OPEntry("MOV    A, D", 0x7a, 1));
        opcodes.add(new OPEntry("MOV    A, E", 0x7b, 1));
        opcodes.add(new OPEntry("MOV    A, H", 0x7c, 1));
        opcodes.add(new OPEntry("MOV    A, L", 0x7d, 1));
        opcodes.add(new OPEntry("MOV    A, M", 0x7e, 1));
        opcodes.add(new OPEntry("MOV    A, A", 0x7f, 1));
        opcodes.add(new OPEntry("ADD    B", 0x80, 1));
        opcodes.add(new OPEntry("ADD    C", 0x81, 1));
        opcodes.add(new OPEntry("ADD    D", 0x82, 1));
        opcodes.add(new OPEntry("ADD    E", 0x83, 1));
        opcodes.add(new OPEntry("ADD    H", 0x84, 1));
        opcodes.add(new OPEntry("ADD    L", 0x85, 1));
        opcodes.add(new OPEntry("ADD    M", 0x86, 1));
        opcodes.add(new OPEntry("ADD    A", 0x87, 1));
        opcodes.add(new OPEntry("ADC    B", 0x88, 1));
        opcodes.add(new OPEntry("ADC    C", 0x89, 1));
        opcodes.add(new OPEntry("ADC    D", 0x8a, 1));
        opcodes.add(new OPEntry("ADC    E", 0x8b, 1));
        opcodes.add(new OPEntry("ADC    H", 0x8c, 1));
        opcodes.add(new OPEntry("ADC    L", 0x8d, 1));
        opcodes.add(new OPEntry("ADC    M", 0x8e, 1));
        opcodes.add(new OPEntry("ADC    A", 0x8f, 1));
        opcodes.add(new OPEntry("SUB    B", 0x90, 1));
        opcodes.add(new OPEntry("SUB    C", 0x91, 1));
        opcodes.add(new OPEntry("SUB    D", 0x92, 1));
        opcodes.add(new OPEntry("SUB    E", 0x93, 1));
        opcodes.add(new OPEntry("SUB    H", 0x94, 1));
        opcodes.add(new OPEntry("SUB    L", 0x95, 1));
        opcodes.add(new OPEntry("SUB    M", 0x96, 1));
        opcodes.add(new OPEntry("SUB    A", 0x97, 1));
        opcodes.add(new OPEntry("SBB    B", 0x98, 1));
        opcodes.add(new OPEntry("SBB    C", 0x99, 1));
        opcodes.add(new OPEntry("SBB    D", 0x9a, 1));
        opcodes.add(new OPEntry("SBB    E", 0x9b, 1));
        opcodes.add(new OPEntry("SBB    H", 0x9c, 1));
        opcodes.add(new OPEntry("SBB    L", 0x9d, 1));
        opcodes.add(new OPEntry("SBB    M", 0x9e, 1));
        opcodes.add(new OPEntry("SBB    A", 0x9f, 1));
        opcodes.add(new OPEntry("ANA    B", 0xa0, 1));
        opcodes.add(new OPEntry("ANA    C", 0xa1, 1));
        opcodes.add(new OPEntry("ANA    D", 0xa2, 1));
        opcodes.add(new OPEntry("ANA    E", 0xa3, 1));
        opcodes.add(new OPEntry("ANA    H", 0xa4, 1));
        opcodes.add(new OPEntry("ANA    L", 0xa5, 1));
        opcodes.add(new OPEntry("ANA    M", 0xa6, 1));
        opcodes.add(new OPEntry("ANA    A", 0xa7, 1));
        opcodes.add(new OPEntry("XRA    B", 0xa8, 1));
        opcodes.add(new OPEntry("XRA    C", 0xa9, 1));
        opcodes.add(new OPEntry("XRA    D", 0xaa, 1));
        opcodes.add(new OPEntry("XRA    E", 0xab, 1));
        opcodes.add(new OPEntry("XRA    H", 0xac, 1));
        opcodes.add(new OPEntry("XRA    L", 0xad, 1));
        opcodes.add(new OPEntry("XRA    M", 0xae, 1));
        opcodes.add(new OPEntry("XRA    A", 0xaf, 1));
        opcodes.add(new OPEntry("ORA    B", 0xb0, 1));
        opcodes.add(new OPEntry("ORA    C", 0xb1, 1));
        opcodes.add(new OPEntry("ORA    D", 0xb2, 1));
        opcodes.add(new OPEntry("ORA    E", 0xb3, 1));
        opcodes.add(new OPEntry("ORA    H", 0xb4, 1));
        opcodes.add(new OPEntry("ORA    L", 0xb5, 1));
        opcodes.add(new OPEntry("ORA    M", 0xb6, 1));
        opcodes.add(new OPEntry("ORA    A", 0xb7, 1));
        opcodes.add(new OPEntry("CMP    B", 0xb8, 1));
        opcodes.add(new OPEntry("CMP    C", 0xb9, 1));
        opcodes.add(new OPEntry("CMP    D", 0xba, 1));
        opcodes.add(new OPEntry("CMP    E", 0xbb, 1));
        opcodes.add(new OPEntry("CMP    H", 0xbc, 1));
        opcodes.add(new OPEntry("CMP    L", 0xbd, 1));
        opcodes.add(new OPEntry("CMP    M", 0xbe, 1));
        opcodes.add(new OPEntry("CMP    A", 0xbf, 1));
        opcodes.add(new OPEntry("RNZ    ", 0xc0, 1));
        opcodes.add(new OPEntry("POP    B", 0xc1, 1));
        opcodes.add(new OPEntry("JNZ    ", 0xc2, 3));
        opcodes.add(new OPEntry("JMP    ", 0xc3, 3));
        opcodes.add(new OPEntry("CNZ    ", 0xc4, 3));
        opcodes.add(new OPEntry("PUSH   B", 0xc5, 1));
        opcodes.add(new OPEntry("ADI    ", 0xc6, 2));
        opcodes.add(new OPEntry("RST    0", 0xc7, 1));
        opcodes.add(new OPEntry("RZ     ", 0xc8, 1));
        opcodes.add(new OPEntry("RET    ", 0xc9, 1));
        opcodes.add(new OPEntry("JZ     ", 0xca, 3));
        opcodes.add(new OPEntry("NOP    ", 0xcb, 1));
        opcodes.add(new OPEntry("CZ     ", 0xcc, 3));
        opcodes.add(new OPEntry("CALL   ", 0xcd, 3));
        opcodes.add(new OPEntry("ACI    ", 0xce, 3));
        opcodes.add(new OPEntry("RST    1", 0xcf, 1));
        opcodes.add(new OPEntry("RNC    ", 0xd0, 1));
        opcodes.add(new OPEntry("POP    D", 0xd1, 1));
        opcodes.add(new OPEntry("JNC    ", 0xd2, 3));
        opcodes.add(new OPEntry("OUT    ", 0xd3, 2));
        opcodes.add(new OPEntry("CNC    ", 0xd4, 3));
        opcodes.add(new OPEntry("PUSH   D", 0xd5, 1));
        opcodes.add(new OPEntry("SUI    ", 0xd6, 2));
        opcodes.add(new OPEntry("RST    2", 0xd7, 1));
        opcodes.add(new OPEntry("RC     ", 0xd8, 1));
        opcodes.add(new OPEntry("NOP    ", 0xd9, 1));
        opcodes.add(new OPEntry("JC     ", 0xda, 3));
        opcodes.add(new OPEntry("IN     ", 0xdb, 2));
        opcodes.add(new OPEntry("CC     ", 0xdc, 3));
        opcodes.add(new OPEntry("NOP    ", 0xdd, 1));
        opcodes.add(new OPEntry("SBI    ", 0xde, 2));
        opcodes.add(new OPEntry("RST    3", 0xdf, 1));
        opcodes.add(new OPEntry("RPO    ", 0xe0, 1));
        opcodes.add(new OPEntry("POP    H", 0xe1, 1));
        opcodes.add(new OPEntry("JPO    ", 0xe2, 3));
        opcodes.add(new OPEntry("XTHL   ", 0xe3, 1));
        opcodes.add(new OPEntry("CPO    ", 0xe4, 3));
        opcodes.add(new OPEntry("PUSH   H", 0xe5, 1));
        opcodes.add(new OPEntry("ANI    ", 0xe6, 2));
        opcodes.add(new OPEntry("RST    4", 0xe7, 1));
        opcodes.add(new OPEntry("RPE    ", 0xe8, 1));
        opcodes.add(new OPEntry("CHL    ", 0xe9, 1));
        opcodes.add(new OPEntry("JPE    ", 0xea, 3));
        opcodes.add(new OPEntry("XCHG   ", 0xeb, 1));
        opcodes.add(new OPEntry("CPE    ", 0xec, 3));
        opcodes.add(new OPEntry("NOP    ", 0xed, 1));
        opcodes.add(new OPEntry("XRI    ", 0xee, 2));
        opcodes.add(new OPEntry("RST    5", 0xef, 1));
        opcodes.add(new OPEntry("RP     ", 0xf0, 1));
        opcodes.add(new OPEntry("POP    PSW", 0xf1, 1));
        opcodes.add(new OPEntry("JP     ", 0xf2, 3));
        opcodes.add(new OPEntry("DI     ", 0xf3, 1));
        opcodes.add(new OPEntry("CP     ", 0xf4, 3));
        opcodes.add(new OPEntry("PUSH   PSW", 0xf5, 1));
        opcodes.add(new OPEntry("ORI    ", 0xf6, 2));
        opcodes.add(new OPEntry("RST    6", 0xf7, 1));
        opcodes.add(new OPEntry("RM     ", 0xf8, 1));
        opcodes.add(new OPEntry("SPHL   ", 0xf9, 1));
        opcodes.add(new OPEntry("JM     ", 0xfa, 3));
        opcodes.add(new OPEntry("EI     ", 0xfb, 1));
        opcodes.add(new OPEntry("CM     ", 0xfc, 3));
        opcodes.add(new OPEntry("NOP    ", 0xfd, 1));
        opcodes.add(new OPEntry("CPI    ", 0xfe, 2));
        opcodes.add(new OPEntry("RST    7", 0xff, 1));
    }

    public OPEntry getOPDataFromCode(byte code) {
        for (OPEntry o : opcodes)
            if (o.code == code) return o;
        return null;
    }

    public String disassemble8080p(byte a, byte b, byte c) {
        return disassemble8080p(new byte[]{a, b, c});
    }

    public String disassemble8080p(byte[] codeBuffer) {
        return disassemble8080p(codeBuffer, 0, getOPDataFromCode(codeBuffer[0]).bytes - 1, false);
    }

    public String disassemble8080p(byte[] codeBuffer, int progCounter, int end, boolean hexDump) {
        int pc = progCounter;
        String all = "";

        while (pc <= end) {
            byte c = codeBuffer[pc];

            OPEntry op = getOPDataFromCode(c);

            String finalLine = String.format("%04X ", pc);

            if (op.bytes == 1)
                finalLine += DatatypeConverter.printHexBinary(new byte[]{codeBuffer[pc]}) + "       ";
            else if (op.bytes == 2)
                finalLine += DatatypeConverter.printHexBinary(new byte[]{codeBuffer[pc]}) + " " + DatatypeConverter.printHexBinary(new byte[]{codeBuffer[pc + 1]}) + "    ";
            else if (op.bytes == 3)
                finalLine += DatatypeConverter.printHexBinary(new byte[]{codeBuffer[pc]}) + " " + DatatypeConverter.printHexBinary(new byte[]{codeBuffer[pc + 1]}) + " " + DatatypeConverter.printHexBinary(new byte[]{codeBuffer[pc + 2]}) + " ";

            finalLine += op.name;

            if (op.bytes == 2)
                finalLine += "#0x" + DatatypeConverter.printHexBinary(new byte[]{codeBuffer[pc + 1]});
            else if (op.bytes == 3)
                finalLine += "$" + DatatypeConverter.printHexBinary(new byte[]{codeBuffer[pc + 2], codeBuffer[pc + 1]});

            all += finalLine + ((pc + op.bytes <= end || hexDump) ? "\n" : "");
            pc += op.bytes;
        }

        if (hexDump) {
            int lines = (int) Math.ceil((end - progCounter) / 0x10);
            if (lines == 0) lines++;

            for (int i = 0; i <= lines; i++) {
                all += String.format("%08X ", progCounter + i * 0x10);
                for (int j = 0; j < 0x10; j++)
                    all += " " + DatatypeConverter.printHexBinary(new byte[]{codeBuffer[progCounter + i * 0x10 + j]});
                all += "\n";
            }
        }

        return all;
    }
}
