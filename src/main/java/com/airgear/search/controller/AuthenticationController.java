package com.airgear.search.controller;

import com.airgear.entity.AuthToken;
import com.airgear.model.CustomUserDetails;
import com.airgear.search.security.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final TokenProvider jwtTokenUtil;

    @PostMapping(value = "/authenticate")
    public AuthToken login(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return jwtTokenUtil.generateToken(userDetails);
    }
}
