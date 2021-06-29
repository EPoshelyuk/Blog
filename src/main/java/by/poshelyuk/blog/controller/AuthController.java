package by.poshelyuk.blog.controller;

import by.poshelyuk.blog.dto.AuthRequestDto;
import by.poshelyuk.blog.exception.ActivationCodeNotFoundException;
import by.poshelyuk.blog.security.JwtProvider;
import by.poshelyuk.blog.service.PasswordService;
import by.poshelyuk.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordService passwordService;

    @Autowired
    public AuthController(UserService userService, JwtProvider jwtProvider, AuthenticationManager authenticationManager, PasswordService passwordService) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.passwordService = passwordService;
    }

    @PostMapping("/auth")
    public ResponseEntity authResponse(@Valid @RequestBody AuthRequestDto request, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();

        if (getResponseError(bindingResult, response)) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            response.put("Token:", userService.findByEmailAndPassword(request.getEmail(), request.getPassword()));
        } catch (UsernameNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/auth/forgot_password")
    public ResponseEntity forgotPassword(@RequestBody AuthRequestDto authRequestDto) {
        if (passwordService.resetPassword(authRequestDto)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth/reset")
    public ResponseEntity updatePassword(@RequestBody String email,
                                         @RequestBody String code,
                                         @RequestBody String newPassword) throws ActivationCodeNotFoundException {
        if (passwordService.updatePassword(email, code, newPassword)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean getResponseError(BindingResult bindingResult, Map<String, Object> response) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                response.put(error.getField(), error.getDefaultMessage());
            }
            return true;
        }
        return false;
    }
}

