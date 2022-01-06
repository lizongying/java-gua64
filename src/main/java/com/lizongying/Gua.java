package com.lizongying;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Gua {
    String gua = "䷁䷖䷇䷓䷏䷢䷬䷋" +
            "䷎䷳䷦䷴䷽䷷䷞䷠" +
            "䷆䷃䷜䷺䷧䷿䷮䷅" +
            "䷭䷑䷯䷸䷟䷱䷛䷫" +
            "䷗䷚䷂䷩䷲䷔䷐䷘" +
            "䷣䷕䷾䷤䷶䷝䷰䷌" +
            "䷒䷨䷻䷼䷵䷥䷹䷉" +
            "䷊䷙䷄䷈䷡䷍䷪䷀";

    String[] gua64list;
    Map<String, Integer> gua64dict = new HashMap<>();

    public Gua() {
        this.gua64list = gua.split("");
        for (int i = 0; i < 64; i++) {
            this.gua64dict.put(this.gua64list[i], i);
        }
    }

    public String encode(String str) {
        ArrayList<String> out = new ArrayList<>();
        byte[] inByte = str.getBytes(StandardCharsets.UTF_8);
        int strLen = inByte.length;
        int begin;
        for (int i = 0; i < strLen; i += 3) {
            begin = (inByte[i] & 0xff) >> 2;
            out.add(this.gua64list[begin]);
            if (i == strLen - 1) {
                begin = (inByte[i] & 0xff & 0x3) << 4;
                out.add(this.gua64list[begin]);
                out.add("☯");
                out.add("☯");
                continue;
            }
            begin = (inByte[i] & 0xff & 0x3) << 4 | (inByte[i + 1] & 0xff) >> 4;
            out.add(this.gua64list[begin]);
            if (i == strLen - 2) {
                begin = (inByte[i + 1] & 0xff & 0xf) << 2;
                out.add(this.gua64list[begin]);
                out.add("☯");
                continue;
            }
            begin = (inByte[i + 1] & 0xff & 0xf) << 2 | (inByte[i + 2] & 0xff) >> 6;
            out.add(this.gua64list[begin]);
            begin = inByte[i + 2] & 0xff & 0x3f;
            out.add(this.gua64list[begin]);
        }
        return String.join("", out);
    }

    public String decode(String str) {
        int strLen = str.length();
        int[] in = new int[strLen];
        for (int i = 0; i < strLen; i++) {
            in[i] = this.gua64dict.getOrDefault(String.valueOf(str.charAt(i)), 0);
        }
        ArrayList<Byte> outBytes = new ArrayList<>();
        int inLen = in.length;
        for (int i = 0; i < inLen; i += 4) {
            outBytes.add((byte) ((in[i] & 0x3f) << 2 | (in[i + 1] >> 4 & 0x3)));
            int two = (in[i + 1] & 0xf) << 4 | (in[i + 2] >> 2 & 0xf);
            if (two != 0) {
                outBytes.add((byte) two);
            }
            int three = (in[i + 2] & 0x3) << 6 | (in[i + 3] & 0x3f);
            if (three != 0) {
                outBytes.add((byte) three);
            }
        }
        int outSize = outBytes.size();
        byte[] out = new byte[outSize];
        for (int i = 0; i < outSize; i++) {
            out[i] = outBytes.get(i);
        }
        return new String(out);
    }
}
