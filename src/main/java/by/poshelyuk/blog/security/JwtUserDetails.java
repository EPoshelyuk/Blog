package by.poshelyuk.blog.security;

import by.poshelyuk.blog.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class JwtUserDetails implements UserDetails {
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static JwtUserDetails fromUserToCustomUserDetails(User user) {
        JwtUserDetails jwtUserDetails = new JwtUserDetails();
        jwtUserDetails.email = user.getEmail();
        jwtUserDetails.password = user.getPassword();
        jwtUserDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
        return jwtUserDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
