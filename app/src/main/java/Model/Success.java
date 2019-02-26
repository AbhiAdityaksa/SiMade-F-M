package Model;

import com.google.gson.annotations.SerializedName;

public class Success{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("identity_no")
	private String identityNo;

	@SerializedName("email")
	private String email;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setIdentityNo(String identityNo){
		this.identityNo = identityNo;
	}

	public String getIdentityNo(){
		return identityNo;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"Success{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",identity_no = '" + identityNo + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}