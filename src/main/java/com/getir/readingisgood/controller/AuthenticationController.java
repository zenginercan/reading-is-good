/*
package com.getir.readingisgood.controller;

import com.getir.readingisgood.model.request.CreateCustomerRequest;
import com.getir.readingisgood.model.request.LoginRequest;
import com.getir.readingisgood.model.response.AuthenticationResponse;
import com.getir.readingisgood.model.response.WebResponse;
import com.getir.readingisgood.service.AuthenticationService;
import com.getir.readingisgood.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path="/user")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final CustomerService customerService;

    @PostMapping(path="/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(this.authenticationService
                .authenticate(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<WebResponse> registerUser(@RequestBody @Valid CreateCustomerRequest request) {
        return ResponseEntity.ok(this.customerService.createCustomer(request));
    }

}
*/
