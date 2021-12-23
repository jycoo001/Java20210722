package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	String ex = ",.css,.js,.png,.jpg,.jpeg";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String url = req.getRequestURI();
		int pos = url.lastIndexOf(".");
		if (pos > 0) {
			if (ex.indexOf("," + url.substring(pos) + ",") >= 0) {
				chain.doFilter(request, response);// ͨ������
				return;
			}
		}

		if (url.indexOf("login.html") >= 0 || url.indexOf("Admin/login") >= 0 || url.indexOf("validatecode.jsp") >= 0
				|| url.indexOf("See/See") >= 0 || url.indexOf("Writer/login") >= 0 || url.indexOf("Reader/login") >= 0
				|| url.indexOf("Writer/ll") >= 0 || url.indexOf("edit.jsp") >= 0 || url.indexOf("Admin/xx") >= 0
				|| url.indexOf("Admin/insert") >= 0)
			chain.doFilter(request, response);// ͨ������
		else {
			if (req.getSession().getAttribute("Admin") == null && req.getSession().getAttribute("Writer") == null
					&& req.getSession().getAttribute("Reader") == null) {
				resp.sendRedirect(req.getContextPath() + "/login.html");
			} else {
				chain.doFilter(request, response);// ͨ������
			}

		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
