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
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(cipherText);
        result = prime * result + Arrays.hashCode(iv);
        result = prime * result + Arrays.hashCode(salt);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CipherBlock other = (CipherBlock) obj;
        if (!Arrays.equals(cipherText, other.cipherText))
            return false;
        if (!Arrays.equals(iv, other.iv))
            return false;
        if (!Arrays.equals(salt, other.salt))
            return false;
        return true;
    }

}
