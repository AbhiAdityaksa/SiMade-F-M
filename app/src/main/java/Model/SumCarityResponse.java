package Model;

import com.google.gson.annotations.SerializedName;

public class SumCarityResponse{

	@SerializedName("valid")
	private String valid;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("total_carity")
	private String totalCarity;

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
}