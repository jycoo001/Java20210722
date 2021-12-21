package com.jyc.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.model.Department;
import com.jyc.model.Employee;
import com.jyc.service.DepartmentService;
import com.jyc.service.EmployeeService;
import com.jyc.util.Constant;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/list")
	public String list(Map<String, Object> map, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "8") Integer pageSize, Department d, HttpSession session) {
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");
		PageHelper.startPage(pageNumber, pageSize);

		List<Department> list = departmentService.quertSelector(d);
		map.put("departmentList", list);
		PageInfo<Department> page = new PageInfo<>(list);
		// page
		page.calcByNavigatePages(5);
		map.put("page", page);
		map.put("department", d);
		return "department/list";
	}

	@GetMapping(value = { "/insert", "/update/{id}" })
	public String toAddEdit(@PathVariable(required = false) Integer id, Map<String, Object> map) {
		if (id != null) {
			Department e = departmentService.selectOne(id);
			map.put("department", e);
			return "department/update";
		} else {
			return "department/update";
		}
	}

	@PostMapping(value = { "/insert", "/update" })
	public String edit(HttpSession session, Department d, Map<String, Object> map,
			@RequestParam(name = "picture") MultipartFile multipartFile) {
		String uu = "project/depimg/";
		if (d.getId() != null) {
			if (d.getName() != null && d.getName().trim().length() > 0) {
				if (multipartFile.getOriginalFilename() != null
						&& multipartFile.getOriginalFilename().trim().length() > 0) {
					String uid = UUID.randomUUID().toString();
					int idx = multipartFile.getOriginalFilename().lastIndexOf(".");
					String name = uid + "." + multipartFile.getOriginalFilename().substring(idx + 1);
					String url = Constant.PICTURE_URL + uu + name;
					File file = new File(url);
					try {
						multipartFile.transferTo(file);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
					d.setLogo(uu + name);
				}
				int rows = departmentService.update(d);
				if (rows > 0) {
					session.setAttribute("detail", "修改成功");
					return "redirect:/department/list";
				} else {
					map.put("department", d);
					map.put("detail", "修改失败！");
					return "department/edit";
				}
			} else {
				map.put("department", d);
				map.put("detail", "修改项中有空值！");
				return "department/edit";
			}
		} else {
			if (multipartFile.getOriginalFilename() != null
					&& multipartFile.getOriginalFilename().trim().length() > 0) {
				String uid = UUID.randomUUID().toString();
				int idx = multipartFile.getOriginalFilename().lastIndexOf(".");
				String name = uid + "." + multipartFile.getOriginalFilename().substring(idx + 1);
				String url = Constant.PICTURE_URL + uu + name;
				File file = new File(url);
				try {
					multipartFile.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				d.setLogo(uu + name);
			}
			if (d.getName() != null && d.getName().trim().length() > 0) {
				int row = departmentService.insert(d);
				if (row > 0) {
					session.setAttribute("detail", "添加成功");
					return "redirect:/department/list";
				} else {
					map.put("detail", "添加失败");
					map.put("department", d);
					return "department/update";
				}
			} else {
				map.put("detail", "添加项中有空值");
				map.put("department", d);
				return "department/update";
			}
		}
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(required = true) Integer id, HttpSession session) {
		if (id != null) {
			Department department = departmentService.selectOne(id);
			if (department.getCount() <= 0) {
				int row = departmentService.delete(id);
				if (row > 0) {
					session.setAttribute("detail", "删除成功");
				} else {
					session.setAttribute("detail", "删除失败");
				}
			} else {
				session.setAttribute("detail", "部门人数不为空");
				return "redirect:/department/list";
			}

		}
		return "redirect:/department/list";
	}

	@GetMapping("/updateAndDelete/{id}")
	public String deleteAndUpdate(@PathVariable(required = true) Integer id, Map<String, Object> map) {
		List<Department> list = departmentService.quertSelector(null);
		map.put("departmentList", list);
		map.put("id", id);
		return "department/updateEm";
	}

	@RequestMapping("/edit")
	public String deleteEdit(Integer id, Integer departmentId, Map<String, Object> map) {
		// 设置employee的id
		Employee employee = new Employee();
		employee.setDepartmentId(id);
		List<Employee> list = employeeService.quertSelector(employee);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setDepartmentId(departmentId);
		}
		int rows = employeeService.updateMany(list);
		if (rows > 0) {
			int row = departmentService.delete(id);
			if (row > 0) {
				departmentService.update();
				map.put("detail", "修改和删除成功！");
				return "redirect:/department/list";
			} else {
				map.put("detail", "修改成功！删除失败！");
				return "redirect:/department/list";
			}
		} else {
			map.put("detail", "修改失败！");
			return "redirect:/department/list";
		}
	}

	@PostMapping(value = "/delete", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(name = "che[]") Integer[] ids) {
		Map<String, Object> resp = new HashMap<>();
		List<Department> list = departmentService.selectorMany(ids);

		if (ids.length > 0) {
			Object rows = departmentService.deleteMany(ids, list);
			if (rows instanceof Integer) {
				resp.put("success", true);
				resp.put("rows", rows);
			} else {
				resp.put("success", false);
				resp.put("error", rows + "");
			}
		} else {
			resp.put("success", false);
			resp.put("error", "删除项不能为空");
		}
		return resp;
	}
}
