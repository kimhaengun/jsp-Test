package com.cos.TestProject.service;

import java.util.List;

import com.cos.TestProject.domain.user.User;
import com.cos.TestProject.domain.user.UserDao;
import com.cos.TestProject.domain.user.dto.DeleteReqDto;
import com.cos.TestProject.domain.user.dto.JoinReqDto;
import com.cos.TestProject.domain.user.dto.LoginReqDto;

public class UserService {
	private UserDao userDao = new UserDao();
	public List<User> 유저목록보기(){
		return userDao.findAll();
	}
	public int 글삭제(DeleteReqDto dto) {
		int result = userDao.deleteById(dto);
		return result;
	}
	public int 회원가입(JoinReqDto dto) {
		int result = userDao.save(dto);
		return result;
	}

	public User 로그인(LoginReqDto dto) {
		// TODO Auto-generated method stub
		return userDao.findByUsernameAndPassword(dto);
}
}