package by.poshelyuk.blog.controller;

import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.exception.ActivationCodeNotFoundException;
import by.poshelyuk.blog.exception.UserAlreadyExistsException;
import by.poshelyuk.blog.service.EmailService;
import by.poshelyuk.blog.service.RegistrationService;
import by.poshelyuk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
public class RegistrationController {

    private String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    private final UserService userService;
    private final RegistrationService registrationService;

    public RegistrationController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }


    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody User user) throws UserAlreadyExistsException {
//        Map<String, Object> response = new HashMap<>();
//        String regex = EMAIL_REGEX;
//        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(user.getEmail());
//        if (matcher.matches() || !ObjectUtils.isEmpty(user.getPassword())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } else {
            registrationService.register(user);
            return new ResponseEntity<>(HttpStatus.OK);
       // }
    }

    @GetMapping("/auth/confirm/{email}/{activationCode}")
    public ResponseEntity<User> activateCode(@PathVariable String email, @PathVariable String activationCode)
            throws ActivationCodeNotFoundException, UserAlreadyExistsException {
        boolean checkActivationCode = registrationService.checkActivationCode(email, activationCode);
        if (checkActivationCode = true) {
            User user = userService.findByEmail(email);
            user.setEnabled(true);
            userService.saveUser(user);
            user.setEnabled(true);
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}








