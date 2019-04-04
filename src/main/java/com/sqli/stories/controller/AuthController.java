package com.sqli.stories.controller;

import com.sqli.stories.helpers.factory.SigninResponseFactory;
import com.sqli.stories.helpers.payload.LoginRequest;
import com.sqli.stories.helpers.payload.MemberAuthenticatedResponse;
import com.sqli.stories.helpers.payload.SigninResponse;
import com.sqli.stories.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/auth/login")
    public ResponseEntity<SigninResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        MemberAuthenticatedResponse response=authService.login(loginRequest);
        SigninResponse signinResponse= SigninResponseFactory.createSigninResponse(response,true);
        if(response==null || response.getLogin().equals(" ") || response.getTeam()==null){
          signinResponse.setSuccess(false);
        }
        return ResponseEntity.ok(signinResponse);
    }


}
