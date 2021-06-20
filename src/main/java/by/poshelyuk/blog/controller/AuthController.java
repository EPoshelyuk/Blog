package by.poshelyuk.blog.controller;

import by.poshelyuk.blog.dto.AuthRequest;
import by.poshelyuk.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@Slf4j
@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth")
    public ResponseEntity authResponse(@Valid @RequestBody AuthRequest request, BindingResult
            bindingResult) {
        Map<String, Object> response = new HashMap<>();

        if (getMapResponseError(bindingResult, response)) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            response.put("Token:", userService.findByEmailAndPassword(request.getEmail(), request.getPassword()));
        } catch (UsernameNotFoundException exception) {
            log.error("IN authResponse - {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private boolean getMapResponseError(BindingResult bindingResult, Map<String, Object> response) {
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

