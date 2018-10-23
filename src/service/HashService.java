package service;

import model.HashBean;
import dao.HashDAO;

public class HashService {

	private HashDAO hashdao;
	
	public HashService() {
		hashdao=HashDAO.getInstance();
	}
	
	public HashBean uploadBoard(String hash, int boardNum) {
		HashBean hashbean=new HashBean();
		hashbean.setBoardNum(boardNum);
		hashbean.setHash(hash);
		hashdao.setHash(hashbean);
		return hashbean;
	}
}
