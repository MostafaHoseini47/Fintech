package com.snapp.fintech.web.rest;

import com.snapp.fintech.config.constant.PathUrlConstants;
import com.snapp.fintech.config.metrics.ApiMetrics;
import com.snapp.fintech.config.security.service.AuthenticationService;
import com.snapp.fintech.config.security.service.dto.request.SignUpRequest;
import com.snapp.fintech.config.security.service.dto.request.SigninRequest;
import com.snapp.fintech.config.security.service.dto.response.JwtAuthenticationResponse;
import com.snapp.fintech.service.UserService;
import com.snapp.fintech.service.dto.ChangePasswordDto;
import com.snapp.fintech.service.dto.UserDto;
import com.snapp.fintech.web.rest.model.ModelResponse;
import com.snapp.fintech.web.rest.swagger.UserApi;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PathUrlConstants.USER_CONTROLLER)
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    private final ApiMetrics apiMetrics;

    @PostMapping("/signup")
    @Timed
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    @Timed
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(authenticationService.signin(request));
    }

    @PostMapping("/change-password")
    @Timed
    public ResponseEntity<ModelResponse> changePassword(@RequestBody ChangePasswordDto dto) {
        ModelResponse modelResponse = userService.changePassword(dto);
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(modelResponse);
    }

    @PutMapping
    @Timed
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto dto) {
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    @PostMapping("/deactivate-user/{username}")
    @Timed
    public ResponseEntity<ModelResponse> deactivateUser(@PathVariable("username") String username) {
        ModelResponse modelResponse = userService.deactivateUser(username);
        apiMetrics.incrementApiCallCounter();
        return ResponseEntity.ok(modelResponse);
    }
}
