package io.kortex.aes;

import io.kortex.aes.AESEncryption;
import io.kortex.aes.AESException;
import io.kortex.aes.CipherBlock;

import org.junit.Assert;
import org.junit.Test;

public class EncriptionTest {

    private String message = "danillo";
    private String randomSecret = "test";

    private AESEncryption aesEncryption;

    @Test
    public void testingEncryptionAndDecription128BitWithBase64() throws AESException {

        aesEncryption = new AESEncryption(65536, 128, 32);

        CipherBlock cipherBlock = aesEncryption.encryptBase64(message, randomSecret);

        String decrypt = aesEncryption.decryptBase64(cipherBlock, randomSecret);
        Assert.assertEquals(message, decrypt);
    }

    @Test
    public void testingEncryptionAndDecription256BitWithBase64() throws AESException {

        aesEncryption = new AESEncryption(65536, 256, 32);

        CipherBlock cipherBlock = aesEncryption.encryptBase64(message, randomSecret);

        String decrypt = aesEncryption.decryptBase64(cipherBlock, randomSecret);
        Assert.assertEquals(message, decrypt);
    }

}
