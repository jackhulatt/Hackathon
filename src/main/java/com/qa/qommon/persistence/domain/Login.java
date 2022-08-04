package com.qa.qommon.persistence.domain;

import java.util.Objects;

public class Login {

private long loginId;
private String username;
private String password;



    public Login() {
    }

    public Login(Long loginId, String username, String password) {
        this.loginId = loginId;
        this.username = username;
        this.password = password;
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public long getLoginId() {
        return this.loginId;
    }

    public void setLoginId(long loginId) {
        this.loginId = loginId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login loginId(int loginId) {
        setLoginId(loginId);
        return this;
    }

    public Login username(String username) {
        setUsername(username);
        return this;
    }

    public Login password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Login)) {
            return false;
        }
        Login login = (Login) o;
        return loginId == login.loginId && Objects.equals(username, login.username) && Objects.equals(password, login.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginId, username, password);
    }

    @Override
    public String toString() {
        return "{" +
            " loginId='" + getLoginId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }
}