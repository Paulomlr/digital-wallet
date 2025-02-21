package com.paulomlr.carteira_digital.infra.config.security;

import com.paulomlr.carteira_digital.core.domain.User;
import com.paulomlr.carteira_digital.core.ports.TokenService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenServiceImpl implements TokenService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public TokenServiceImpl(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public String generateToken(User user) {
        Instant now = Instant.now();
        Instant expiration = now.plus(1, ChronoUnit.HOURS);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("digital_wallet")
                .issuedAt(now)
                .expiresAt(expiration)
                .subject(user.getUserId().toString())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public boolean validateToken(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt != null;
    }
}
