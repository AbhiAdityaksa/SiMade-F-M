package Model;

import com.google.gson.annotations.SerializedName;

public class HistoryResponse{

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("nama_user")
	private String namaUser;

	@SerializedName("nama_admin")
	private String namaAdmin;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setNamaUser(String namaUser){
		this.namaUser = namaUser;
	}

	public String getNamaUser(){
		return namaUser;
	}

	public void setNamaAdmin(String namaAdmin){
		this.namaAdmin = namaAdmin;
	}

	public String getNamaAdmin(){
		return namaAdmin;
	}

	@Override
 	public String toString(){
		return 
			"HistoryResponse{" + 
			"created_at = '" + createdAt + '\'' + 
			",nama_user = '" + namaUser + '\'' + 
			",nama_admin = '" + namaAdmin + '\'' + 
			"}";
		}
}