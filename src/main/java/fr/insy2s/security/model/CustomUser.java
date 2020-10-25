package fr.insy2s.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    private Long userId;
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                      Long userId) {
        super(username, password, authorities);
        this.userId = userId;
    }

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean
        credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public Long getUserId() {
        return this.userId;
    }
}
