package com.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.dao.UserDao;
import com.board.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(UserDto dto) throws Exception {
		UserDao dao=sqlSession.getMapper(UserDao.class);
		dao.insert(dto);		
	}

	
	@Override
	public UserDto selectUser(String u_id) throws Exception {
		UserDao dao=sqlSession.getMapper(UserDao.class);
		
		return dao.selectUser(u_id);
	}

	@Override
	public List<UserDto> selectAll() throws Exception {
		UserDao dao=sqlSession.getMapper(UserDao.class);
		System.out.println("dao from selectAll: " + dao);
		return dao.selectAll();
	}

	@Override
	public void delete(String u_id) throws Exception {
		UserDao dao=sqlSession.getMapper(UserDao.class);
		dao.delete(u_id);
	}
	
	@Override
	public void deactivate(String u_id) throws Exception {
		UserDao dao=sqlSession.getMapper(UserDao.class);
		dao.deactivate(u_id);
	}
	
	@Override
	public void update(UserDto dto) throws Exception {
		UserDao dao=sqlSession.getMapper(UserDao.class);
		dao.update(dto);
		
	}
	
	@Transactional
	@Override
	public void updateImg(String user_Img, String u_id) throws Exception {
		UserDao dao=sqlSession.getMapper(UserDao.class);
		dao.updateImg(user_Img, u_id);
	}
	
	@Override
	public List<UserDto> select_idInquiry_email(String u_name, String u_email) {
		UserDao dao=sqlSession.getMapper(UserDao.class);
		return dao.select_idInquiry_email(u_name, u_email);
	}

	@Override
	public List<UserDto> select_idInquiry_phone(String u_name, String u_phoneNumber) {
		UserDao dao=sqlSession.getMapper(UserDao.class);
		return dao.select_idInquiry_phone(u_name, u_phoneNumber);
	}

	@Override
	public boolean phoneNumber_compare(String u_id, String u_phoneNumber) {
		UserDao dao=sqlSession.getMapper(UserDao.class);
		return dao.phoneNumber_compare(u_id, u_phoneNumber);
	}
	@Override
	public boolean email_compare(String u_id, String u_email) {
		UserDao dao=sqlSession.getMapper(UserDao.class);
		return dao.email_compare(u_id, u_email);
	}
	
	@Override
	public void pw_update(String u_id, String encPassword) throws Exception {
		UserDao dao=sqlSession.getMapper(UserDao.class);
		dao.pw_update(u_id, encPassword);
		
	}
	
	@Override
	public String getU_pwByU_id(String userId) {
		UserDao dao=sqlSession.getMapper(UserDao.class);
		return dao.getU_pwByU_id(userId);
	}


	

}
