package Model;

import com.google.gson.annotations.SerializedName;

public class News{

	@SerializedName("valid")
	private String valid;

	@SerializedName("category_name")
	private String categoryName;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("etc")
	private String etc;

	@SerializedName("carity_id")
	private String carityId;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public void setValid(String valid){
		this.valid = valid;
	}

	public String getValid(){
		return valid;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
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

	public void setCarityId(String carityId){
		this.carityId = carityId;
	}

	public String getCarityId(){
		return carityId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"News{" + 
			"valid = '" + valid + '\'' + 
			",category_name = '" + categoryName + '\'' + 
			",category_id = '" + categoryId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",etc = '" + etc + '\'' + 
			",carity_id = '" + carityId + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}