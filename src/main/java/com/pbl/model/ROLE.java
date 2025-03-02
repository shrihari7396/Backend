package com.pbl.model;

public enum ROLE {
    ADMIN,
    STUDENT;

    public String getAuthority() {
        return this.name();
    }
}
