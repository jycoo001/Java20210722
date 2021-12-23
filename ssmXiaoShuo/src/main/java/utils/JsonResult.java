package utils;

public class JsonResult {
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public JsonResult(int status) {
		super();
		this.status = status;
	}
	
}
