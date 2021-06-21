package by.poshelyuk.blog.security;

import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtUserDetailsService implements UserDetailsService {

    private final UserServiceImpl userService;

    public JwtUserDetailsService(UserServiceImpl userService) {
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
