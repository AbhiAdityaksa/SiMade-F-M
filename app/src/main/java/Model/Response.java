package Model;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public Response(String name) {
		this.getName();
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
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

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Response{" +
			"updated_at = '" + updatedAt + '\'' +
			",name = '" + name + '\'' +
			",created_at = '" + createdAt + '\'' +
			",id = '" + id + '\'' +
			"message = '" + message + '\'' +
			",status = '" + status + '\'' + 
			"}";
		}

}