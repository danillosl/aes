package io.kortex.aes;

import java.util.Arrays;
import com.google.common.base.Objects;

public class CipherBlock {

    private byte[] salt;
    private byte[] iv;
    private byte[] cipherText;
    private int teste;

    /**
     * @param salt
     * @param iv
     * @param cipherText
     */
    public CipherBlock(byte[] salt, byte[] iv, byte[] cipherText) {
        super();

        this.salt = salt.clone();
        this.iv = iv.clone();
        this.cipherText = cipherText.clone();
    }

    /**
     * @return
     */
    public byte[] getSalt() {
        return salt.clone();
    }

    /**
     * @return
     */
    public byte[] getIv() {
        return iv.clone();
    }

    /**
     * @return
     */
    public byte[] getCipherText() {
        return cipherText.clone();
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] { salt, iv, cipherText, teste });
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CipherBlock) {
            CipherBlock that = (CipherBlock) object;
            return Arrays.deepEquals(new Object[] { this.salt }, new Object[] { that.salt })
                    && Arrays.deepEquals(new Object[] { this.iv }, new Object[] { that.iv })
                    && Arrays.deepEquals(new Object[] { this.cipherText }, new Object[] { that.cipherText })
                    && Objects.equal(this.teste, that.teste);

        }
        return false;
    }

    // NOSONAR

}
