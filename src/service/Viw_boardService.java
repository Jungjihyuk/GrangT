package service;

import java.util.Vector;

//import dao.UserDAO;
//import model.UserBean;
import dao.Viw_boardDAO;
import model.Viw_boardBean;

public class Viw_boardService {
	private Viw_boardDAO viw_boarddao;
	
	public Viw_boardService() {
		viw_boarddao=Viw_boardDAO.getInstance();
	}
	
	public Vector<Viw_boardBean> selectAllBoard(String category) {
		Vector<Viw_boardBean> list=viw_boarddao.selectAllBoard(category);
		return list;
	}
	
	public void removeBoard() {
		
	}
	
	public void updateBoard() {
		
	}
	
	public Vector<Viw_boardBean> searchBoard(String hash) {
		Vector<Viw_boardBean> Slist=viw_boarddao.searchHashBoard(hash);
		return Slist;
	}
	
}