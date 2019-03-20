package Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SumCarityResponse implements Parcelable {

	@SerializedName("valid")
	private String valid;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("total_carity")
	private String totalCarity;

	protected SumCarityResponse(Parcel in) {
		valid = in.readString();
		name = in.readString();
		id = in.readInt();
		totalCarity = in.readString();
	}

	public static final Creator<SumCarityResponse> CREATOR = new Creator<SumCarityResponse>() {
		@Override
		public SumCarityResponse createFromParcel(Parcel in) {
			return new SumCarityResponse(in);
		}

		@Override
		public SumCarityResponse[] newArray(int size) {
			return new SumCarityResponse[size];
		}
	};

	public void setValid(String valid){
		this.valid = valid;
	}

	public String getValid(){
		return valid;
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

	public void setTotalCarity(String totalCarity){
		this.totalCarity = totalCarity;
	}

	public String getTotalCarity(){
		return totalCarity;
	}

	@Override
 	public String toString(){
		return 
			"SumCarityResponse{" + 
			"valid = '" + valid + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",total_carity = '" + totalCarity + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(valid);
		parcel.writeString(name);
		parcel.writeInt(id);
		parcel.writeString(totalCarity);
	}
}