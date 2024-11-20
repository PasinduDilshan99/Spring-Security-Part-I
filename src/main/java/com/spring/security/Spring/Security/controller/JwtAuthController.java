//package com.spring.security.Spring.Security.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.Instant;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/v1")
//public class JwtAuthController {
//
//    private JwtEncoder jwtEncoder;
//
//    @Autowired
//    public JwtAuthController(JwtEncoder jwtEncoder) {
//        this.jwtEncoder = jwtEncoder;
//    }
//
//    record JwtResponse(String token) {
//
//    }
//
//    @PostMapping(path = "/auth")
//    public JwtResponse authenticate(Authentication authentication) {
//        return new JwtResponse(createToken(authentication));
//    }
//
//    private String createToken(Authentication authentication) {
//        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
//                .issuer("self")
//                .issuedAt(Instant.now())
//                .expiresAt(Instant.now().plusSeconds(60 * 30))
//                .subject(authentication.getName())
//                .claim("scope", createScope(authentication))
//                .build();
//        JwtEncoderParameters parameters = JwtEncoderParameters.from(jwtClaimsSet);
//        return jwtEncoder.encode(parameters).getTokenValue();
//    }
//
//    private String createScope(Authentication authentication) {
//        return authentication.getAuthorities()
//                .stream()
//                .map(a -> a.getAuthority())
//                .collect(Collectors.joining(" "));
//    }
//}
