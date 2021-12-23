package entity;

public class Writer {
	private int id;
	private String name;
	private String password;
	private int Library_id;
	private String Library_name;

	public Writer() {
	}

	public Writer(int id, String name, String password, int Library_id) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.Library_id = Library_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLibrary_id() {
		return Library_id;
	}

	public void setLibrary_id(int Library_id) {
		this.Library_id = Library_id;
	}

	public String getLibrary_name() {
		return Library_name;
	}

	public void setLibrary_name(String Library_name) {
		this.Library_name = Library_name;
	}

}
