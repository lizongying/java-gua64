# 六十四卦

六十四卦代替base64

## install

```
```

## example

```
public class Test {

    public void main(String[] args) {
        Gua gua = new Gua();
        String encode = gua.encode("hello，世界");
        System.out.println(encode);
        String decode = gua.decode("䷯䷬䷿䷶䷸䷬䷀䷌䷌䷎䷼䷲䷰䷳䷸䷘䷔䷭䷒☯");
        System.out.println(decode);
    }
}
```