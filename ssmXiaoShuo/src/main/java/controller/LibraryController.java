package controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.Library;
import entity.Writer;
import service.LibraryService;
import service.WriterService;

@Controller
@RequestMapping("Library")
public class LibraryController extends BasicController<Library> {

	@Autowired
	LibraryService service;
	@Autowired
	WriterService rservice;

	@Override
	public void delete(int id, ModelMap m, HttpServletResponse resp) {
		service.delete(id);
		Writer u = rservice.selectByLibrary_id(id);
		u.setLibrary_id(0);
		rservice.update(u);
		toindex(resp);
	}

	@RequestMapping("readerindex")
	public String readerIndex(ModelMap m, HttpServletResponse resp) {

		m.put("list", service.select());
		return "Library/readerindex";
	}

	@RequestMapping("read")
	public String read(Integer id, ModelMap m, HttpServletResponse resp) {

		List<Library> br = service.select();
		int count = br.size();
		Library li = br.get(id - 1);
		m.put("list", li);
		m.put("count", count);
		return "Library/read";
	}

	@RequestMapping("writer")
	public String writer(Integer id, ModelMap m, HttpServletResponse resp) {

		List<Library> br = service.select();
		int count = br.size();
		Library li = br.get(id - 1);
		m.put("list", li);
		m.put("count", count);
		return "Library/writer";
	}

	@RequestMapping("lastzhang")
	public String last(Integer id, ModelMap m, HttpServletResponse resp) {

		List<Library> br = service.select();
		int count = br.size();
		Library li = br.get(id - 2);
		m.put("list", li);
		m.put("count", count);
		return "Library/read";
	}

	@RequestMapping("nextzhang")
	public String next(Integer id, ModelMap m, HttpServletResponse resp) {

		List<Library> br = service.select();
		int count = br.size();
		Library li = br.get(id);
		m.put("list", li);
		m.put("count", count);
		return "Library/read";
	}

	@RequestMapping("lastzhango")
	public String last1(Integer id, ModelMap m, HttpServletResponse resp) {

		List<Library> br = service.select();
		int count = br.size();
		Library li = br.get(id - 2);
		m.put("list", li);
		m.put("count", count);
		return "Library/writer";
	}

	@RequestMapping("nextzhango")
	public String nexto(Integer id, ModelMap m, HttpServletResponse resp) {

		List<Library> br = service.select();
		int count = br.size();
		Library li = br.get(id);
		m.put("list", li);
		m.put("count", count);
		return "Library/writer";
	}
	

	
	
	@RequestMapping("save")
	public void  save(Library u,ModelMap m,HttpServletResponse resp) {
		String name_zhengwen = u.getName_zhengwen();
		name_zhengwen.replaceAll("    ", "</p><br><p <p style="+"text-indent:2em"+">" );
		u.setName_zhengwen(name_zhengwen);
		service.insert(u);
		toindex(resp);
	}

	
	
}