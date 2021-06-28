package by.poshelyuk.blog.exception;

public class ActivationCodeNotFoundException extends Exception {

    public ActivationCodeNotFoundException(String code) {
        super(String.format("Activation code %s not found ", code));
    }
}

