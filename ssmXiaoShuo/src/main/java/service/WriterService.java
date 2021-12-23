package service;

import entity.Writer;

public interface WriterService extends BasicService<Writer> {

	public Writer selectByLibrary_id(int Library_id);

	public void updatepass(Writer u);

	public Writer login(Writer u);
}
