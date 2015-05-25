package io.kortex.aes;

public class AESException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AESException(Throwable cause) {
        super(cause);
    }

    public AESException(String message, Throwable cause) {
        super(message, cause);
    }

}
