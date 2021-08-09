/*
package com.getir.readingisgood.config;

import com.sun.security.auth.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private CustomerUserDetailService customerUserDetailService;

    private static final String SECRET = "private_token_secret_key";



    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = extractTokenFromRequest(httpServletRequest);
        if (validateToken(jwtToken)) {
            Long phoneNumber = extractMobileNoFromToken(jwtToken);
            UserPrinciple userPrinciple = this.customerUserDetailService.loadUserByPhoneNumber(phoneNumber);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userPrinciple, null, userPrinciple.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails((new WebAuthenticationDetailsSource()).buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    public String getAccessToken(String id, Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + Long.valueOf(100000)))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

*/
/*
    public Claims populateClaims(UserPrincipal userPrincipal) {
        Claims claims = Jwts.claims().setSubject((userPrincipal.getMobileNumber().toString()));
        claims.put("scopes", userPrincipal.getAuthorities().stream().map(Object::toString).collect(Collectors.toList()));
        return claims;
    }*//*


    public Long extractMobileNoFromToken(String token) {
        return Long.valueOf(Long.parseLong(getClaimsByToken(token).getSubject()));
    }

    String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return extractTokenFromAuthorizationHeader(bearerToken);
    }

    private Claims getClaimsByToken(String token) {
        return (Claims)Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
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
    }

    public String extractTokenFromAuthorizationHeader(String authorizationHeaderValue) {
        return authorizationHeaderValue.split(" ")[1];
    }
}
*/
