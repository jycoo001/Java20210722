package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Admin;
import entity.Reader;
import entity.Writer;
import entity.base;
import service.AdminService;
import service.ReaderService;
import service.WriterService;
import utils.JsonResult;
import utils.MD5Utils;

@Controller
@RequestMapping("Admin")
public class AdminController extends BasicController<Admin> {

	@Autowired
	AdminService service;
	@Autowired
	WriterService wService;
	@Autowired
	ReaderService rService;

	@RequestMapping("repass")
	public @ResponseBody JsonResult repass(String pass, String pass1, String pass2, HttpServletRequest req) {
		if (!pass1.equals(pass2))
			return new JsonResult(-1);
		Admin u = (Admin) req.getSession().getAttribute("Admin");
		if (!u.getPassword().equals(MD5Utils.MD5(pass)))
			return new JsonResult(-2);
		u.setPassword(MD5Utils.MD5(pass2));
		service.update(u);
		return new JsonResult(1);
	}

	@RequestMapping("login")
	public void login(base u, String yanzheng, String login_type, ModelMap model, HttpServletRequest req,
			HttpServletResponse resp, HttpSession session) {
		if (login_type.equals("Admin")) {
			String key = (String) session.getAttribute("key");
			if (key.equals(yanzheng)) {
				Admin cu = new Admin();
				cu.setName(u.getName());
				cu.setPassword(u.getPassword());
				Admin current = service.login(cu);
				if (current == null) {
					tourl(resp, req.getContextPath() + "/1ogin.html");
				} else {
					HttpSession s = req.getSession();
					s.setMaxInactiveInterval(15 * 60);
					s.setAttribute("Admin", current);
					tourl(resp, req.getContextPath() + "/index.jsp");
				}
			} else {
				System.out.println("error," + key);
				tourl(resp, req.getContextPath() + "/login.html");
			}
		} else if (login_type.equals("Writer")) {
			String key = (String) session.getAttribute("key");
			if (key.equals(yanzheng)) {
				Writer wu = new Writer();
				wu.setName(u.getName());
				wu.setPassword(u.getPassword());
				Writer current = wService.login(wu);
				if (current == null) {
					tourl(resp, req.getContextPath() + "/1ogin.html");
				} else {
					HttpSession s = req.getSession();
					s.setMaxInactiveInterval(15 * 60);
					s.setAttribute("Writer", current);
					tourl(resp, req.getContextPath() + "/Writeindex.jsp");
				}
			} else {
				System.out.println("error," + key);
				tourl(resp, req.getContextPath() + "/login.html");
			}
		} else if (login_type.equals("Reader")) {
			String key = (String) session.getAttribute("key");
			if (key.equals(yanzheng)) {
				Reader ru = new Reader();
				ru.setName(u.getName());
				ru.setPassword(u.getPassword());
				Reader current = rService.login(ru);
				if (current == null) {
					tourl(resp, req.getContextPath() + "/1ogin.html");
				} else {
					HttpSession s = req.getSession();
					s.setMaxInactiveInterval(15 * 60);
					s.setAttribute("Reader", current);
					tourl(resp, req.getContextPath() + "/Readerindex.jsp");
				}
			} else {
				System.out.println("error," + key);
				tourl(resp, req.getContextPath() + "/login.html");
			}
		}
	}

	@RequestMapping("outlogin")
	public void outlogin(HttpServletRequest req, HttpServletResponse resp) {

		req.getSession().removeAttribute("Admin");
		req.getSession().removeAttribute("Writer");
		req.getSession().removeAttribute("Reader");
		tourl(resp, req.getContextPath() + "/login.html");
	}
	@RequestMapping("xx")
	public void xx(HttpServletRequest req, HttpServletResponse resp) {
		tourl(resp, req.getContextPath() + "/edit.jsp");
	}
	@RequestMapping("insert")
	public void ins(base u, String login_type, HttpServletRequest req, HttpServletResponse resp) {
		HttpSession s = req.getSession();
		s.setMaxInactiveInterval(15 * 60);
		s.setAttribute("Admin", 1);
		if (login_type.equals("Admin")) {

			Admin cu = new Admin();
			cu.setName(u.getName());
			cu.setPassword(u.getPassword());
			int current = service.insert(cu);

			if (current == 0) {
				req.getSession().removeAttribute("Admin");
				tourl(resp, req.getContextPath() + "/edit.jsp");
				
			} else {
				req.getSession().removeAttribute("Admin");
				tourl(resp, req.getContextPath() + "/login.html");
				
			}
		} else if (login_type.equals("Writer")) {
			Writer wu = new Writer();
			wu.setName(u.getName());
			wu.setPassword(u.getPassword());
			int current = wService.insert(wu);
			if (current == 0) {
				req.getSession().removeAttribute("Admin");
				tourl(resp, req.getContextPath() + "/edit.jsp");
				
			} else {
				req.getSession().removeAttribute("Admin");
				tourl(resp, req.getContextPath() + "/login.html");
				
			}
		} else if (login_type.equals("Reader")) {
			Reader ru = new Reader();
			ru.setName(u.getName());
			ru.setPassword(u.getPassword());
			int current = rService.insert(ru);
			if (current == 0) {
				req.getSession().removeAttribute("Admin");
				tourl(resp, req.getContextPath() + "/edit.jsp");
				
			} else {
				req.getSession().removeAttribute("Admin");
				tourl(resp, req.getContextPath() + "/login.html");
				
			}
		}
	}
}