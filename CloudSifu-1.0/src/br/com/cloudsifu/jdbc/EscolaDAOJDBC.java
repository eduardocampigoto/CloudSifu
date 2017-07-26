package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Endereco;
import br.com.cloudsifu.objects.Escola;
import br.com.cloudsifu.services.EnderecoServico;
import br.com.cloudsifu.test.JDBCTeste;

public class EscolaDAOJDBC implements EscolaDAO {

	private AcessaDB acessaDB;
	private Connection connect;
	private EnderecoServico enderecoServico;
	private JDBCTeste jdbcTeste;

	public EscolaDAOJDBC() {
		acessaDB = new AcessaDB();
		connect = acessaDB.criaConexao();
		enderecoServico = new EnderecoServico();
		jdbcTeste = new JDBCTeste();
	}

	public Escola montarEscola(Escola escola) throws GlobalException{
		if(escola != null){
			escola.setEndereco(enderecoServico.buscarEnderecoId(escola.getEndereco()));
		}
		return escola;
	}
	
	public void adicionarEscola(Escola escola) throws GlobalException {
		if(escola == null){
			return;
		}
		enderecoServico.adicionarEndereco(escola.getEndereco());
		escola.setEndereco(enderecoServico.buscarEndereco(escola.getEndereco()));
		String comando = "INSERT INTO Escola "
				+ "(nome, responsavel, email, telefone, endereco) VALUES(?,?,?,?,?)";
		PreparedStatement p;

		try {

			p = connect.prepareStatement(comando);
			p.setString(1, escola.getNome());
			p.setString(2, escola.getResponsavel());
			p.setString(3, escola.getEmail());
			p.setLong(4, escola.getTelefone());
			p.setInt(5, escola.getEndereco().getId());
			p.execute();
			acessaDB.fechaConexao();

		} catch (Exception e) {
			throw new GlobalException("adicionar escola", e);
		}
	}

	public boolean editarEscola(Escola escola) throws GlobalException {
		enderecoServico.editarEndereco(escola.getEndereco());
		escola.setEndereco(enderecoServico.buscarEndereco(escola.getEndereco()));

		String comando = "UPDATE Escola SET nome=?, responsavel=?, email=?, telefone=?, endereco=? WHERE id="
				+ escola.getId();
		PreparedStatement p;

		try {

			p = connect.prepareStatement(comando);

			p.setString(1, escola.getNome());
			p.setString(2, escola.getResponsavel());
			p.setString(3, escola.getEmail());
			p.setLong(4, escola.getTelefone());
			p.setInt(5, escola.getEndereco().getId());
			p.execute();
			acessaDB.fechaConexao();
		} catch (Exception e) {
			throw new GlobalException("editar escola", e);
		}
		
		return true;

	}

	public Escola buscarEscolaId(Escola busca) throws GlobalException {

		Escola escola = null;
		String comando = "SELECT * FROM Escola WHERE id =" + busca.getId();
		jdbcTeste.mostraQuerie(comando, "EscolaDAOJDBC", "buscarEscolaId");

		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				escola = new Escola();
				escola.setId(rs.getInt("id"));
				escola.setNome(rs.getString("nome"));
				escola.setResponsavel(rs.getString("responsavel"));
				escola.setEmail(rs.getString("email"));
				escola.setTelefone(rs.getLong("telefone"));
				escola.setEndereco(new Endereco());
				escola.getEndereco().setId((rs.getInt("endereco")));
			}
			acessaDB.fechaConexao();
		} catch (Exception e) {
			throw new GlobalException("buscar escola por ID", e);
		}
		
		return montarEscola(escola);
	}

	public Escola buscarEscola(Escola busca) throws GlobalException {
		Escola escola = null;
		String comando = "SELECT * FROM Escola" + " WHERE"
				+ " Escola.nome ='" + busca.getNome() + "'"
				+ " AND"
				+ " Escola.endereco ='" + busca.getEndereco().getId()+ "'";
		try {

			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				escola = new Escola();
				escola.setId(rs.getInt("id"));
				escola.setNome(rs.getString("nome"));
				escola.setResponsavel(rs.getString("responsavel"));
				escola.setEmail(rs.getString("email"));
				escola.setTelefone(rs.getLong("telefone"));
				escola.setEndereco(new Endereco());
				escola.getEndereco().setId((rs.getInt("endereco")));
			}
			acessaDB.fechaConexao();
		
		} catch (Exception e) {
			throw new GlobalException("buscar escola", e);
		}
		return montarEscola(escola);
	}

	public List<Escola> buscarEscolaLista(Escola busca) throws GlobalException {
		List<Escola> escolaLista = null;
		String comando = "SELECT * FROM Escola";
		comando += "WHERE Escola.nome ='" + busca.getNome() + "%'"
				+ " OR Endereco.logradouro ='"
				+ busca.getEndereco().getLogradouro() + "%'"
				+ " OR Escola.bairro ='" + busca.getEndereco().getBairro()
				+ "%'";
		try {

			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				if(rs.first()){
					escolaLista = new ArrayList<Escola>();
				}
				Escola escola = new Escola();
				escola.setId(rs.getInt("id"));
				escola.setNome(rs.getString("nome"));
				escola.setResponsavel(rs.getString("responsavel"));
				escola.setEmail(rs.getString("email"));
				escola.setTelefone(rs.getLong("telefone"));
				escola.setEndereco(new Endereco());
				escola.getEndereco().setId((rs.getInt("endereco")));
				escolaLista.add(montarEscola(escola));

			}
		} catch (Exception e) {
			throw new GlobalException("buscar escola", e);
		}
		acessaDB.fechaConexao();
		return escolaLista;
		
	}

	public boolean excluirEscola(Escola escola) throws GlobalException {

		String comando = "DELETE  FROM Escola WHERE id =" + escola.getId();
		Statement p;

		try {
			p = connect.createStatement();
			p.execute(comando);

		} catch (Exception e) {
			throw new GlobalException("excluir escola", e);
		}
		acessaDB.fechaConexao();
		return true;

	}

}
