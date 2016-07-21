package com.slyth.emulator;

import com.slyth.disassembler.Disassembler;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Emulator {

    boolean noPCAdd = false;

    void unimplementedInstruction(State8080 state) {
        System.out.println(String.format("Error: Unimplemented instruction executing 0x%x.", state.memory[state.pc]));
        System.exit(1);
    }

    void writeToHL(State8080 state, byte value) {
        short offset = bytesToShort(state.l, state.h);//(short) ((short) (state.h << 8) | (short) (state.l));
        state.memory[offset] = value;
    }

    byte readFromHL(State8080 state) {
        short offset = bytesToShort(state.l, state.h);//(short) ((short) (state.h << 8) | (short) (state.l));
        return state.memory[offset];
    }

    void flagsZSP(State8080 state, byte value) {
        state.cc.z = (value == 0);
        state.cc.s = (0x80 == (value & 0x80));
        state.cc.p = parity(value, 8);
    }

    void arithFlagsA(State8080 state, short res) {
        state.cc.cy = (res > 0xff);
        state.cc.z = ((res & 0xff) == 0);
        state.cc.s = (0x80 == (res & 0x80));
        state.cc.p = parity(res & 0xff, 8);
    }

    boolean parity(int x, int size) {
        int i;
        int p = 0;
        x = (x & ((1 << size) - 1));
        for (i = 0; i < size; i++) {
            if ((x & 0x1) == 1) p++;
            x = x >> 1;
        }

        return 0 == (p & 0x1);
    }

    public void emulate8080Op(State8080 state, Disassembler disassembler) {
        byte opcode = state.memory[state.pc];
        int intOpCode = opcode & 0xFF;

        System.out.println(disassembler.disassemble8080p(state.memory, state.pc, state.pc, false));

        switch (intOpCode) {
            case 0x00:
                break;
            case 0x01:
                state.c = state.memory[state.pc + 1];
                state.b = state.memory[state.pc + 2];
                break;
            case 0x02:
                unimplementedInstruction(state);
                break;
            case 0x03:
                unimplementedInstruction(state);
                break;
            case 0x04:
                unimplementedInstruction(state);
                break;
            case 0x05:
                state.b--;
                flagsZSP(state, state.b);
                break;
            case 0x06:
                state.b = state.memory[state.pc + 1];
                break;
            case 0x07:
                unimplementedInstruction(state);
                break;
            case 0x08:
                unimplementedInstruction(state);
                break;
            case 0x09:
                int hl = bytesToShort(state.l, state.h);//((short) state.h << 8) | (short) state.l;
                int bc = bytesToShort(state.c, state.b);//((short) state.b << 8) | (short) state.c;
                int res = hl + bc;
                state.h = (byte) ((res & 0xff00) >> 8);
                state.l = (byte) (res & 0xff);
                state.cc.cy = (res & 0xffff0000) != 0;
                break;
            case 0x0a:
                short offset0a = bytesToShort(state.c, state.b);//(short) ((short) (state.b << 8) | (short) (state.c));
                state.a = state.memory[offset0a];
                break;
            case 0x0b:
                unimplementedInstruction(state);
                break;
            case 0x0c:
                unimplementedInstruction(state);
                break;
            case 0x0d:
                state.c--;
                flagsZSP(state, state.c);
                break;
            case 0x0e:
                unimplementedInstruction(state);
                break;
            case 0x0f:
                unimplementedInstruction(state);
                break;
            case 0x10:
                unimplementedInstruction(state);
                break;
            case 0x11:
                state.e = state.memory[state.pc + 1];
                state.d = state.memory[state.pc + 2];
                break;
            case 0x12:
                unimplementedInstruction(state);
                break;
            case 0x13:
                state.e++;
                if (state.e == 0)
                    state.d++;
                break;
            case 0x14:
                unimplementedInstruction(state);
                break;
            case 0x15:
                unimplementedInstruction(state);
                break;
            case 0x16:
                unimplementedInstruction(state);
                break;
            case 0x17:
                unimplementedInstruction(state);
                break;
            case 0x18:
                unimplementedInstruction(state);
                break;
            case 0x19:
                unimplementedInstruction(state);
                break;
            case 0x1a:
                short offset1a = bytesToShort(state.e, state.d);//(short) ((short) (state.d << 8) | (short) (state.e));
                state.a = state.memory[offset1a];
                break;
            case 0x1b:
                unimplementedInstruction(state);
                break;
            case 0x1c:
                unimplementedInstruction(state);
                break;
            case 0x1d:
                unimplementedInstruction(state);
                break;
            case 0x1e:
                unimplementedInstruction(state);
                break;
            case 0x1f:
                unimplementedInstruction(state);
                break;
            case 0x20:
                unimplementedInstruction(state);
                break;
            case 0x21:
                state.h = state.memory[state.pc + 1];
                break;
            case 0x22:
                unimplementedInstruction(state);
                break;
            case 0x23:
                state.l++;
                if (state.l == 0)
                    state.h++;
                break;
            case 0x24:
                state.h++;
                break;
            case 0x25:
                unimplementedInstruction(state);
                break;
            case 0x26:
                unimplementedInstruction(state);
                break;
            case 0x27:
                unimplementedInstruction(state);
                break;
            case 0x28:
                unimplementedInstruction(state);
                break;
            case 0x29:
                unimplementedInstruction(state);
                break;
            case 0x2a:
                unimplementedInstruction(state);
                break;
            case 0x2b:
                unimplementedInstruction(state);
                break;
            case 0x2c:
                unimplementedInstruction(state);
                break;
            case 0x2d:
                unimplementedInstruction(state);
                break;
            case 0x2e:
                unimplementedInstruction(state);
                break;
            case 0x2f:
                unimplementedInstruction(state);
                break;
            case 0x30:
                unimplementedInstruction(state);
                break;
            case 0x31:
                state.sp = bytesToShort(state.memory[state.pc + 1], state.memory[state.pc + 2]);
                break;
            case 0x32:
                unimplementedInstruction(state);
                break;
            case 0x33:
                unimplementedInstruction(state);
                break;
            case 0x34:
                unimplementedInstruction(state);
                break;
            case 0x35:
                unimplementedInstruction(state);
                break;
            case 0x36:
                writeToHL(state, state.memory[state.pc + 1]);
                break;
            case 0x37:
                unimplementedInstruction(state);
                break;
            case 0x38:
                unimplementedInstruction(state);
                break;
            case 0x39:
                unimplementedInstruction(state);
                break;
            case 0x3a:
                unimplementedInstruction(state);
                break;
            case 0x3b:
                unimplementedInstruction(state);
                break;
            case 0x3c:
                unimplementedInstruction(state);
                break;
            case 0x3d:
                unimplementedInstruction(state);
                break;
            case 0x3e:
                unimplementedInstruction(state);
                break;
            case 0x3f:
                unimplementedInstruction(state);
                break;
            case 0x40:
                state.b = state.b; // oh you
                break;
            case 0x41:
                state.b = state.c;
                break;
            case 0x42:
                state.b = state.d;
                break;
            case 0x43:
                state.b = state.e;
                break;
            case 0x44:
                state.b = state.h;
                break;
            case 0x45:
                state.b = state.l;
                break;
            case 0x46:
                state.b = readFromHL(state);
                break;
            case 0x47:
                state.b = state.a;
                break;
            case 0x48:
                state.c = state.b;
                break;
            case 0x49:
                state.c = state.c;
                break;
            case 0x4a:
                state.c = state.d;
                break;
            case 0x4b:
                state.c = state.e;
                break;
            case 0x4c:
                state.c = state.h;
                break;
            case 0x4d:
                state.c = state.l;
                break;
            case 0x4e:
                state.c = readFromHL(state);
                break;
            case 0x4f:
                state.c = state.a;
                break;
            case 0x50:
                state.d = state.b;
                break;
            case 0x51:
                state.d = state.c;
                break;
            case 0x52:
                state.d = state.d;
                break;
            case 0x53:
                state.d = state.e;
                break;
            case 0x54:
                state.d = state.h;
                break;
            case 0x55:
                state.d = state.l;
                break;
            case 0x56:
                state.d = readFromHL(state);
                break;
            case 0x57:
                state.d = state.a;
                break;
            case 0x58:
                state.e = state.b;
                break;
            case 0x59:
                state.e = state.c;
                break;
            case 0x5a:
                state.e = state.d;
                break;
            case 0x5b:
                state.e = state.e;
                break;
            case 0x5c:
                state.e = state.h;
                break;
            case 0x5d:
                state.e = state.l;
                break;
            case 0x5e:
                state.e = readFromHL(state);
                break;
            case 0x5f:
                state.e = state.a;
                break;
            case 0x60:
                state.h = state.b;
                break;
            case 0x61:
                state.h = state.c;
                break;
            case 0x62:
                state.h = state.d;
                break;
            case 0x63:
                state.h = state.e;
                break;
            case 0x64:
                state.h = state.h;
                break;
            case 0x65:
                state.h = state.l;
                break;
            case 0x66:
                state.h = readFromHL(state);
                break;
            case 0x67:
                state.h = state.a;
                break;
            case 0x68:
                state.l = state.b;
                break;
            case 0x69:
                state.l = state.c;
                break;
            case 0x6a:
                state.l = state.d;
                break;
            case 0x6b:
                state.l = state.e;
                break;
            case 0x6c:
                state.l = state.h;
                break;
            case 0x6d:
                state.l = state.l;
                break;
            case 0x6e:
                state.l = readFromHL(state);
                break;
            case 0x6f:
                state.l = state.a;
                break;
            case 0x70:
                writeToHL(state, state.b);
                break;
            case 0x71:
                writeToHL(state, state.c);
                break;
            case 0x72:
                writeToHL(state, state.d);
                break;
            case 0x73:
                writeToHL(state, state.e);
                break;
            case 0x74:
                writeToHL(state, state.h);
                break;
            case 0x75:
                writeToHL(state, state.l);
                break;
            case 0x76:
                break;
            case 0x77:
                writeToHL(state, state.a);
                break;
            case 0x78:
                state.a = state.b;
                break;
            case 0x79:
                state.a = state.c;
                break;
            case 0x7a:
                state.a = state.d;
                break;
            case 0x7b:
                state.a = state.e;
                break;
            case 0x7c:
                state.a = state.h;
                break;
            case 0x7d:
                state.a = state.l;
                break;
            case 0x7e:
                state.a = readFromHL(state);
                break;
            case 0x7f:
                state.a = state.a;
                break;
            case 0x80:
                short answer80 = (short) ((short) state.a + (short) state.b);
                arithFlagsA(state, answer80);
                state.a = (byte) (answer80 & 0xFF);
                break;
            case 0x81:
                short answer81 = (short) ((short) state.a + (short) state.c);
                arithFlagsA(state, answer81);
                state.a = (byte) (answer81 & 0xFF);
                break;
            case 0x82:
                short answer82 = (short) ((short) state.a + (short) state.d);
                arithFlagsA(state, answer82);
                state.a = (byte) (answer82 & 0xFF);
                break;
            case 0x83:
                short answer83 = (short) ((short) state.a + (short) state.e);
                arithFlagsA(state, answer83);
                state.a = (byte) (answer83 & 0xFF);
                break;
            case 0x84:
                short answer84 = (short) ((short) state.a + (short) state.h);
                arithFlagsA(state, answer84);
                state.a = (byte) (answer84 & 0xFF);
                break;
            case 0x85:
                short answer85 = (short) ((short) state.a + (short) state.l);
                arithFlagsA(state, answer85);
                state.a = (byte) (answer85 & 0xFF);
                break;
            case 0x86:
                short answer86 = (short) ((short) state.a + (short) readFromHL(state));
                arithFlagsA(state, answer86);
                state.a = (byte) (answer86 & 0xFF);
                break;
            case 0x87:
                unimplementedInstruction(state);
                break;
            case 0x88:
                unimplementedInstruction(state);
                break;
            case 0x89:
                unimplementedInstruction(state);
                break;
            case 0x8a:
                unimplementedInstruction(state);
                break;
            case 0x8b:
                unimplementedInstruction(state);
                break;
            case 0x8c:
                unimplementedInstruction(state);
                break;
            case 0x8d:
                unimplementedInstruction(state);
                break;
            case 0x8e:
                unimplementedInstruction(state);
                break;
            case 0x8f:
                unimplementedInstruction(state);
                break;
            case 0x90:
                unimplementedInstruction(state);
                break;
            case 0x91:
                unimplementedInstruction(state);
                break;
            case 0x92:
                unimplementedInstruction(state);
                break;
            case 0x93:
                unimplementedInstruction(state);
                break;
            case 0x94:
                unimplementedInstruction(state);
                break;
            case 0x95:
                unimplementedInstruction(state);
                break;
            case 0x96:
                unimplementedInstruction(state);
                break;
            case 0x97:
                unimplementedInstruction(state);
                break;
            case 0x98:
                unimplementedInstruction(state);
                break;
            case 0x99:
                unimplementedInstruction(state);
                break;
            case 0x9a:
                unimplementedInstruction(state);
                break;
            case 0x9b:
                unimplementedInstruction(state);
                break;
            case 0x9c:
                unimplementedInstruction(state);
                break;
            case 0x9d:
                unimplementedInstruction(state);
                break;
            case 0x9e:
                unimplementedInstruction(state);
                break;
            case 0x9f:
                unimplementedInstruction(state);
                break;
            case 0xa0:
                unimplementedInstruction(state);
                break;
            case 0xa1:
                unimplementedInstruction(state);
                break;
            case 0xa2:
                unimplementedInstruction(state);
                break;
            case 0xa3:
                unimplementedInstruction(state);
                break;
            case 0xa4:
                unimplementedInstruction(state);
                break;
            case 0xa5:
                unimplementedInstruction(state);
                break;
            case 0xa6:
                unimplementedInstruction(state);
                break;
            case 0xa7:
                unimplementedInstruction(state);
                break;
            case 0xa8:
                unimplementedInstruction(state);
                break;
            case 0xa9:
                unimplementedInstruction(state);
                break;
            case 0xaa:
                unimplementedInstruction(state);
                break;
            case 0xab:
                unimplementedInstruction(state);
                break;
            case 0xac:
                unimplementedInstruction(state);
                break;
            case 0xad:
                unimplementedInstruction(state);
                break;
            case 0xae:
                unimplementedInstruction(state);
                break;
            case 0xaf:
                unimplementedInstruction(state);
                break;
            case 0xb0:
                unimplementedInstruction(state);
                break;
            case 0xb1:
                unimplementedInstruction(state);
                break;
            case 0xb2:
                unimplementedInstruction(state);
                break;
            case 0xb3:
                unimplementedInstruction(state);
                break;
            case 0xb4:
                unimplementedInstruction(state);
                break;
            case 0xb5:
                unimplementedInstruction(state);
                break;
            case 0xb6:
                unimplementedInstruction(state);
                break;
            case 0xb7:
                unimplementedInstruction(state);
                break;
            case 0xb8:
                unimplementedInstruction(state);
                break;
            case 0xb9:
                unimplementedInstruction(state);
                break;
            case 0xba:
                unimplementedInstruction(state);
                break;
            case 0xbb:
                unimplementedInstruction(state);
                break;
            case 0xbc:
                unimplementedInstruction(state);
                break;
            case 0xbd:
                unimplementedInstruction(state);
                break;
            case 0xbe:
                unimplementedInstruction(state);
                break;
            case 0xbf:
                unimplementedInstruction(state);
                break;
            case 0xc0:
                unimplementedInstruction(state);
                break;
            case 0xc1:
                unimplementedInstruction(state);
                break;
            case 0xc2:
                noPCAdd = true;
                if (!state.cc.z)
                    state.pc = bytesToShort(state.memory[state.pc + 1], state.memory[state.pc + 2]);
                else
                    noPCAdd = false;
                break;
            case 0xc3:
                noPCAdd = true;
                state.pc = bytesToShort(state.memory[state.pc + 1], state.memory[state.pc + 2]);
                break;
            case 0xc4:
                unimplementedInstruction(state);
                break;
            case 0xc5:
                unimplementedInstruction(state);
                break;
            case 0xc6:
                short answerc6 = bytesToShort(state.memory[state.pc + 1], state.a);//(short) ((short) state.a + (short) state.memory[state.pc + 1]);
                flagsZSP(state, (byte) (answerc6 & 0xff));
                state.cc.cy = answerc6 > 0xff;
                state.a = (byte) (answerc6 & 0xFF);
                break;
            case 0xc7:
                unimplementedInstruction(state);
                break;
            case 0xc8:
                unimplementedInstruction(state);
                break;
            case 0xc9:
                noPCAdd = true;
                state.pc = bytesToShort(state.memory[state.sp], state.memory[state.sp + 1]);//(short) ((short) (state.memory[state.sp]) | (short) (state.memory[state.sp + 1] >> 8));
                state.pc++;
                state.sp += 2;
                break;
            case 0xca:
                unimplementedInstruction(state);
                break;
            case 0xcb:
                unimplementedInstruction(state);
                break;
            case 0xcc:
                unimplementedInstruction(state);
                break;
            case 0xcd:
                noPCAdd = true;
                short ret = (short) (state.pc + 2);
                state.memory[state.sp - 1] = (byte) (ret >> 8 & (byte) (0xff));
                state.memory[state.sp - 2] = (byte) (ret & (byte) 0xff);
                state.sp -= 2;
                state.pc = bytesToShort(state.memory[state.pc + 1], state.memory[state.pc + 2]);
                break;
            case 0xce:
                unimplementedInstruction(state);
                break;
            case 0xcf:
                unimplementedInstruction(state);
                break;
            case 0xd0:
                unimplementedInstruction(state);
                break;
            case 0xd1:
                unimplementedInstruction(state);
                break;
            case 0xd2:
                unimplementedInstruction(state);
                break;
            case 0xd3:
                unimplementedInstruction(state);
                break;
            case 0xd4:
                unimplementedInstruction(state);
                break;
            case 0xd5:
                unimplementedInstruction(state);
                break;
            case 0xd6:
                unimplementedInstruction(state);
                break;
            case 0xd7:
                unimplementedInstruction(state);
                break;
            case 0xd8:
                unimplementedInstruction(state);
                break;
            case 0xd9:
                unimplementedInstruction(state);
                break;
            case 0xda:
                unimplementedInstruction(state);
                break;
            case 0xdb:
                unimplementedInstruction(state);
                break;
            case 0xdc:
                unimplementedInstruction(state);
                break;
            case 0xdd:
                unimplementedInstruction(state);
                break;
            case 0xde:
                unimplementedInstruction(state);
                break;
            case 0xdf:
                unimplementedInstruction(state);
                break;
            case 0xe0:
                unimplementedInstruction(state);
                break;
            case 0xe1:
                unimplementedInstruction(state);
                break;
            case 0xe2:
                unimplementedInstruction(state);
                break;
            case 0xe3:
                unimplementedInstruction(state);
                break;
            case 0xe4:
                unimplementedInstruction(state);
                break;
            case 0xe5:
                unimplementedInstruction(state);
                break;
            case 0xe6:
                unimplementedInstruction(state);
                break;
            case 0xe7:
                unimplementedInstruction(state);
                break;
            case 0xe8:
                unimplementedInstruction(state);
                break;
            case 0xe9:
                unimplementedInstruction(state);
                break;
            case 0xea:
                unimplementedInstruction(state);
                break;
            case 0xeb:
                unimplementedInstruction(state);
                break;
            case 0xec:
                unimplementedInstruction(state);
                break;
            case 0xed:
                unimplementedInstruction(state);
                break;
            case 0xee:
                unimplementedInstruction(state);
                break;
            case 0xef:
                unimplementedInstruction(state);
                break;
            case 0xf0:
                unimplementedInstruction(state);
                break;
            case 0xf1:
                unimplementedInstruction(state);
                break;
            case 0xf2:
                unimplementedInstruction(state);
                break;
            case 0xf3:
                unimplementedInstruction(state);
                break;
            case 0xf4:
                unimplementedInstruction(state);
                break;
            case 0xf5:
                unimplementedInstruction(state);
                break;
            case 0xf6:
                unimplementedInstruction(state);
                break;
            case 0xf7:
                unimplementedInstruction(state);
                break;
            case 0xf8:
                unimplementedInstruction(state);
                break;
            case 0xf9:
                unimplementedInstruction(state);
                break;
            case 0xfa:
                unimplementedInstruction(state);
                break;
            case 0xfb:
                unimplementedInstruction(state);
                break;
            case 0xfc:
                unimplementedInstruction(state);
                break;
            case 0xfd:
                unimplementedInstruction(state);
                break;
            case 0xfe:
                byte x = (byte) (state.a - state.memory[state.pc + 1]);
                flagsZSP(state, x);
                state.cc.cy = (state.a < state.memory[state.pc + 1]);
                break;
            case 0xff:
                unimplementedInstruction(state);
                break;
            default:
                unimplementedInstruction(state);
        }

        if (noPCAdd)
            noPCAdd = false;
        else
            state.pc += disassembler.getOPDataFromCode(state.memory[state.pc]).bytes;
    }

    short bytesToShort(byte a, byte b) {
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.put(a);
        bb.put(b);
        return bb.getShort(0);
    }
}
