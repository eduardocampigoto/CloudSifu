package br.com.cloudsifu.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.cloudsifu.objects.Usuario;
import br.com.cloudsifu.safe.CriptoDecode;
import br.com.cloudsifu.services.LoginServico;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Usuario usuario = null;
	private LoginServico  loginServico;
	private CriptoDecode decode;
    public Login() {
        super();
        decode = new CriptoDecode();
        loginServico = new LoginServico();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "index.html";
		if(request.getSession().getAttribute("usuario") != null 
		   && !request.getSession().getAttribute("usuario").equals("")){
			page="pages/profile.html";
		}
		response.encodeRedirectURL(page);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String senha = "";
		HttpSession sessao = request.getSession();
		try{
			if(request.getParameter("usuario") != null && 
				!request.getParameter("usuario").equals("") && 
				request.getParameter("senha") != null &&
				request.getParameter("senha") != ""){		
				Usuario tentativa = new Usuario();
				senha = decode.descriptografar(request.getParameter("senha"));
				tentativa.setUsuario(request.getParameter("usuario"));
				tentativa.setSenha(senha);
				usuario = loginServico.validarLogin(tentativa);
			}
			if(usuario != null){
				response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
				response.setHeader("Pragma", "no-cache");
				sessao.setAttribute("usuario", usuario);
				response.encodeRedirectURL("pages/profile.html");
			}else{
				sessao.setAttribute("usuario", null);
				sessao.invalidate();
				response.encodeRedirectURL("index.html");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
