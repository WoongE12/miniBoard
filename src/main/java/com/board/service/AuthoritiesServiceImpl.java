package com.board.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.AuthoritiesDao;
import com.board.dto.AuthoritiesDto;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(AuthoritiesDto dto) throws Exception {
		AuthoritiesDao dao=sqlSession.getMapper(AuthoritiesDao.class);
		dao.insert(dto);		
	}

}
