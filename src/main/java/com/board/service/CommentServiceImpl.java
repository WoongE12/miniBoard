package com.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.CommentDao;
import com.board.dto.CommentDto;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void commentCreate(CommentDto dto) throws Exception{
		CommentDao dao = sqlSession.getMapper(CommentDao.class);
		dao.commentCreate(dto);
	}
	
	@Override
	public void reCommentCreate(CommentDto dto) throws Exception{
		CommentDao dao = sqlSession.getMapper(CommentDao.class);
		dao.reCommentCreate(dto);
	}
	
	@Override
	public void commentUpdate(CommentDto dto) throws Exception{
		CommentDao dao = sqlSession.getMapper(CommentDao.class);
		dao.commentUpdate(dto);
	}
	
	@Override
	public void commentDelete(int comm_id) throws Exception{
		CommentDao dao = sqlSession.getMapper(CommentDao.class);
		dao.commentDelete(comm_id);
	}
	
	@Override
	public List<CommentDto> commentList(int b_id) throws Exception{
		CommentDao dao = sqlSession.getMapper(CommentDao.class);
		return dao.commentList(b_id);
	}
	
	@Override
	public int commentCount(int comm_id) throws Exception{
		CommentDao dao = sqlSession.getMapper(CommentDao.class);
		return dao.commentCount(comm_id);
	}

	@Override
	public List<CommentDto> selectReply(int comm_id) throws Exception {
		CommentDao dao = sqlSession.getMapper(CommentDao.class);
		return dao.selectReply(comm_id);
	}
	
	@Override
    public CommentDto getCommentById(int comm_id) throws Exception {
        CommentDao dao = sqlSession.getMapper(CommentDao.class);
        return dao.getCommentById(comm_id);
    }

}
