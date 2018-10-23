package service;

import dao.UserDAO;
import model.UserBean;

public class UserService {
	private UserDAO userdao;
	
	public UserService() {
		userdao=UserDAO.getInstance();
	}
	
	public boolean signup(String id, String password, String passwordchk, String name) {
		if(password.equals(passwordchk)==true){
			UserBean userbean=new UserBean();
			userbean.setId(id);
			userbean.setPassword(password);
			userbean.setUserName(name);
			userdao.insertUser(userbean);
			return true;
		}else {
			return false;
		}
	}
	public boolean login(String id, String password) {
		UserBean userbean=userdao.selectOne(id);
		if(userbean==null) return false; //존재하지 않는 아이디
		else {
			if(userbean.getPassword().equals(password))return true;//로그인 성공
			else return false;//비밀번호가 틀린경우
		}
	}
	
	public void updateCategory() {
		
	}
	
	public void updateMyphoto() {
		
	}
}
