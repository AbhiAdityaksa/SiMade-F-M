package Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DetailSumCarityResponse implements Parcelable {

	@SerializedName("verified_status")
	private String verifiedStatus;

	@SerializedName("nominal")
	private String nominal;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("etc")
	private String etc;

	@SerializedName("carity_id")
	private String carityId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("nama_warga")
	private String namaWarga;

	@SerializedName("id")
	private int id;

	@SerializedName("invoice")
	private String invoice;

	protected DetailSumCarityResponse(Parcel in) {
		verifiedStatus = in.readString();
		nominal = in.readString();
		updatedAt = in.readString();
		userId = in.readString();
		etc = in.readString();
		carityId = in.readString();
		createdAt = in.readString();
		namaWarga = in.readString();
		id = in.readInt();
		invoice = in.readString();
	}

	public static final Creator<DetailSumCarityResponse> CREATOR = new Creator<DetailSumCarityResponse>() {
		@Override
		public DetailSumCarityResponse createFromParcel(Parcel in) {
			return new DetailSumCarityResponse(in);
		}

		@Override
		public DetailSumCarityResponse[] newArray(int size) {
			return new DetailSumCarityResponse[size];
		}
	};

	public void setVerifiedStatus(String verifiedStatus){
		this.verifiedStatus = verifiedStatus;
	}

	public String getVerifiedStatus(){
		return verifiedStatus;
	}

	public void setNominal(String nominal){
		this.nominal = nominal;
	}

	public String getNominal(){
		return nominal;
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

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setNamaWarga(String namaWarga){
		this.namaWarga = namaWarga;
	}

	public String getNamaWarga(){
		return namaWarga;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setInvoice(String invoice){
		this.invoice = invoice;
	}

	public String getInvoice(){
		return invoice;
	}

	@Override
 	public String toString(){
		return 
			"DetailSumCarityResponse{" + 
			"verified_status = '" + verifiedStatus + '\'' + 
			",nominal = '" + nominal + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",etc = '" + etc + '\'' + 
			",carity_id = '" + carityId + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",nama_warga = '" + namaWarga + '\'' + 
			",id = '" + id + '\'' + 
			",invoice = '" + invoice + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(verifiedStatus);
		parcel.writeString(nominal);
		parcel.writeString(updatedAt);
		parcel.writeString(userId);
		parcel.writeString(etc);
		parcel.writeString(carityId);
		parcel.writeString(createdAt);
		parcel.writeString(namaWarga);
		parcel.writeInt(id);
		parcel.writeString(invoice);
	}
}