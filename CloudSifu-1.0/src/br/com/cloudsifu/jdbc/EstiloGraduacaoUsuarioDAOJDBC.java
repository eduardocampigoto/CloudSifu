package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Estilo;
import br.com.cloudsifu.objects.EstiloGraduacaoUsuario;
import br.com.cloudsifu.objects.Graduacao;
import br.com.cloudsifu.objects.Usuario;
import br.com.cloudsifu.services.EstiloServico;
import br.com.cloudsifu.services.GraduacaoServico;
import br.com.cloudsifu.services.UsuarioServico;

public class EstiloGraduacaoUsuarioDAOJDBC implements EstiloGraduacaoUsuarioDAO {

	private Connection connect;
	private AcessaDB acessaDB;
	private EstiloServico estiloServico;
	private GraduacaoServico graduacaoServico;
	private UsuarioServico usuarioServico;
	private DateConvert convert;

	public EstiloGraduacaoUsuarioDAOJDBC() {
		acessaDB = new AcessaDB();
		connect = acessaDB.criaConexao();
		estiloServico = new EstiloServico();
		graduacaoServico = new GraduacaoServico();
		usuarioServico = new UsuarioServico();
		convert = new DateConvert();
	}

	public boolean adicionarEstiloGraduacaoUsuario(EstiloGraduacaoUsuario estiloGraduacaoUsuario)
			throws GlobalException {

		String comando = "INSERT INTO `EstiloGraduacaoUsuario` "
				+ "(estilo,graduacao,usuario,data)" + "VALUES(?,?,?,?)";
		Date sqlDate = convert.converteParaSqlDate(estiloGraduacaoUsuario.getData());
		PreparedStatement p;

		try {

			p = connect.prepareStatement(comando);
			p.setInt(1, estiloGraduacaoUsuario.getEstilo().getId());
			p.setInt(2, estiloGraduacaoUsuario.getGraduacao().getId());
			p.setInt(3, estiloGraduacaoUsuario.getUsuario().getId());
			p.setDate(4, sqlDate);
			p.execute();

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("adicionar Estilo Graduacao", e);
		}
		acessaDB.fechaConexao();
		return true;

	}

