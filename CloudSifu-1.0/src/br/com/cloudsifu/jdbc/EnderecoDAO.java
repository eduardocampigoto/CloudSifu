package br.com.cloudsifu.jdbc;
import java.util.List;
import br.com.cloudsifu.exception.GlobalException;
import br.com.cloudsifu.objects.Endereco;

public interface EnderecoDAO {
	
	
	
	public void adicionarEndereco(Endereco endereco) throws GlobalException;
	public List<Endereco> buscarEnderecoLista(Endereco endereco) throws GlobalException;
	public Endereco buscarEndereco(Endereco endereco) throws GlobalException;
	public Endereco buscarEnderecoId(Endereco endereco) throws GlobalException;
	public boolean editarEndereco(Endereco endereco) throws GlobalException;
	public boolean excluirEndereco(Endereco endereco) throws GlobalException;
	
}
