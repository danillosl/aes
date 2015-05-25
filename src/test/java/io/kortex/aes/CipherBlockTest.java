package io.kortex.aes;

import io.kortex.aes.CipherBlock;

import java.security.SecureRandom;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CipherBlockTest {

    private CipherBlock cipherBlock;
    private byte[] salt;
    private byte[] iv;
    private byte[] cipherText;

    @Before
    public void init() {

        iv = this.getRandom();
        salt = this.getRandom();
        cipherText = this.getRandom();

        cipherBlock = new CipherBlock(salt, iv, cipherText);
    }

    @Test
    public void testImmutabilityOfSaltField() {
        byte[] saltRandom = cipherBlock.getSalt();
        saltRandom = getRandom();
        Assert.assertTrue(!Arrays.equals(saltRandom, this.salt) && Arrays.equals(cipherBlock.getSalt(), this.salt));

    }

    @Test
    public void testImmutabilityOfIvField() {
        byte[] ivRandom = cipherBlock.getIv();
        ivRandom = getRandom();
        Assert.assertTrue(!Arrays.equals(ivRandom, this.iv) && Arrays.equals(cipherBlock.getIv(), this.iv));

    }

    @Test
    public void testImmutabilityOfCipherTextField() {
        byte[] cipherTextRandom = cipherBlock.getCipherText();
        cipherTextRandom = getRandom();
        Assert.assertTrue(!Arrays.equals(cipherTextRandom, this.cipherText) && Arrays.equals(cipherBlock.getCipherText(), this.cipherText));

    }

    private byte[] getRandom() {
        byte[] randomBytes = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(randomBytes);
        return randomBytes;
    }
}
