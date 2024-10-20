package com.board.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class CommentDto {
	
	private int comm_id;
	private String comm_content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date comm_date;
//	private int replyid;
	private int b_id;
	private String u_id;
	private String comm_del="N";
	private Integer parent_id;
	
	
	public CommentDto() {}
	
	public CommentDto(int comm_id, String comm_content, Date comm_date, int b_id, String u_id, String comm_del,
			Integer parent_id) {
		super();
		this.comm_id = comm_id;
		this.comm_content = comm_content;
		this.comm_date = comm_date;
		this.b_id = b_id;
		this.u_id = u_id;
		this.comm_del = comm_del;
		this.parent_id = parent_id;
	}
	
	
	public int getComm_id() {
		return comm_id;
	}
	public void setComm_id(int comm_id) {
		this.comm_id = comm_id;
	}
	public String getComm_content() {
		return comm_content;
	}
	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}
	public Date getComm_date() {
		return comm_date;
	}
	public void setComm_date(Date comm_date) {
		this.comm_date = comm_date;
	}
//	public int getReplyid() {
//		return replyid;
//	}
//	public void setReplyid(int replyid) {
//		this.replyid = replyid;
//	}
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getComm_del() {
		return comm_del;
	}
	public void setComm_del(String comm_del) {
		this.comm_del = comm_del;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	
	
	@Override
	public String toString() {
		return "CommentDto [comm_id=" + comm_id + ", comm_content=" + comm_content + ", comm_date=" + comm_date
				+ ", b_id=" + b_id + ", u_id=" + u_id + ", comm_del=" + comm_del
				+ ", parent_id=" + parent_id + "]";
	}
	
	
	
	
	
	
	
	
}