	public EstiloGraduacaoUsuario buscarEstiloGraduacaoUsuarioNome(EstiloGraduacaoUsuario busca)
			throws GlobalException {
		EstiloGraduacaoUsuario estiloGraduacaoUsuario = new EstiloGraduacaoUsuario();
		String comando = "SELECT * FROM Estilo, Graduacao, Usuario";

		if (!busca.equals("null") && !busca.equals("")) {
			comando += " WHERE nome LIKE '" + busca.getEstilo().getNome() + "'"
					+ " OR nome LIKE '" + busca.getGraduacao().getNome() + "'"
					+ " OR nome LIKE '" + busca.getUsuario().getNome() + "'";
		}
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				estiloGraduacaoUsuario.setId(rs.getInt("id"));
				estiloGraduacaoUsuario.getEstilo().setId(rs.getInt("estilo"));
				estiloGraduacaoUsuario.setEstilo(estiloServico.buscarEstiloId(estiloGraduacaoUsuario.getEstilo()));
				estiloGraduacaoUsuario.getGraduacao().setId(rs.getInt("graduacao"));
				estiloGraduacaoUsuario.setGraduacao(graduacaoServico.buscarGraduacaoId(estiloGraduacaoUsuario.getGraduacao()));
				estiloGraduacaoUsuario.getUsuario().setId(rs.getInt("usuario"));
				estiloGraduacaoUsuario.setUsuario(usuarioServico.buscarUsuarioId(estiloGraduacaoUsuario.getUsuario()));
				estiloGraduacaoUsuario.setData(convert.convertParaUtilDate(Date.valueOf(rs.getString("data"))));
				
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar EstiloGraduacaoUsuario", e);
		}
		acessaDB.fechaConexao();
		return estiloGraduacaoUsuario;
	}
	
	public List<EstiloGraduacaoUsuario> buscarEstiloGraduacaoUsuarioId(EstiloGraduacaoUsuario busca)
			throws GlobalException {
		EstiloGraduacaoUsuario estiloGraduacaoUsuario = null;
		List<EstiloGraduacaoUsuario> listaEstiloGraduacaoUsuario = new ArrayList<EstiloGraduacaoUsuario>();
		String comando = "SELECT * FROM EstiloGraduacaoUsuario";

		if (!busca.equals("null") && !busca.equals("")) {
			comando +=" WHERE usuario='" + busca.getUsuario().getId() + "'";
		}
		System.out.println(comando);
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				estiloGraduacaoUsuario = new EstiloGraduacaoUsuario();
				estiloGraduacaoUsuario.setId(rs.getInt("id"));
				estiloGraduacaoUsuario.setEstilo(new Estilo());
				estiloGraduacaoUsuario.getEstilo().setId(rs.getInt("estilo"));
				estiloGraduacaoUsuario.setEstilo(estiloServico.buscarEstiloId(estiloGraduacaoUsuario.getEstilo()));
				estiloGraduacaoUsuario.setGraduacao(new Graduacao());
				estiloGraduacaoUsuario.getGraduacao().setId(rs.getInt("graduacao"));
				estiloGraduacaoUsuario.setGraduacao(graduacaoServico.buscarGraduacaoId(estiloGraduacaoUsuario.getGraduacao()));
				estiloGraduacaoUsuario.setUsuario(new Usuario());
				estiloGraduacaoUsuario.getUsuario().setId(rs.getInt("usuario"));
				estiloGraduacaoUsuario.setUsuario(usuarioServico.buscarUsuarioId(estiloGraduacaoUsuario.getUsuario()));
				estiloGraduacaoUsuario.setData(convert.convertParaUtilDate(Date.valueOf(rs.getString("data"))));
				listaEstiloGraduacaoUsuario.add(estiloGraduacaoUsuario);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar EstiloGraduacaoUsuario", e);
		}
		acessaDB.fechaConexao();
		return listaEstiloGraduacaoUsuario;
	}
	
	public List<EstiloGraduacaoUsuario> buscarListaEstiloGraduacaoUsuario(EstiloGraduacaoUsuario busca)
			throws GlobalException {
		EstiloGraduacaoUsuario estiloGraduacaoUsuario = null;
		List<EstiloGraduacaoUsuario> listaEstiloGraduacaoUsuario = new ArrayList<EstiloGraduacaoUsuario>();
		String comando = "SELECT * FROM EstiloGraduacaoUsuario";

		if (!busca.equals("null") && !busca.equals("")) {
			comando +="usuario='" + busca.getUsuario().getId() + "'";
		}
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				estiloGraduacaoUsuario = new EstiloGraduacaoUsuario();
				estiloGraduacaoUsuario.setId(rs.getInt("id"));
				estiloGraduacaoUsuario.getEstilo().setId(rs.getInt("estilo"));
				estiloGraduacaoUsuario.setEstilo(estiloServico.buscarEstiloId(estiloGraduacaoUsuario.getEstilo()));
				estiloGraduacaoUsuario.getGraduacao().setId(rs.getInt("graduacao"));
				estiloGraduacaoUsuario.setGraduacao(graduacaoServico.buscarGraduacaoId(estiloGraduacaoUsuario.getGraduacao()));
				estiloGraduacaoUsuario.getUsuario().setId(rs.getInt("usuario"));
				estiloGraduacaoUsuario.setUsuario(usuarioServico.buscarUsuarioId(estiloGraduacaoUsuario.getUsuario()));
				estiloGraduacaoUsuario.setData(convert.convertParaUtilDate(Date.valueOf(rs.getString("data"))));
				listaEstiloGraduacaoUsuario.add(estiloGraduacaoUsuario);
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar EstiloGraduacaoUsuario", e);
		}
		acessaDB.fechaConexao();
		return listaEstiloGraduacaoUsuario;
	}

	public boolean excluirEstiloGraduacaoUsuario(EstiloGraduacaoUsuario busca)
			throws GlobalException {

		String comando = "DELETE  FROM EstiloGraduacaoUsuario WHERE id = "
				+ busca.getId();
		Statement p;

		try {
			p = connect.createStatement();
			p.execute(comando);

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("excluir EstiloGraduacaoUsuario", e);
		}
		acessaDB.fechaConexao();
		return true;

	}

	public boolean editarEstiloGraduacaoUsuario(EstiloGraduacaoUsuario busca)
			throws GlobalException {

		String comando = "UPDATE EstiloGraduacaoUsuario SET estilo=?, graduacao=?, usuario=? WHERE id="
				+ busca.getId();

		Date sqlDate = convert.converteParaSqlDate(busca.getData());
		PreparedStatement p;

		try {
			p = connect.prepareStatement(comando);
			p.setInt(1, busca.getEstilo().getId());
			p.setInt(2, busca.getGraduacao().getId());
			p.setInt(3, busca.getUsuario().getId());
			p.setDate(4, sqlDate);
			p.execute();

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("editar EstiloGraduacaoUsuario", e);
		}
		acessaDB.fechaConexao();
		return true;

	}

	

}
