package service;

//import dao.UserDAO;
//import model.UserBean;
import dao.BoardDAO;
import model.BoardBean;
//import dao.HashDAO;
//import model.HashBean;
import model.UserBean;

public class BoardService {
	private BoardDAO boarddao;
	
	public BoardService() {
		boarddao=BoardDAO.getInstance();
	}
	
	public int uploadBoard(String content, String fileName, String fileRealName, String category, String userId) {
		if(content!=null) {
			BoardBean boardbean=new BoardBean();
			boardbean.setContent(content);
			boardbean.setPhotoName(fileName);
			boardbean.setPhotoRealName(fileRealName);
			boardbean.setUserId(userId);
			boardbean.setCategory(category);
			boarddao.insertBoard(boardbean);
			return 1;
		}else{
			return 0;
		}
		
	}
	
	public void removeBoard() {
		
	}
	
	public void updateBoard() {
		
	}
	
	public void searchBoard() {
		
	}
}
