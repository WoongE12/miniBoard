package com.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.AdminDao;
import com.board.dto.AuthoritiesDto;
import com.board.dto.UserDto;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private SqlSession sqlSession;
	
	
	
	@Override
	public ArrayList<UserDto> selectAll() throws Exception {
		AdminDao dao=sqlSession.getMapper(AdminDao.class);
		System.out.println(dao.selectAll());
		return dao.selectAll();
	}
	
	@Override
	public ArrayList<AuthoritiesDto> selectAllAuthorities() throws Exception {
		AdminDao dao=sqlSession.getMapper(AdminDao.class);
		System.out.println(dao.selectAllAuthorities());
		return dao.selectAllAuthorities();
	}

	 // 권한 업데이트 메서드 추가
	@Override
    public void updateAuthority(AuthoritiesDto authoritiesDto) throws Exception {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.updateAuthority(authoritiesDto);
    }
	 // 계정 상태 업데이트 메서드 추가
	@Override
	public void updateStatus(String u_id, int enabled) throws Exception {
		AdminDao dao = sqlSession.getMapper(AdminDao.class);
		 Map<String, Object> params = new HashMap<>();
		 params.put("u_id", u_id);
		 params.put("enabled", enabled);
		 dao.updateStatus(params);
		
	}

	@Override
	public void updateUser(UserDto userDto) throws Exception {
		AdminDao dao = sqlSession.getMapper(AdminDao.class);
		dao.updateUser(userDto);
		
	}

	
	

	

	
}
