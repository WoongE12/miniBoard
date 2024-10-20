package com.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.BoardDao;
import com.board.dto.BoardDto;
import com.board.vo.BoardVo;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void create(BoardDto board) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.create(board);
	}
	
	@Override
	public BoardDto read(int b_id) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		return dao.read(b_id);
	}

	@Override
	public void update(BoardDto board) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.update(board);
	}

	@Override
	public void delete(int b_id) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.delete(b_id);
	}
	
	@Override
	public List<BoardDto> listAll() throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		List<BoardDto> dtos = dao.listAll();
		return dtos;
	}
	
//-------------------------------------------------------------------------

	@Override
	public void b_viewUpdate(int b_id) throws Exception{
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		dao.b_viewUpdate(b_id);
	}
	
	@Override
	public List<BoardDto> listSearch(BoardVo vo) throws Exception{
		BoardDao dao=sqlSession.getMapper(BoardDao.class);
		return dao.listSearch(vo);
	}
	
	@Override
    public int getTotalCountByCategory(BoardVo vo) throws Exception {
        BoardDao dao = sqlSession.getMapper(BoardDao.class);
        return dao.getTotalCountByCategory(vo);
    }
//--------------------------------------------------------------------------
	@Override
	public List<BoardDto> getUserPosts(String u_id) throws Exception {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		return dao.getUserPosts(u_id);
	}
	
	@Override
	public List<BoardDto> getLatestPosts(int limit) throws Exception {
	    BoardDao dao = sqlSession.getMapper(BoardDao.class);
	    return dao.getLatestPosts(limit); // DAO 호출
	}
	
	@Override
    public List<BoardDto> getPostsByCategory(String category) throws Exception {
        BoardDao dao = sqlSession.getMapper(BoardDao.class);
        return dao.getPostsByCategory(category); // DAO 호출
    }
	
	@Override
	public List<BoardDto> getPostsByViewCount(int limit) throws Exception {
	    return sqlSession.selectList("getPostsByViewCount", limit); // SQL 쿼리 호출
	}
}
