package com.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.board.dto.UserDto;

public interface UserDao {
	public List<UserDto> selectAll() throws Exception;
	public UserDto selectUser(String u_id) throws Exception;
	
	public List<UserDto> select_idInquiry_email(@Param("u_name") String u_name, @Param("u_email") String u_email);
	public List<UserDto> select_idInquiry_phone(@Param("u_name") String u_name, @Param("u_phoneNumber") String u_phoneNumber);
	
	public void insert(UserDto dto) throws Exception;
	
	public void delete(String u_id) throws Exception;
	
	public void deactivate(String u_id) throws Exception;
	
	public void update(UserDto dto) throws Exception;
	public void updateImg(@Param("user_Img") String user_Img, @Param("u_id") String u_id) throws Exception;
	
	
	public boolean phoneNumber_compare(@Param("u_id")String u_id, @Param("u_phoneNumber")String u_phoneNumber);
	public boolean email_compare(@Param("u_id")String u_id, @Param("u_email")String u_email);
	
	public void pw_update(@Param("u_id")String u_id, @Param("encPassword")String encPassword) throws Exception;
	
	public String getU_pwByU_id(String userId);
}
