package io.kortex.aes;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.migcomponents.migbase64.Base64;

public class AESEncryption {

    private SecretKeyFactory factory;

    private int numberOfInteractions;
    private int keyLenght;
    private int saltSize;
    private static final int IV_SIZE = 16;

    public AESEncryption(int numberOfInteractions, int keyLenght, int saltSize) throws AESException {
        super();
        try {
            this.factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            this.numberOfInteractions = numberOfInteractions;
            this.keyLenght = keyLenght;
            this.saltSize = saltSize;
        } catch (NoSuchAlgorithmException e) {
            throw new AESException(e);
        }
    }

    private CipherBlock encrypt(String message, String password) throws AESException {

        byte[] salt = getSalt();
        byte[] iv = getIV();
        byte[] output = null;
        try {
            char[] passwordBytes = password.toCharArray();
            byte[] messageBytes = message.getBytes();

            KeySpec keySpec = new PBEKeySpec(passwordBytes, salt, numberOfInteractions, keyLenght);
            SecretKey secretKey = factory.generateSecret(keySpec);
            SecretKey secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            Cipher cipher = getAESCBCPKCS5InitializedInstance(Cipher.ENCRYPT_MODE, secret, ivParameterSpec);

            output = cipher.doFinal(messageBytes);
        } catch (IllegalBlockSizeException e) {
            throw new AESException(e);
        } catch (BadPaddingException e) {
            throw new AESException(e);
        } catch (InvalidKeySpecException e) {
            throw new AESException(e);
        }

        return new CipherBlock(salt, iv, output);

    }

    private Cipher getAESCBCPKCS5InitializedInstance(int cipherMode, SecretKey secret, IvParameterSpec ivParameterSpec) throws AESException {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(cipherMode, secret, ivParameterSpec);

        } catch (InvalidKeyException e) {
            throw new AESException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new AESException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new AESException(e);
        } catch (NoSuchPaddingException e) {
            throw new AESException(e);
        }

        return cipher;
    }

    public CipherBlock encryptBase64(String message, String password) throws AESException {
        CipherBlock encrypted = this.encrypt(message, password);
        byte[] cipherText = Base64.encodeToByte(encrypted.getCipherText(), false);
        byte[] iv = Base64.encodeToByte(encrypted.getIv(), false);
        byte[] salt = Base64.encodeToByte(encrypted.getSalt(), false);
        return new CipherBlock(salt, iv, cipherText);

    }

    private String decrypt(CipherBlock cipherBlock, String password) throws AESException {

        byte[] decryptedBytes = null;

        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(cipherBlock.getIv());

            char[] passwordBytes = password.toCharArray();

            KeySpec keySpec = new PBEKeySpec(passwordBytes, cipherBlock.getSalt(), numberOfInteractions, keyLenght);
            SecretKey tmp;
            tmp = factory.generateSecret(keySpec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = getAESCBCPKCS5InitializedInstance(Cipher.DECRYPT_MODE, secret, ivParameterSpec);

            decryptedBytes = cipher.doFinal(cipherBlock.getCipherText());
        } catch (InvalidKeySpecException e) {

            throw new AESException(e);
        } catch (IllegalBlockSizeException e) {
            throw new AESException(e);
        } catch (BadPaddingException e) {
            throw new AESException(e);
        }
        return new String(decryptedBytes);
    }

    public String decryptBase64(CipherBlock cipherBlock, String password) throws AESException {
        byte[] cipherText = Base64.decode(cipherBlock.getCipherText());
        byte[] iv = Base64.decode(cipherBlock.getIv());
        byte[] salt = Base64.decode(cipherBlock.getSalt());
        CipherBlock basicCipherBlock = new CipherBlock(salt, iv, cipherText);
        return this.decrypt(basicCipherBlock, password);

    }

    private byte[] getSalt() {
        byte[] salt = new byte[this.saltSize];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return salt;
    }

    private byte[] getIV() {
        byte[] iv = new byte[AESEncryption.IV_SIZE];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        return iv;
    }
}