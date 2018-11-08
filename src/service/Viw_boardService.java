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
	
	public String searchBoard(String hash) {
		if(hash==null) hash="";
		StringBuffer result=new StringBuffer("");
		result.append("{\"result\":[");
		Vector<Viw_boardBean> list=viw_boarddao.searchHashBoard(hash);
		for(int i=0; i<list.size(); i++) {
			result.append("[{\"value\":\""+list.get(i).getUserName()+"\"},");
			result.append("{\"value\":\""+list.get(i).getCategory()+"\"},");
			result.append("{\"value\":\""+list.get(i).getPresent()+"\"},");
			result.append("{\"value\":\""+list.get(i).getPhotoRealName()+"\"},");
			result.append("{\"value\":\""+list.get(i).getContent()+"\"},");
			result.append("{\"value\":\""+list.get(i).getHash()+"\"}],");
		}
		result.append("]}");
		return result.toString();
	}
	
}