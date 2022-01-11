package io.github.lizongying;

import org.junit.Assert;
import org.junit.Test;

public class GuaTest {


    @Test
    public void encode() {
        Gua gua = new Gua();
        String encode = gua.encode("hello，世界");
        System.out.println(encode);
        Assert.assertEquals("䷯䷬䷿䷶䷸䷬䷀䷌䷌䷎䷼䷲䷰䷳䷸䷘䷔䷭䷒☯", encode);
    }


    @Test
    public void decode() {
        Gua gua = new Gua();
        String decode = gua.decode("䷯䷬䷿䷶䷸䷬䷀䷌䷌䷎䷼䷲䷰䷳䷸䷘䷔䷭䷒☯");
        System.out.println(decode);
        Assert.assertEquals("hello，世界", decode);
    }
}