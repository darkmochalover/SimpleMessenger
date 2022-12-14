package domain;

import java.awt.Button;
import java.util.ArrayList;

import javax.swing.JTextField;

public class FollowInfo {
	
	private String target_propic; /* 팔로워들의 프로필 사진 */ 
	private String target_name; /* 팔로워의 이름 */ 
	private String target_id; 		/* 팔로워들의 아이디 */ 
	private String target_desc; /* 팔로워들의 한 줄 소개 */
	
	public FollowInfo()
	{
		
	}
	
	public String getTarget_propic() {
		return target_propic;
	}

	public void setTarget_propic(String target_propic) {
		this.target_propic = target_propic;
	}

	public String getTarget_name() {
		return target_name;
	}

	public void setTarget_name(String target_name) {
		this.target_name = target_name;
	}

	public String getTarget_id() {
		return target_id;
	}

	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}

	public String getTarget_desc() {
		return target_desc;
	}

	public void setTarget_desc(String target_desc) {
		this.target_desc = target_desc;
	}

	public FollowInfo(
			String target_propic, /* 팔로워들의 프로필 사진 */ 
			String target_name, /* 팔로워의 이름 */ 
			String target_id, 		/* 팔로워들의 아이디 */ 
			String target_desc /* 팔로워들의 한 줄 소개 */
	)
	{
		this.target_propic = target_propic; 		/* 팔로워들의 프로필 사진 */ 
		this.target_name = target_name; 			/* 팔로워의 이름 */ 
		this.target_id = target_id; 				/* 팔로워들의 아이디 */ 
		this.target_desc = target_desc; 			/* 팔로워들의 한 줄 소개 */
	}
	

}
