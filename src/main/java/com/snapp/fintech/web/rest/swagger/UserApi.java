package com.snapp.fintech.web.rest.swagger;

import com.snapp.fintech.config.security.service.dto.request.SignUpRequest;
import com.snapp.fintech.config.security.service.dto.request.SigninRequest;
import com.snapp.fintech.config.security.service.dto.response.JwtAuthenticationResponse;
import com.snapp.fintech.service.dto.ChangePasswordDto;
import com.snapp.fintech.service.dto.UserDto;
import com.snapp.fintech.web.rest.model.ModelResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "User", description = "the user Api")
public interface UserApi {

    @Operation(summary = "signUp", description = "sign up and register user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    ResponseEntity<JwtAuthenticationResponse> signup(SignUpRequest request);


    @Operation(summary = "signIn", description = "sign in and input user in application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    ResponseEntity<JwtAuthenticationResponse> signin(SigninRequest request);

    @Operation(summary = "change password", description = "user can change previous password and create new password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<ModelResponse> changePassword(ChangePasswordDto dto);

    @Operation(summary = "update user", description = "user can update their information and save again")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<UserDto> updateUser(UserDto dto);


    @Operation(summary = "deactivate user", description = "deactivate user means user can't do anything in the app")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully operation")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<ModelResponse> deactivateUser(String username);
}
