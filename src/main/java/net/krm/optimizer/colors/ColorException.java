package net.krm.optimizer.colors;

public class ColorException extends Exception  {

    ColorErrorCode errorCode;

    public ColorException(ColorErrorCode errorCode, Throwable cause) {
        super(errorCode.getErrorString(), cause);
        this.errorCode = errorCode;
    }

    public ColorException(ColorErrorCode errorCode) {
        super(errorCode.getErrorString());
        this.errorCode = errorCode;
    }

    public ColorException(ColorErrorCode errorCode, String colorValue) {
        super(errorCode.getErrorString() + ": " + colorValue);
        this.errorCode = errorCode;
    }

    public ColorException(ColorErrorCode errorCode, String colorValue, Throwable cause) {
        super(errorCode.getErrorString() + ": " + colorValue, cause);
        this.errorCode = errorCode;
    }

    public ColorErrorCode getErrorCode() {
        return errorCode;
    }
}
