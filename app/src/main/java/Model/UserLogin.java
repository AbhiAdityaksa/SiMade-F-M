package Model;

import com.google.gson.annotations.SerializedName;

public class UserLogin{

	@SerializedName("verified_status")
	private String verifiedStatus;

	@SerializedName("photo_profile")
	private String photoProfile;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("permission")
	private String permission;

	@SerializedName("worked_status")
	private String workedStatus;

	@SerializedName("token")
	private String token;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("contact")
	private String contact;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("identity_no")
	private String identityNo;

	@SerializedName("photo_identity")
	private String photoIdentity;

	@SerializedName("email")
	private String email;

	public void setVerifiedStatus(String verifiedStatus){
		this.verifiedStatus = verifiedStatus;
	}

	public String getVerifiedStatus(){
		return verifiedStatus;
	}

	public void setPhotoProfile(String photoProfile){
		this.photoProfile = photoProfile;
	}

	public String getPhotoProfile(){
		return photoProfile;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setPermission(String permission){
		this.permission = permission;
	}

	public String getPermission(){
		return permission;
	}

	public void setWorkedStatus(String workedStatus){
		this.workedStatus = workedStatus;
	}

	public String getWorkedStatus(){
		return workedStatus;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setContact(String contact){
		this.contact = contact;
	}

	public String getContact(){
		return contact;
	}

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

	public void setPhotoIdentity(String photoIdentity){
		this.photoIdentity = photoIdentity;
	}

	public String getPhotoIdentity(){
		return photoIdentity;
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
			"UserLogin{" + 
			"verified_status = '" + verifiedStatus + '\'' + 
			",photo_profile = '" + photoProfile + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",permission = '" + permission + '\'' + 
			",worked_status = '" + workedStatus + '\'' + 
			",token = '" + token + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",contact = '" + contact + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",identity_no = '" + identityNo + '\'' + 
			",photo_identity = '" + photoIdentity + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}