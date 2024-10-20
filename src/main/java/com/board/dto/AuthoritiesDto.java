package com.board.dto;

public class AuthoritiesDto {
	// u_id, authorities
	private String u_id;
	private String authority;
	
	public AuthoritiesDto() {}
	
	public AuthoritiesDto(String u_id, String authority) {
		super();
		this.u_id = u_id;
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "AuthoritiesDto [u_id=" + u_id + ", authority=" + authority + "]";
	}
	
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	
	
}
