package Model;

import com.google.gson.annotations.SerializedName;

public class UserReport{

	@SerializedName("category_name")
	private String categoryName;

	@SerializedName("verified_status")
	private String verifiedStatus;

	@SerializedName("photo_profile")
	private String photoProfile;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("worked_status")
	private String workedStatus;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("etc")
	private String etc;

	@SerializedName("report_category_id")
	private String reportCategoryId;

	@SerializedName("contact")
	private String contact;

	@SerializedName("accepted_status")
	private String acceptedStatus;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMessage() {
		return message;
	}

	public void setMessage(Integer message) {
		this.message = message;
	}

	@SerializedName("status")
	private Integer status;

	@SerializedName("message")
	private Integer message;




	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@SerializedName("error")
	private String error;

	@SerializedName("success")
	private Success success;

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

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

	public void setWorkedStatus(String workedStatus){
		this.workedStatus = workedStatus;
	}

	public String getWorkedStatus(){
		return workedStatus;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setEtc(String etc){
		this.etc = etc;
	}

	public String getEtc(){
		return etc;
	}

	public void setReportCategoryId(String reportCategoryId){
		this.reportCategoryId = reportCategoryId;
	}

	public String getReportCategoryId(){
		return reportCategoryId;
	}

	public void setContact(String contact){
		this.contact = contact;
	}

	public String getContact(){
		return contact;
	}

	public void setAcceptedStatus(String acceptedStatus){
		this.acceptedStatus = acceptedStatus;
	}

	public String getAcceptedStatus(){
		return acceptedStatus;
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
			"UserReport{" + 
			"category_name = '" + categoryName + '\'' + 
			",verified_status = '" + verifiedStatus + '\'' + 
			",photo_profile = '" + photoProfile + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",worked_status = '" + workedStatus + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",etc = '" + etc + '\'' + 
			",report_category_id = '" + reportCategoryId + '\'' + 
			",contact = '" + contact + '\'' + 
			",accepted_status = '" + acceptedStatus + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",identity_no = '" + identityNo + '\'' + 
			",photo_identity = '" + photoIdentity + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}