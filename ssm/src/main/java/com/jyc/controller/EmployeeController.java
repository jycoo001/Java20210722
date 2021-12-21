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
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/list")
	public String list(HttpSession session, Employee employeeStr, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "8") Integer pageSize, Map<String, Object> map) {
		List<Department> list = departmentService.quertSelector(null);
		map.put("department", list);
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");

		PageHelper.startPage(pageNumber, pageSize);

		List<Employee> list1 = employeeService.quertSelector(employeeStr);
		PageInfo<Employee> page = new PageInfo<>(list1);
		// page
		page.calcByNavigatePages(5);

		map.put("employeeList", list1);
		map.put("employee", employeeStr);
		map.put("page", page);
		return "employee/list";
	}

	@GetMapping(value = { "/insert", "/update/{id}" })
	public String toAddEdit(@PathVariable(required = false) Integer id, Map<String, Object> map) {
		map.put("list", departmentService.quertSelector(null));
		if (id != null) {
			Employee e = employeeService.selectorOne(id);
			map.put("employee", e);
			return "employee/update";
		} else {
			return "employee/update";
		}
	}

	@PostMapping(value = { "/insert", "/update" })
	public String edit(HttpSession session, Employee employeeStr, Map<String, Object> map,
			@RequestParam(name = "picturex") MultipartFile multipartFile) {
		String uu = "project/img/";
		if (employeeStr.getId() != null) {
			if (employeeStr.employee()) {
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
					employeeStr.setPicture((uu + name));
				}
				int rows = employeeService.update(employeeStr);
				if (rows > 0) {
					session.setAttribute("detail", "修改成功");
					return "redirect:/employee/list";
				} else {
					map.put("employee", employeeStr);
					map.put("detail", "修改失败！");
					return "employee/edit";
				}
			} else {
				map.put("employee", employeeStr);
				map.put("detail", "修改项中有空值！");
				return "employee/edit";
			}
		} else {
			if (employeeStr.employee()) {
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
					employeeStr.setPicture((uu + name));
				}

				int row = employeeService.insert(employeeStr);
				if (row > 0) {
					session.setAttribute("detail", "添加成功");
					return "redirect:/employee/list";
				} else {
					map.put("detail", "添加失败");
					map.put("employee", employeeStr);
					return "employee/update";
				}
			} else {
				map.put("detail", "添加项中有空值");
				map.put("employee", employeeStr);
				return "employee/update";
			}
		}
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(required = true) Integer id, HttpSession session) {
		if (id != null) {
			int row = employeeService.delete(id);
			if (row > 0) {
				session.setAttribute("detail", "删除成功");
			} else {
				session.setAttribute("detail", "删除失败");
			}
		}
		return "redirect:/employee/list";
	}

	@PostMapping(value = "/delete", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(name = "che[]") Integer[] ids) {
		Map<String, Object> resp = new HashMap<>();
		if (ids.length > 0) {
			int rows = employeeService.deleteMany(ids);
			if (rows > 0) {
				resp.put("success", true);
				resp.put("rows", rows);
			} else {
				resp.put("success", false);
				resp.put("error", "删除失败");
			}
		} else {
			resp.put("success", false);
			resp.put("error", "删除的项不能为空");
		}
		return resp;
	}
}
