package com.dm107;

import java.sql.Date;

public class EntregaModel {
	
	private int id;
	private int numPedido;
	private int idCliente;
	private String nomeRecebe;
	private String cpfRecebe;
	private Date dataEntrega;
	
	public EntregaModel(){
		
	}
	
	public EntregaModel(int id, int numPedido, int idCliente, String nomeRecebe, String cpfRecebe, Date dataEntrega) {
		super();
		this.id = id;
		this.numPedido = numPedido;
		this.idCliente = idCliente;
		this.nomeRecebe = nomeRecebe;
		this.cpfRecebe = cpfRecebe;
		this.dataEntrega = dataEntrega;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumPedido() {
		return numPedido;
	}
	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomeRecebe() {
		return nomeRecebe;
	}
	public void setNomeRecebe(String nomeRecebe) {
		this.nomeRecebe = nomeRecebe;
	}
	public String getCpfRecebe() {
		return cpfRecebe;
	}
	public void setCpfRecebe(String cpfRecebe) {
		this.cpfRecebe = cpfRecebe;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

}
