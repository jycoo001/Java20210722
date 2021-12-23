package utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SearchInfo {

	private Map<String, String[]> params;
	private int pageno = 1;
	private String where = "";

	public String getWhere() {
		return where;
	}

	public SearchInfo(String where) {
		super();
		this.where = where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	private int max = 5;

	public String getLimit() {
		return " limit " + ((pageno - 1) * max) + "," + max;
	}

	public SearchInfo() {
		params = getRequest().getParameterMap();
		String P = getRequest().getParameter("pageno");
		if (P != null) {
			pageno = Integer.valueOf(P);
		}
		getRequest().setAttribute("pre", pageno > 1 ? pageno - 1 : 1);
		getRequest().setAttribute("next", pageno + 1);
	}

	public String getString(String name) {
		String[] ps = params.get(name);
		if (ps != null && ps.length > 0)
			return ps[0];
		return "";
	}

	public boolean checkString(String name) {
		String v = getString(name);
		return v != null && v.length() > 0;
	}

	public void toWhere(String name, String colname, int type) {
		String e = "=";
		switch (type) {
		case 1:
			e = "like";
			break;
		}
		String v = getString(name);
		getRequest().setAttribute(name, v);
		if (type == 0)
			v = "'" + v + "'";
		if (type == 1)
			v = "'%" + v + "%'";
		String w = colname + " " + e + " " + v;
		if (!checkString(name))
			return;

		if (where.length() == 0) {
			where = " where ";
			where += w;
		} else {
			where += " and " + w;
		}

	}

	public static HttpServletRequest getRequest() { // pageno map
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;

	}
}
