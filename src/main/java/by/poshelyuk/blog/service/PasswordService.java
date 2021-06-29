package by.poshelyuk.blog.service;

import by.poshelyuk.blog.dto.AuthRequestDto;
import by.poshelyuk.blog.exception.ActivationCodeNotFoundException;

public interface PasswordService {

    boolean resetPassword(AuthRequestDto authRequestDto);

    boolean updatePassword(String email, String code, String newPassword) throws ActivationCodeNotFoundException;
}
