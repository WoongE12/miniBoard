package com.board.dao;

import java.util.ArrayList;
import java.util.Map;

import com.board.dto.AuthoritiesDto;
import com.board.dto.UserDto;

public interface AdminDao {
	public ArrayList<UserDto> selectAll() throws Exception;
	public ArrayList<AuthoritiesDto> selectAllAuthorities() throws Exception;
	public void updateAuthority(AuthoritiesDto authoritiesDto)  throws Exception;
	public void updateStatus(Map<String, Object> params) throws Exception;
	public void updateUser(UserDto userDto) throws Exception;
	

	
	
}
