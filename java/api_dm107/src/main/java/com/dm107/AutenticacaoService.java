package com.dm107;

import java.io.IOException;
import java.sql.Connection;
import java.util.Base64;
import java.util.StringTokenizer;

public class AutenticacaoService {
	
public boolean autenticacao(String credentials) {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();

		try {
			
			Connection conn = connectionFactory.getConnection();
			AutenticacaoDAO auth = new AutenticacaoDAO();
		
			final String dataEncoded = credentials.replaceFirst("Basic" + " ", "");
			String dataUserPass = null;
			
			try {
				byte[] decoder = Base64.getDecoder().decode(dataEncoded);
				dataUserPass = new String(decoder, "UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			final StringTokenizer tokenizer = new StringTokenizer(dataUserPass, ":");
			
			final String user = tokenizer.nextToken();
			final String password = tokenizer.nextToken();
			
			boolean authStatus = auth.autenticado(conn, user, password);
			return authStatus;
			
		} catch (Exception e) {
			
			return false;
			
		}
		
		
		
	}

}
