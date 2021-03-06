package com.app.auth.dao.Role.enums;

public enum RoleEnums {
    EMPLOYEE(1),
    HR(2);

    private final int value;
    private RoleEnums(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
