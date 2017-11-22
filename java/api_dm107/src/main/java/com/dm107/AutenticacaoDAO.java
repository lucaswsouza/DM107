package com.dm107;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacaoDAO {
	public AutenticacaoDAO () {
		
	}
	
	public boolean autenticado(Connection conn, String user, String password) throws SQLException{
		
		String sql = "select * from usuarios where user = ? and senha = ?";
		PreparedStatement pstm;
		pstm = conn.prepareStatement(sql);
		pstm.setString(1, user);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();
		
		boolean resposta = !rs.next() ? false : true;
		
		return (resposta);
		
	}

}
