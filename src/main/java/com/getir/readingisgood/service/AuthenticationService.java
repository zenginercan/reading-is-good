/*
package com.getir.readingisgood.service;

import com.getir.readingisgood.config.CustomerUserDetailService;
import com.getir.readingisgood.config.JwtUtil;
import com.getir.readingisgood.config.UserPrinciple;
import com.getir.readingisgood.model.request.LoginRequest;
import com.getir.readingisgood.model.response.AuthenticationResponse;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
@Slf4j
public class AuthenticationService {

    private final CustomerUserDetailService customerUserDetailService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    private static final String SECRET = "private_token_secret_key";

    public AuthenticationResponse authenticate(LoginRequest loginRequest)  {
*/
/*        Authentication authentication = this.authenticationManager
                .authenticate((Authentication) new UsernamePasswordAuthenticationToken(loginRequest.getMobileNumber(),
                loginRequest.getPassword()));*//*



            try{
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getPhoneNumber(), loginRequest.getPassword())
                );

            }catch (Exception e){
                log.info(e.getMessage());
                e.printStackTrace();
            }


        final UserDetails userDetails = customerUserDetailService.loadUserByUsername(loginRequest.getPhoneNumber());
        //final String jwtToken = generateToken(authentication);
        final String jwt = jwtUtil.generateToken(userDetails);

        //SecurityContextHolder.getContext().setAuthentication(authentication);
        //UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setJwtToken(jwt);
        return authenticationResponse;
*/
/*        return AuthenticationResponse.builder()
                    .jwtToken(generateToken(authentication))
                    .build();*//*

    }

*/
/*    public String generateToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple)authentication.getPrincipal();
        return getAccessToken(userPrinciple.getId(), populateClaims(userPrinciple));
    }*//*


*/
/*    public Claims populateClaims(UserPrinciple userPrinciple) {
        Claims claims = Jwts.claims().setSubject((userPrinciple.getPhoneNumber().toString()));
        return claims;
    }

    public String getAccessToken(String id, Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + Long.valueOf(100000)))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.info("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.info("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.info("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.info("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.info("JWT claims string is empty.");
        }
        return false;
    }*//*

}
*/
