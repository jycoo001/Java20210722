package entity;

public class Library {
	private int id;
	private String name;
	private String name_one;
	private String name_zhengwen;

	public Library() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Library(int id, String name, String name_one, String name_zhengwen) {
		super();
		this.id = id;
		this.name = name;
		this.name_one = name_one;
		this.name_zhengwen = name_zhengwen;
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

	public String getName_one() {
		return name_one;
	}

	public void setName_one(String name_one) {
		this.name_one = name_one;
	}

	public String getName_zhengwen() {
		return name_zhengwen;
	}

	public void setName_zhengwen(String name_zhengwen) {
		this.name_zhengwen = name_zhengwen;
	}

	@Override
	public String toString() {
		return "Library [id=" + id + ", name=" + name + ", name_one=" + name_one + ", name_zhengwen=" + name_zhengwen
				+ "]";
	}

}
