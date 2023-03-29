package br.com.AluraTransacao.Transacao.util;

import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class Gerador {

	public Integer gerar() {
		return new Random().nextInt(100000, 999999);
	}
	
	public String codificar(Integer value) {
		return BCrypt.hashpw(value.toString(), BCrypt.gensalt(6));
	}
}
