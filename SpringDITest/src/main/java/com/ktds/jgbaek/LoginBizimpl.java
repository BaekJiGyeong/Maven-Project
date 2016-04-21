package com.ktds.jgbaek;

public class LoginBizimpl implements LoginBiz {

	private LoginDAO loginDAO;
	
	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	@Override
	public boolean login() {
		return loginDAO.login();
	}

}
