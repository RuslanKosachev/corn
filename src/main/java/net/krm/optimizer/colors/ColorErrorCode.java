package net.krm.optimizer.colors;

public enum ColorErrorCode {

    WRONG_COLOR_STRING("this color does not exist"),
    NULL_COLOR("there is no reference to the object type: " + Color.class.getSimpleName());

    private String errorString;

    private ColorErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}