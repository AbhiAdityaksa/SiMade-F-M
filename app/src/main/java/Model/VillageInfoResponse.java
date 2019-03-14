package Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class VillageInfoResponse implements Parcelable {

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

	@SerializedName("picture")
	private String picture;

	protected VillageInfoResponse(Parcel in) {
		valid = in.readString();
		categoryName = in.readString();
		categoryId = in.readString();
		updatedAt = in.readString();
		userId = in.readString();
		etc = in.readString();
		carityId = in.readString();
		name = in.readString();
		createdAt = in.readString();
		id = in.readInt();
		picture = in.readString();
	}

	public static final Creator<VillageInfoResponse> CREATOR = new Creator<VillageInfoResponse>() {
		@Override
		public VillageInfoResponse createFromParcel(Parcel in) {
			return new VillageInfoResponse(in);
		}

		@Override
		public VillageInfoResponse[] newArray(int size) {
			return new VillageInfoResponse[size];
		}
	};

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

	public void setPicture(String picture){
		this.picture = picture;
	}

	public String getPicture(){
		return picture;
	}

	@Override
 	public String toString(){
		return 
			"VillageInfoResponse{" + 
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
			",picture = '" + picture + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(valid);
		parcel.writeString(categoryName);
		parcel.writeString(categoryId);
		parcel.writeString(updatedAt);
		parcel.writeString(userId);
		parcel.writeString(etc);
		parcel.writeString(carityId);
		parcel.writeString(name);
		parcel.writeString(createdAt);
		parcel.writeInt(id);
		parcel.writeString(picture);
	}
}