package com.dm107;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntregaDAO {
	
	public void inserir(Connection conn, EntregaModel entrega) throws SQLException{
		String query = "insert into entregas (id, numero_pedido, id_cliente, nome_recebedor, cpf_recebedor, data_entrega) values (?, ?, ?, ?, ?, ?);";
		PreparedStatement pstm;
		
		pstm = conn.prepareStatement(query);
		
		pstm.setInt(1, entrega.getId());
		pstm.setInt(2, entrega.getNumPedido());
		pstm.setInt(3, entrega.getIdCliente());
		pstm.setString(4, entrega.getNomeRecebe());
		pstm.setString(5, entrega.getCpfRecebe());
		pstm.setDate(6, new java.sql.Date(entrega.getDataEntrega().getTime()));
		
		pstm.execute();
	}
	
	
	public EntregaModel listByNumeroPedido(Connection conn, int numPedido) throws SQLException{
		String query = "select * from entregas where numero_pedido = ?";
		
		PreparedStatement pstm;
		
		pstm = conn.prepareStatement(query);
		pstm.setInt(1, numPedido);
		
		ResultSet rs = pstm.executeQuery();

		EntregaModel entrega = new EntregaModel();
		
		while (rs.next())
		{
			int id = rs.getInt("id");
			int numeroPedido = rs.getInt("numero_pedido");
			int idCliente = rs.getInt("id_cliente");
			String nomeRecebedor = rs.getString("nome_recebedor");
			String cpfRecebedor = rs.getString("cpf_recebedor");
			Date dataEntrega = rs.getDate("data_entrega");
			
			entrega = new EntregaModel(id, numeroPedido, idCliente, nomeRecebedor, cpfRecebedor, dataEntrega);
		}
		
		return entrega;
	}

}
