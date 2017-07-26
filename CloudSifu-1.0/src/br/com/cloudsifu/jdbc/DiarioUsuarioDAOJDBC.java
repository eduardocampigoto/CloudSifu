package br.com.cloudsifu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.DiarioTurma;
import br.com.cloudsifu.objects.DiarioUsuario;
import br.com.cloudsifu.objects.Usuario;
import br.com.cloudsifu.services.DiarioTurmaServico;
import br.com.cloudsifu.services.UsuarioServico;

public class DiarioUsuarioDAOJDBC implements DiarioUsuarioDAO {

	private AcessaDB acessaDB;
	private Connection connect;
	private DiarioUsuario diarioUsuario;
	private UsuarioServico usuarioServico;
	private DiarioTurmaServico diarioTurmaServico;
	
	public DiarioUsuarioDAOJDBC() {
		usuarioServico = new UsuarioServico();
		diarioUsuario = new DiarioUsuario();
		diarioTurmaServico = new DiarioTurmaServico();
		acessaDB = new AcessaDB();
		connect = acessaDB.criaConexao();

	}
		
	public void adicionarListaDiarioUsuario(List<DiarioUsuario> listaAdicionar)
			throws GlobalException {
		String comando ="";
		
		try {
			Iterator<DiarioUsuario> itDiarioUsuario = listaAdicionar.iterator();
			while(itDiarioUsuario.hasNext()){			
			diarioUsuario = itDiarioUsuario.next();
			diarioUsuario.setDiarioTurma(diarioTurmaServico.buscarDiarioTurmaData(diarioUsuario.getDiarioTurma()));
			if(diarioUsuario.getId() == 0){
				comando = "INSERT INTO `DiarioUsuario` "
					+ "(diarioTurma, usuario, presenca, observacao) VALUES(?,?,?,?)";
			}else{
				comando = "UPDATE DiarioUsuario SET  diarioTurma=?, usuario=?, presenca=?, observacao=? WHERE diarioTurma="
						+ diarioUsuario.getDiarioTurma().getId();
				comando+=" AND usuario= "+diarioUsuario.getUsuario().getId();
			}
			
			PreparedStatement p;
			p = connect.prepareStatement(comando);
			p.setInt(1, diarioUsuario.getDiarioTurma().getId());
			p.setInt(2, diarioUsuario.getUsuario().getId());
			p.setBoolean(3, diarioUsuario.getPresenca());
			p.setString(4, diarioUsuario.getObservacao());
			p.execute();

			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("adicionar DiarioUsuario", e);
		}
		acessaDB.fechaConexao();
	}
	
	
	public boolean editarDiarioUsuario(DiarioUsuario diarioUsuario)	throws GlobalException {

		try {

			String comando = "UPDATE DiarioUsuario SET  diarioTurma =?, usuario=?, presenca=?, observacao=? WHERE diarioTurma="
					+ diarioUsuario.getDiarioTurma().getId();
			comando+=" AND usuario= "+diarioUsuario.getUsuario().getId();
			
			PreparedStatement p;
			p = connect.prepareStatement(comando);
			p.setInt(1, diarioUsuario.getDiarioTurma().getId());
			p.setInt(2, diarioUsuario.getUsuario().getId());
			p.setBoolean(3, diarioUsuario.getPresenca());
			p.setString(4, diarioUsuario.getObservacao());

			p.execute();

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("editar DiarioUsuario", e);
		}
		acessaDB.fechaConexao();
		return true;

	}

	public boolean editarListaDiarioUsuario(List<DiarioUsuario> listaEditar)
			throws GlobalException {

		try {
			Iterator<DiarioUsuario> itDiarioUsuario = listaEditar.iterator();
			while(itDiarioUsuario.hasNext()){
			diarioUsuario = itDiarioUsuario.next();
			String comando = "UPDATE DiarioUsuario SET  diarioTurma =?, usuario=?, presenca=?, observacao=? WHERE diarioTurma="
					+ diarioUsuario.getDiarioTurma().getId();
			comando+=" AND usuario= "+diarioUsuario.getUsuario().getId();
			
			PreparedStatement p;
			p = connect.prepareStatement(comando);
			p.setInt(1, diarioUsuario.getDiarioTurma().getId());
			p.setInt(2, diarioUsuario.getUsuario().getId());
			p.setBoolean(3, diarioUsuario.getPresenca());
			p.setString(4, diarioUsuario.getObservacao());

			p.execute();
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("editar DiarioUsuario", e);
		}
		acessaDB.fechaConexao();
		return true;

	}

	public DiarioUsuario buscarDiarioUsuarioId(DiarioUsuario busca)
			throws GlobalException {

		String comando = "SELECT * FROM DiarioUsuario WHERE id ="+ busca.getId();

		
		try {

			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				diarioUsuario.setId(rs.getInt("id"));
				diarioUsuario.setDiarioTurma(new DiarioTurma());
				diarioUsuario.getDiarioTurma().setId(rs.getInt("diarioTurma"));
				diarioUsuario.setDiarioTurma(diarioTurmaServico.buscarDiarioTurmaId(diarioUsuario.getDiarioTurma()));
				diarioUsuario.setPresenca(rs.getBoolean("presenca"));
				diarioUsuario.setUsuario(new Usuario());
				diarioUsuario.getUsuario().setId(rs.getInt("usuario"));
				diarioUsuario.setUsuario(usuarioServico.buscarUsuarioId(diarioUsuario.getUsuario()));
				diarioUsuario.setObservacao(rs.getString("observacao"));
			}

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar DiarioUsuario por ID", e);
		}
		acessaDB.fechaConexao();
		return diarioUsuario;
	}

	public List<DiarioUsuario> buscarDiarioUsuario(DiarioUsuario busca)
			throws GlobalException {
		List<DiarioUsuario> diarioUsuarioLista = new ArrayList<DiarioUsuario>();
		String comando = "SELECT * FROM DiarioUsuario";		
		comando += " WHERE id =" + busca.getId();
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				diarioUsuario.setId(rs.getInt("id"));
				diarioUsuario.setDiarioTurma(new DiarioTurma());
				diarioUsuario.getDiarioTurma().setId(rs.getInt("diarioTurma"));
				diarioUsuario.setDiarioTurma(diarioTurmaServico.buscarDiarioTurmaId(diarioUsuario.getDiarioTurma()));
				diarioUsuario.setPresenca(rs.getBoolean("presenca"));
				diarioUsuario.setUsuario(new Usuario());
				diarioUsuario.getUsuario().setId(rs.getInt("usuario"));
				diarioUsuario.setUsuario(usuarioServico.buscarUsuarioId(diarioUsuario.getUsuario()));
				diarioUsuario.setObservacao(rs.getString("observacao"));
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("editar DiarioUsuario", e);
		}
		acessaDB.fechaConexao();
		return diarioUsuarioLista;

	}
	
	
	public List<DiarioUsuario> buscarListaDiarioUsuario(DiarioUsuario busca)
			throws GlobalException {
		List<DiarioUsuario> diarioUsuarioLista = new ArrayList<DiarioUsuario>();
		String comando = "SELECT * FROM DiarioUsuario";		
		comando+= " WHERE diarioTurma= '" + busca.getDiarioTurma().getId()+"'";
		try {
			ResultSet rs = acessaDB.consulta(comando);
			while (rs.next()) {
				diarioUsuario = new DiarioUsuario();
				diarioUsuario.setId(rs.getInt("id"));
				diarioUsuario.setDiarioTurma(busca.getDiarioTurma());
				diarioUsuario.setPresenca(rs.getBoolean("presenca"));
				diarioUsuario.setUsuario(new Usuario());
				diarioUsuario.getUsuario().setId(rs.getInt("usuario"));
				diarioUsuario.setUsuario(usuarioServico.buscarUsuarioId(diarioUsuario.getUsuario()));
				diarioUsuario.setObservacao(rs.getString("observacao"));
				diarioUsuarioLista.add(diarioUsuario);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("buscar lista DiarioUsuario", e);
		}
		acessaDB.fechaConexao();
		return diarioUsuarioLista;

	}

	
	public boolean excluirDiarioUsuario(DiarioUsuario exclui)
			throws GlobalException {

		String comando = "DELETE  FROM DiarioUsuario WHERE id ="
				+ exclui.getId();
		Statement p;

		try {
			p = connect.createStatement();
			p.execute(comando);

		} catch (Throwable e) {
			e.printStackTrace();
			throw new GlobalException("excluir DiarioUsuario", e);
		}
		acessaDB.fechaConexao();
		return true;

	}

}
