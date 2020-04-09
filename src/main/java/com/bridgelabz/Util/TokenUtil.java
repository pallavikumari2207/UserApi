package com.bridgelabz.Util;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
@Component
public class TokenUtil {
		private static final String TOKEN_SECRET_CODE = "bridgelabz";

		public String createToken(Long id) {
			String generatedToken = null;
			try {
				generatedToken = JWT.create().withClaim("userId", id).sign(Algorithm.HMAC256(TOKEN_SECRET_CODE));
			} catch (IllegalArgumentException | JWTCreationException e) {
				e.printStackTrace();
			}
			return generatedToken;
		}

		public Long decodeToken(String token) {
			Long userId = (long) 0;
			try {
				Verification verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET_CODE));
				JWTVerifier jwtVerifier = verification.build();
				DecodedJWT decodeJwt = jwtVerifier.verify(token);
				Claim claim = decodeJwt.getClaim("userId");
				userId = claim.asLong();
			} catch (IllegalArgumentException | JWTCreationException e) {
				e.printStackTrace();
			}
			return userId;
		}
	}
