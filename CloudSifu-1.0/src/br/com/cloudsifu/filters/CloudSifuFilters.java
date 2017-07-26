package br.com.cloudsifu.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebFilter;

import br.com.cloudsifu.objects.Usuario;


@WebFilter(urlPatterns = {"/profile/*", "/rest/*"})
public class CloudSifuFilters implements Filter {

	public CloudSifuFilters() {

	}

	public void destroy() {
		
	}

	public void init(FilterConfig fconfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			
			HttpServletRequest req = (HttpServletRequest) request;			
			HttpServletResponse res = (HttpServletResponse) response;			
			HttpSession sessao = req.getSession();
			Usuario usuario = (Usuario) sessao.getAttribute("usuario");
			String context =  req.getContextPath();
			String page=context+"/profile/profile.html";
			
			if (usuario == null || sessao == null){
				sessao.setAttribute("msg", "Você não está logado !!");
				page =context+"/index.html";
				res.sendRedirect(page);
			}
			sessao.setAttribute("uri", page);
			
			if(!response.isCommitted()){
				chain.doFilter(req, res);

			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			

	}

}// ENDCLASS
