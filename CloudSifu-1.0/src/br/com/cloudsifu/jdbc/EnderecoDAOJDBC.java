package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Bairro;
import br.com.cloudsifu.objects.Endereco;
import br.com.cloudsifu.services.BairroServico;
import br.com.cloudsifu.jdbc.AcessaDB;

public class EnderecoDAOJDBC implements EnderecoDAO {
	private AcessaDB acessaDB;
	private Connection connect;
	private BairroServico bairroServico;
	
	public EnderecoDAOJDBC() {
		acessaDB = new AcessaDB();
		connect = acessaDB.criaConexao();
		bairroServico = new BairroServico();
	}

	private Endereco montarEndereco(Endereco endereco) throws GlobalException{
		if(endereco != null){
		 	endereco.setBairro(bairroServico.buscarBairroId(endereco.getBairro()));
		}
		return endereco;
	}
	public void adicionarEndereco(Endereco endereco) throws GlobalException {

		PreparedStatement p;
		String comando = "INSERT INTO `Endereco` "
				+ "(cep, logradouro, nome, numero, bairro, referencia)"
				+ "VALUES(?,?,?,?,?,?)";

		try {
			bairroServico.adicionarBairro(endereco.getBairro());
			endereco.setBairro(bairroServico.buscarBairro(endereco.getBairro()));
			p = connect.prepareStatement(comando);
			p.setInt(1, endereco.getCep());
			p.setInt(2, endereco.getLogradouro());
			p.setString(3, endereco.getNome());
			p.setInt(4, endereco.getNumero());
			p.setInt(5, endereco.getBairro().getId());
			p.setString(6, endereco.getReferencia());
			p.execute();
			acessaDB.fechaConexao();
			
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException(" Adicionar endereco", e );

		}
	}

	
	public List<Endereco> buscarEnderecoLista(Endereco endereco)
			throws GlobalException {
		List<Endereco> enderecoLista = null;
		String comando = "SELECT * FROM Endereco, Cidade, Bairro, UnidadeFederativa";

		if (!endereco.equals("null") && !endereco.equals("")) {
			comando += " WHERE Cidade.nome LIKE '"
					+ endereco.getBairro().getCidade().getNome()
					+ "' AS cidade " + " AND Bairro.nome LIKE '%"
					+ endereco.getBairro().getNome() + "%' as bairro"
					+ " AND  Endereco.nome LIKE '%" + endereco.getNome()
					+ "%'  as nome" + " AND Cep.numero LIKE '%"
					+ endereco.getCep() + "%' as cep"
					+ " AND CONCAT(tipo,' ',nome) LIKE '% as endereco"
					+ endereco.getLogradouro() + " " + endereco.getNome()
					+ "%'";
		}

		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				if(rs.first()){
					enderecoLista = new ArrayList<Endereco>();
				}	
				endereco.setId(rs.getInt("id"));
				endereco.setCep(rs.getInt("cep"));
				endereco.setLogradouro(rs.getInt("logradouro"));
				endereco.setNome(rs.getString("nome"));
				endereco.setReferencia(rs.getString("referencia"));
				endereco.setNumero(rs.getInt("numero"));
				endereco.setBairro(new Bairro());
				endereco.getBairro().setId(rs.getInt("bairro"));
				enderecoLista.add(montarEndereco(endereco));
			}
			acessaDB.fechaConexao();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar lista de enderecos", e);

		}
		return enderecoLista;
	}

	
	public boolean excluirEndereco(Endereco endereco) throws GlobalException {

		String comando = "DELETE  FROM Endereco WHERE id =" + endereco.getId();
		Statement p;

		try {
			p = connect.createStatement();
			p.execute(comando);
			acessaDB.fechaConexao();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("excluir endereco", e);
		}
		return true;

	}

	
	public boolean editarEndereco(Endereco endereco) throws GlobalException {
		adicionarEndereco(endereco);		
				/*
			String comando = "UPDATE Endereco SET cep=?, bairro=?, nome=?"
				+ ", referencia=?, numero=? WHERE id=" + endereco.getId();

		PreparedStatement p;

		try {

			p = connect.prepareStatement(comando);
			p.setInt(1, endereco.getCep());
			p.setInt(2, endereco.getBairro().getId());
			p.setString(3, endereco.getNome());
			p.setString(4, endereco.getReferencia());
			p.setInt(5, endereco.getNumero());			
			p.execute();
			acessaDB.fechaConexao();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("editar endereco", e);

		}
		*/
		return true;

	}

	public Endereco buscarEnderecoId(Endereco busca) throws GlobalException {
		String comando = "SELECT * FROM Endereco WHERE id =" + busca.getId();
		Endereco endereco =  new Endereco();
		try {

			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				endereco.setId(rs.getInt("id"));
				endereco.setCep(rs.getInt("cep"));
				endereco.setLogradouro(rs.getInt("logradouro"));
				endereco.setNome(rs.getString("nome"));
				endereco.setReferencia(rs.getString("referencia"));
				endereco.setNumero(rs.getInt("numero"));
				endereco.setBairro(new Bairro());
				endereco.getBairro().setId(rs.getInt("bairro"));
			}
			acessaDB.fechaConexao();
		} catch (Throwable e) {

			e.printStackTrace();
			throw new GlobalException("buscar endereco por id", e);
		}
		return montarEndereco(endereco);
	}

	
	public Endereco buscarEndereco(Endereco busca) throws GlobalException {
		Endereco endereco = null;
		String comando = " SELECT * FROM Endereco, Bairro, Cidade, UnidadeFederativa" 
				+ " WHERE"
				+ " Endereco.id = '"+ busca.getId()+"'"
				+ " OR ("
				+ " Endereco.cep ='" + busca.getCep() + "'"
				+ " AND"
				+ " Endereco.numero ='" + busca.getNumero() + "')";
			
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				endereco = new Endereco();
				endereco.setId(rs.getInt("id"));
				endereco.setCep(rs.getInt("cep"));
				endereco.setLogradouro(rs.getInt("logradouro"));
				endereco.setNome(rs.getString("nome"));
				endereco.setNumero(rs.getInt("numero"));
				endereco.setReferencia(rs.getString("referencia"));
				endereco.setBairro(new Bairro());
				endereco.getBairro().setId(rs.getInt("bairro"));
			}
			acessaDB.fechaConexao();
			
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar endereco", e);
		}
		return montarEndereco(endereco);
	}

}// END CLASS