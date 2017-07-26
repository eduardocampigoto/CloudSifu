package br.com.cloudsifu.safe;

import java.util.Base64;
public class CriptoDecode {
	
		
		public String descriptografar(String senha){
			
			
			try{
			
			byte[] base64decodedBytes = Base64.getDecoder().decode(senha.getBytes());
			String descriptografada = new String(base64decodedBytes, "UTF-8");
			
			 
			 return descriptografada;
			
			}catch(Exception e){
				e.printStackTrace();
				return "Erro";
			}
			
			
			
		}
		
	
	
/*	public String descriptografar(String entrada) throws IOException{
		
		BASE64Decoder decodeBase = new BASE64Decoder();
		MessageDigest digest = MessageDigest.getInstance("MD5");
		String chave;
		
		digest.update(entrada.getBytes());	
		//byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
		byte[] base64decodedBytes = Base64.getDecoder()	
		
	try{
		
		digest = MessageDigest.getInstance("MD5");		
		byte[] decodifica = Base64.getDecoder().decode(entrada);		
		String criptografada = new String (decodifica, "UTF-8");
		
		descriptografada = removerChave(criptografada);
	
		return descriptografada;
	}catch(Exception e){
		
		e.printStackTrace();
		return ("ERRO DESCRIPTOGRAFAR");
	}	
			
		
	}
	

	public String removerChave(String criptografada){
		String descriptografada = "";
		try{
			
			for (int i=0; i < criptografada.length(); i=i+2){
				
				descriptografada = descriptografada+criptografada.charAt(i);
				
			
			}
			
			return  descriptografada;
			
		}catch(Exception e){
			e.printStackTrace();
			
			return ("Erro removeChave"); 
		}
  }
	*/				
}//End Class