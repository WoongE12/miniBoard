package com.board.service;

import java.util.List;

import com.board.dto.UserDto;

public interface UserService {
public void insert(UserDto dto) throws Exception;
	
	public UserDto selectUser(String u_id) throws Exception;
	
	public List<UserDto> selectAll() throws Exception;
	public void delete(String u_id) throws Exception;
	
	public void deactivate(String u_id) throws Exception;
	
	public void update(UserDto dto) throws Exception;
	public void updateImg(String user_Img, String u_id) throws Exception;
	
	public List<UserDto> select_idInquiry_email(String u_name, String u_email);
	public List<UserDto> select_idInquiry_phone( String u_name, String u_phoneNumber);
	
	public boolean phoneNumber_compare(String u_id, String u_phoneNumber);
	public boolean email_compare(String u_id, String u_email);
	
	public void pw_update(String u_id, String encPassword) throws Exception;

	public String getU_pwByU_id(String userId);
}
