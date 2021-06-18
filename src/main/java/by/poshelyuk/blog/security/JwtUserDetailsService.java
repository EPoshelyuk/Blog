package by.poshelyuk.blog.security;

import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public JwtUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        if (user == null) {
            log.error("IN loadUserByUsername - user with email {} not found", email);
            throw new UsernameNotFoundException(String.format("User with email %s not found", email));
        }
        return JwtUserDetails.fromUserToCustomUserDetails(user);
    }
}
