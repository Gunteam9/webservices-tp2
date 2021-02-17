package model;

public class User {
	
	private long id;
	private String name;
	private String password;
	
	public User(long id, String nom, String password) {
		super();
		this.id = id;
		this.name = nom;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return name;
	}

	public void setNom(String nom) {
		this.name = nom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
