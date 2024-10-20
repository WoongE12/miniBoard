package com.board.service;

import java.util.ArrayList;

import com.board.dto.AuthoritiesDto;
import com.board.dto.UserDto;

public interface AdminService {
	
	public ArrayList<UserDto> selectAll() throws Exception;
	
	public ArrayList<AuthoritiesDto> selectAllAuthorities() throws Exception;
	
	// 권한 업데이트 메서드 추가
	public void updateAuthority(AuthoritiesDto authoritiesDto) throws Exception;
	// 계정 상태 업데이트 메서드 추가
	public void updateStatus(String u_id, int enabled) throws Exception;

	public void updateUser(UserDto userDto) throws Exception;


	

	

	
	
	
	
	
}
