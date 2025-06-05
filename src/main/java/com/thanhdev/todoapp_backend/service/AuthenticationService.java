package com.thanhdev.todoapp_backend.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.thanhdev.todoapp_backend.dto.request.AuthenticationRequest;
import com.thanhdev.todoapp_backend.dto.request.IntrospectRequest;
import com.thanhdev.todoapp_backend.dto.response.AuthenticationResponse;
import com.thanhdev.todoapp_backend.dto.response.IntrospectResponse;
import com.thanhdev.todoapp_backend.entity.Users;
import com.thanhdev.todoapp_backend.exception.AppException;
import com.thanhdev.todoapp_backend.exception.ErrorCode;
import com.thanhdev.todoapp_backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

	UserRepository userRepository;

	@NonFinal
	@Value("${jwt.signerKey}")
	protected String SIGNER_KEY;

	public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
		var token = request.getToken();

		JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

		SignedJWT signedJWT = SignedJWT.parse(token);

		Date expiryTime = signedJWT.getJWTClaimsSet()
		                           .getExpirationTime();

		var verified = signedJWT.verify(verifier);

		return IntrospectResponse.builder()
		                         .isValid(verified && expiryTime.after(new Date()))
		                         .build();
	}

	public AuthenticationResponse authenticated(AuthenticationRequest request) {
		var user = userRepository.findByUsername(request.getUsername())
		                         .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

		boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

		if (!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);

		var token = generateToken(user);

		return AuthenticationResponse.builder()
		                             .token(token)
		                             .isAuthenticated(true)
		                             .build();
	}

	private String generateToken(Users users) {
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

		JWTClaimsSet jwtClaimSet = new JWTClaimsSet.Builder().subject(users.getUsername())
		                                                     .issuer("thanhdev.com")
		                                                     .issueTime(new Date())
		                                                     .expirationTime(new Date(Instant.now()
		                                                                                     .plus(1, ChronoUnit.HOURS)
		                                                                                     .toEpochMilli()))
		                                                     .claim("userId", users.getId())
		                                                     .claim("scope", buildScope(users))
		                                                     .build();

		Payload payload = new Payload(jwtClaimSet.toJSONObject());
		JWSObject jwsObject = new JWSObject(header, payload);

		try {
			jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
			return jwsObject.serialize();
		} catch (JOSEException e) {
			log.error("Cannot create token: ", e);
			throw new RuntimeException(e);
		}
	}

	private String buildScope(Users users) {
		StringJoiner stringJoiner = new StringJoiner(" ");
		if (!CollectionUtils.isEmpty(users.getRoles())) {
			users.getRoles()
			     .forEach(stringJoiner::add);
		}

		return stringJoiner.toString();
	}
}
