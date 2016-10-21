package com.petrovsky.ssta.model;

import org.springframework.security.core.GrantedAuthority;

//@Entity
public class Authority implements GrantedAuthority {

//    @Id
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Authority() {
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority authority1 = (Authority) o;

        return authority.equals(authority1.authority);

    }

    @Override
    public int hashCode() {
        return authority.hashCode();
    }
}
