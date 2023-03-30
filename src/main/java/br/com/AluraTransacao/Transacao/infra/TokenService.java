package br.com.AluraTransacao.Transacao.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.AluraTransacao.Transacao.model.Usuario;

@Service
public class TokenService {

	public String gerarToken(Usuario usuario) {
		try {
			var algoritimo = Algorithm.HMAC256("123456");
			return JWT.create()
					.withIssuer("API Transacao")
					.withClaim("id", usuario.getId())
					.withExpiresAt(dataExpiracao())
					.sign(algoritimo);
			
		} catch (JWTCreationException exception) {
			throw new RuntimeException("erro ao gerar token JWT", exception);
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
