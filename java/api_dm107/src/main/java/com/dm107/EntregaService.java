package com.dm107;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Connection;

import javax.ws.rs.Consumes;

@Path("/api")
public class EntregaService {
	
	@POST
	@Path("/entregacad/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(EntregaModel entrega){
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		try {
			
			Connection conn = connectionFactory.getConnection();
			EntregaDAO entregaDAO = new EntregaDAO();
			entregaDAO.inserir(conn, entrega);
			return Response.status(Response.Status.OK).build();
			
		} catch (Exception e) {
			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			
		}
	}
	
	@GET
	@Path("/entregalist/{num}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listEntregaById(@PathParam("num") int numero) {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		EntregaModel entrega = null;
		try {
			
			Connection conn = connectionFactory.getConnection();
			EntregaDAO entregaDAO = new EntregaDAO();
			entrega = entregaDAO.listByNumeroPedido(conn, numero);
			return Response.status(Response.Status.OK).entity(entrega).build();
			
		} catch (Exception e) {
			
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			
		}
	}
		
		
}
