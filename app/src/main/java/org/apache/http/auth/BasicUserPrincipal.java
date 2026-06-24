package org.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import org.apache.http.util.Args;
import org.apache.http.util.LangUtils;

public final class BasicUserPrincipal implements Serializable, Principal {
    private static final long serialVersionUID = -2266305184969850467L;
    private final String username;

    public BasicUserPrincipal(String str) {
        Args.notNull(str, "User name");
        this.username = str;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BasicUserPrincipal) && LangUtils.equals(this.username, ((BasicUserPrincipal) obj).username);
    }

    @Override
    public String getName() {
        return this.username;
    }

    @Override
    public int hashCode() {
        return LangUtils.hashCode(17, this.username);
    }

    @Override
    public String toString() {
        return "[principal: " + this.username + "]";
    }
}
