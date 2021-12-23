package controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import service.BasicService;

public class BasicController<T>{
public void toindex(HttpServletResponse resp) {
	tourl(resp,"index");
}

public void tourl(HttpServletResponse resp,String file) {
	try {
		resp.sendRedirect(file);
	} catch (IOException e) {
	}
}
private BasicService<T> getservice(){
	try {
	Field f=this.getClass().getDeclaredField("service");
	f.setAccessible(true);
	return (BasicService<T>) f.get(this);
	}catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}

private String getTname(){
	 Type type = ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	return  ((Class)type).getSimpleName();
}

@RequestMapping("index")
public String  index(ModelMap m) {
	m.put("list", getservice().select());
	return getTname()+"/index";
}

@RequestMapping("delete")
public void  delete(int id,ModelMap m,HttpServletResponse resp) {
	getservice().delete(id);
	toindex(resp);
}

@RequestMapping("add")
public String  add(ModelMap m) {
	return getTname()+"/edit";
}
@RequestMapping("edit")
public String  edit(int id,ModelMap m) {
	m.put("info",getservice().selectById(id));
	return add(m);
}

@RequestMapping("save")
public void  save(T u,ModelMap m,HttpServletResponse resp) {
	getservice().insert(u);
	toindex(resp);
}

@RequestMapping("update")
public void  update(T u,ModelMap m,HttpServletResponse resp) {
	getservice().update(u);
	toindex(resp);
}
}
