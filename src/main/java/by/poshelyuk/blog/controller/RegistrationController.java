package by.poshelyuk.blog.controller;

import by.poshelyuk.blog.entity.User;
import by.poshelyuk.blog.exception.ActivationCodeNotFoundException;
import by.poshelyuk.blog.exception.UserAlreadyExistsException;
import by.poshelyuk.blog.service.RegistrationService;
import by.poshelyuk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RegistrationController {

    private final UserService userService;
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(UserService userService, RegistrationService registrationService) {
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody User user) throws UserAlreadyExistsException {
        registrationService.register(user);
        return new ResponseEntity<>(HttpStatus.OK);
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








