package com.parkingwang.messages;

public class MessageResult {

    public final boolean passed;
    public final String message;

    private MessageResult(boolean passed, String message) {
        this.passed = passed;
        this.message = message;
    }

    public static MessageResult accept(String message) {
        return new MessageResult(true, message);
    }

    public static MessageResult reject() {
        return new MessageResult(false, null);
    }
}