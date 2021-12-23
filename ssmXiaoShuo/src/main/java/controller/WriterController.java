package controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.Writer;
import service.LibraryService;
import service.WriterService;

@Controller
@RequestMapping("Writer")
public class WriterController extends BasicController<Writer> {

	@Autowired
	WriterService service;
	@Autowired
	LibraryService lservice;

	@RequestMapping("add")
	public String add(ModelMap m) {
		m.put("book", lservice.select());
		return "Writer/edit";
	}

	@RequestMapping("update")
	public void update(Writer u, ModelMap m, HttpServletResponse resp) {
		u.setLibrary_id(u.getLibrary_id() + 1);
		service.update(u);
		toindex(resp);
	}

}