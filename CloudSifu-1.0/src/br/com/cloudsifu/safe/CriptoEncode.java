package br.com.cloudsifu.safe;
import java.security.MessageDigest;
import com.sun.jersey.core.util.Base64;


public class CriptoEncode {

	public String criptografarSenha(String senha, String chave){
		Base64 encoder;
		
	/*	try{
			MessageDigest digest = MessageDigest.getInstance("MD5");
			 
			encoder = new Base64();
			String trava = senha+chave;			
			digest.update(trava.getBytes());						
			byte[] cripto = Base64.encode(digest.digest());				
			String criptoString = new String(cripto.);			
			System.out.println("Senha Criptografada: - "+criptografada);
			return criptografada;
			
		}catch(Exception e){
			e.printStackTrace();
			
			return ("Erro criptografarSenha");
		}
	*/	
		return ("Erro criptografarSenha");
	}
	
	
	

}