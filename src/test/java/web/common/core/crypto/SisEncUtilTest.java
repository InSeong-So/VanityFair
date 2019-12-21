package web.common.core.crypto;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import web.common.core.util.SisEncUtil;

@SpringBootTest
public class SisEncUtilTest {

    @Test
    public void test() throws Exception {
        String plainText = "Hello, World!";
        String key = "secret key";

        System.out.println("MD5 : " + plainText + " - " + SisEncUtil.md5(plainText));
        System.out.println("SHA-256 : " + plainText + " - " + SisEncUtil.sha256(plainText));

        String encrypted = SisEncUtil.encryptAES256("Hello, World!", key);
        System.out.println("AES-256 : enc - " + encrypted);
        System.out.println("AES-256 : dec - " + SisEncUtil.decryptAES256(encrypted, key));
    }
}
