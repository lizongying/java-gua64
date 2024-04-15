# 六十四卦编码

六十四卦编码，java实现。
如：“hello，世界”会编码为“䷯䷬䷿䷶䷸䷬䷀䷌䷌䷎䷼䷲䷰䷳䷸䷘䷔䷭䷒〇”。

## all language

* [golang](https://github.com/lizongying/go-gua64)
* [js](https://github.com/lizongying/js-gua64)
* [java](https://github.com/lizongying/java-gua64)
* [php-gua64](https://github.com/lizongying/php-gua64)
* [python](https://github.com/lizongying/pygua64)

## install

[maven](https://mvnrepository.com/artifact/io.github.lizongying/gua64)

* maven
    ```
    <!-- https://mvnrepository.com/artifact/io.github.lizongying/gua64 -->
    <dependency>
        <groupId>io.github.lizongying</groupId>
        <artifactId>gua64</artifactId>
        <version>1.4.3</version>
    </dependency>
    ```

* gradle
    ```
    // https://mvnrepository.com/artifact/io.github.lizongying/gua64
    implementation("io.github.lizongying:gua64:1.4.3")
    ```

## example

```
public class Test {

    public void main(String[] args) {
        Gua gua = new Gua();
        String encode = gua.encode("hello，世界");
        System.out.println(encode);
        String decode = gua.decode("䷯䷬䷿䷶䷸䷬䷀䷌䷌䷎䷼䷲䷰䷳䷸䷘䷔䷭䷒〇");
        System.out.println(decode);
        Boolean verify = gua.verify("䷯䷬䷿䷶䷸䷬䷀䷌䷌䷎䷼䷲䷰䷳䷸䷘䷔䷭䷒〇");
        System.out.println(verify);
    }
}
```