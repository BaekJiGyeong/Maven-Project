package com.ktds.jgbaek;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public boolean login() {
		System.out.println("로그인을 성공했습니다!");
		return true;
	}

}
