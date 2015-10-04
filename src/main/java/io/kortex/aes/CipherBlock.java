package io.kortex.aes;

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

    @override
    public int hashCode(){
    	return 1;
    }
}
