package io.github.lizongying;

import java.nio.charset.StandardCharsets;
import java.util.*;

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
        byte[] guaBytes = gua.getBytes(StandardCharsets.UTF_8);
        gua64list = new String[(int) Math.ceil((double) guaBytes.length / 3)];
        for (int i = 0; i < guaBytes.length; i+=3) {
            String key = new String(guaBytes, i, Math.min(3, guaBytes.length - i), StandardCharsets.UTF_8);
            gua64list[i / 3] = key;
        }

        for (int i = 0; i < gua64list.length; i++) {
            gua64dict.put(gua64list[i], i);
        }
    }

    public String encode(String str) {
        ArrayList<String> out = new ArrayList<>();
        byte[] inByte = str.getBytes(StandardCharsets.UTF_8);
        int strLen = inByte.length;
        int begin;
        for (int i = 0; i < strLen; i += 3) {
            begin = (inByte[i] & 0xff) >> 2;
            out.add(gua64list[begin]);
            if (i == strLen - 1) {
                begin = (inByte[i] & 0xff & 0x3) << 4;
                out.add(gua64list[begin]);
                out.add("〇");
                out.add("〇");
                continue;
            }
            begin = (inByte[i] & 0xff & 0x3) << 4 | (inByte[i + 1] & 0xff) >> 4;
            out.add(gua64list[begin]);
            if (i == strLen - 2) {
                begin = (inByte[i + 1] & 0xff & 0xf) << 2;
                out.add(gua64list[begin]);
                out.add("〇");
                continue;
            }
            begin = (inByte[i + 1] & 0xff & 0xf) << 2 | (inByte[i + 2] & 0xff) >> 6;
            out.add(gua64list[begin]);
            begin = inByte[i + 2] & 0xff & 0x3f;
            out.add(gua64list[begin]);
        }
        return String.join("", out);
    }

    public String decode(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        int[] in = new int[(int) Math.ceil((double) bytes.length / 3)];
        for (int i = 0; i < bytes.length; i += 3) {
            String key = new String(bytes, i, Math.min(3, bytes.length - i), StandardCharsets.UTF_8);
            if (gua64dict.containsKey(key)) {
                in[i / 3] = gua64dict.get(key);
            } else {
                in[i / 3] = 255;
            }
        }
        ArrayList<Byte> outBytes = new ArrayList<>();
        int inLen = in.length;
        for (int i = 0; i < inLen; i += 4) {
            outBytes.add((byte) ((in[i] & 0x3f) << 2 | (in[i + 1] >> 4 & 0x3)));
            if (in[i + 2] != 255) {
                int two = (in[i + 1] & 0xf) << 4 | (in[i + 2] >> 2 & 0xf);
                outBytes.add((byte) two);
            }
            if (in[i + 3] != 255) {
                int three = (in[i + 2] & 0x3) << 6 | (in[i + 3] & 0x3f);
                outBytes.add((byte) three);
            }
        }
        int outSize = outBytes.size();
        byte[] out = new byte[outSize];
        for (int i = 0; i < outSize; i++) {
            out[i] = outBytes.get(i);
        }
        return new String(out, StandardCharsets.UTF_8);
    }

    public boolean verify(String str) {
        Set<Character> set = new HashSet<>();
        for (char c : gua.toCharArray()) {
            set.add(c);
        }
        set.add('〇');

        for (char c : str.toCharArray()) {
            if (!set.contains(c)) {
                return false;
            }
        }
        return true;
    }
}
