package entity;

public class User {

private String id;
	
	private String phone;
	
	private String UserName;
	
	private String password;
	
	private UserStatus status;
	
	private UserPermission permission;
	
	public enum UserStatus {BLOCKED , CONNECTED , DISCONNECTED}
	
	public enum UserPermission {COMPANY_MANAGER , STORE_MANAGER , EXPERT ,
		CUSTOMER_SERVICE_WORKER , CUSTOMER , DATA_COMPANY_MANAGER}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public UserPermission getPermission() {
		return permission;
	}

	public void setPermission(UserPermission permission) {
		this.permission = permission;
	}
}