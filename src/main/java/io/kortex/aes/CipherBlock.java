package io.kortex.aes;

import java.util.Arrays;

public class CipherBlock {

    private byte[] salt;
    private byte[] iv;
    private byte[] cipherText;

    public CipherBlock(byte[] salt, byte[] iv, byte[] cipherText) {
        super();

        this.salt = salt.clone();
        this.iv = iv.clone();
        this.cipherText = cipherText.clone();
    }

    public byte[] getSalt() {
        return salt.clone();
    }

    public byte[] getIv() {
        return iv.clone();
    }

    public byte[] getCipherText() {
        return cipherText.clone();
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] { salt, iv, cipherText });
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CipherBlock) {
            CipherBlock that = (CipherBlock) object;
            return Arrays.deepEquals(new Object[] { this.salt }, new Object[] { that.salt })
                    && Arrays.deepEquals(new Object[] { this.iv }, new Object[] { that.iv })
                    && Arrays.deepEquals(new Object[] { this.cipherText }, new Object[] { that.cipherText });
        }
        return false;
    }

}
