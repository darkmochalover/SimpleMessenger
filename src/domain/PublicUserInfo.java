package domain;

public class PublicUserInfo {

	private String user_name; 		// 이름 
	private String id; 				// 아이디 
	private String description; 	// 한 줄 소개 
	private String status;			 // 상태 
	
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public PublicUserInfo()
	{
		
	};
	
	public PublicUserInfo(	String user_name,		// 이름 
							String id,			// 아이디 
							String description, 	// 한 줄 소개 
							String status		 // 상태 
						)
	{
		this.user_name = user_name; 		// 이름 
		this.id = id; 				// 아이디 
		this.description = description; 	// 한 줄 소개 
		this.status = status;			 // 상태 
	}

}
