package io.github.lizongying;

import org.junit.Assert;
import org.junit.Test;

public class GuaTest {


    @Test
    public void encode() {
        Gua gua = new Gua();
        String encode = gua.encode("hello，世界");
        System.out.println(encode);
        Assert.assertEquals("䷯䷬䷿䷶䷸䷬䷀䷌䷌䷎䷼䷲䷰䷳䷸䷘䷔䷭䷒〇", encode);
    }


    @Test
    public void decode() {
        Gua gua = new Gua();
        String decode = gua.decode("䷯䷬䷿䷶䷸䷬䷀䷌䷌䷎䷼䷲䷰䷳䷸䷘䷔䷭䷒〇");
        System.out.println(decode);
        Assert.assertEquals("hello，世界", decode);
    }

    @Test
    public void decode0() {
        Gua gua = new Gua();
        String decode = gua.decode("䷯䷬䷿䷶䷸䷬䷀䷌䷌䷎䷼䷲䷰䷳䷸䷘䷔䷭䷒〇");
        System.out.println(decode);
        Assert.assertEquals("hello，世界", decode);
    }

    @Test
    public void encode1() {
        Gua gua = new Gua();
        String encode = gua.encode("8087:7000");
        System.out.println(encode);
        Assert.assertEquals("䷞䷓䷁䷊䷷䷼䷣䷉䷽䷓䷁䷒", encode);
    }


    @Test
    public void decode1() {
        Gua gua = new Gua();
        String decode = gua.decode("䷞䷓䷁䷊䷷䷼䷣䷉䷽䷓䷁䷒");
        System.out.println(decode);
        Assert.assertEquals("8087:7000", decode);
    }

    @Test
    public void verify0() {
        Gua gua = new Gua();
        Boolean verify = gua.verify("䷯䷬䷿䷶䷸䷬䷀䷌䷌䷎䷼䷲䷰䷳䷸䷘䷔䷭䷒〇");
        System.out.println(verify);
        Assert.assertEquals(true, verify);
    }

    @Test
    public void verify1() {
        Gua gua = new Gua();
        Boolean verify = gua.verify("hello，世界");
        System.out.println(verify);
        Assert.assertEquals(false, verify);
    }
}