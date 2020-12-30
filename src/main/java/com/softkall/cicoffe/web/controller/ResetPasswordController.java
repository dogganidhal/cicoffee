package com.softkall.cicoffe.web.controller;
import com.softkall.cicoffe.model.entity.ResetCode;
import com.softkall.cicoffe.model.repository.ResetCodeRepository;
import com.softkall.cicoffe.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@AllArgsConstructor
@RequestMapping("/content")
public class ResetPasswordController {

    private final ResetCodeRepository resetCodeRepository;
    private final AuthService authService;

    @GetMapping("/reset-password")
    public String showChangePasswordPage(Model model, @RequestParam String code) {

        Optional<ResetCode> resetCode = resetCodeRepository.findByCode(code);

        if (resetCode.isEmpty()) {
            return "wrong-reset-password";
        }

        model.addAttribute("code", resetCode.get().getCode());

        return "reset-password";

    }

    @PostMapping("/reset-password")
    public String updatePassword(@Valid @ModelAttribute("request") UpdatePasswordRequestDto request) {

        Optional<ResetCode> resetCode = resetCodeRepository.findByCode(request.getCode());

        if (resetCode.isEmpty()) {
            return "wrong-reset-password";
        }

        authService.resetPassword(resetCode.get().getMember(), request.getPassword());

        return "reset-password-successful";

    }

    @Data
    static class UpdatePasswordRequestDto {
        private String code;
        private String password;
    }

}
