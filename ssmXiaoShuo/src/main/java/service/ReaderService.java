package service;

import entity.Reader;

public interface ReaderService extends BasicService<Reader> {
	public void updatepass(Reader u);

	public Reader login(Reader u);
}
