package com.app.auth.constant.enums.ApplicationWorkFlow;

public enum ApplicationWorkFlowOnboardingEnums {
    PROCESSING(0, "Processing"),
    WAITING(1, "Waiting"),
    APPROVE(2, "Approve");

    private final int value;
    private final String str;

    ApplicationWorkFlowOnboardingEnums(int value, String str) {
        this.value = value;
        this.str = str;
    }

    public int getValue() {
        return value;
    }

    public String getStr() {
        return str;
    }
}
