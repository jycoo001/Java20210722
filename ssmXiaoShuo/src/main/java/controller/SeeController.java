package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.See;
import service.LibraryService;
import service.SeeService;

@Controller
@RequestMapping("See")
public class SeeController extends BasicController<See> {

	@Autowired
	SeeService service;
	@Autowired
	LibraryService lservice;

	@RequestMapping("See")
	public String add(ModelMap m) {
		m.put("list", lservice.select());
		return "Library/index";
	}
}