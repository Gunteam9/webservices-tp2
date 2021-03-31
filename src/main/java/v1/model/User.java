package v1.model;

public class User {
	
	private long id;
	private String name;
	private String password;
	private boolean admin = false;

	public User() {
		
	}
	
	public User(long id, String nom, String password) {
		super();
		this.id = id;
		this.name = nom;
		this.password = password;
	}
	
	public User(long id, String nom, String password, boolean isAdmin) {
		super();
		this.id = id;
		this.name = nom;
		this.password = password;
		this.admin = isAdmin;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}
	
	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}


}
