package Model;

import com.google.gson.annotations.SerializedName;

public class VillagerWorkerAdmin{

	@SerializedName("worked")
	private int worked;

	@SerializedName("villager")
	private int villager;

	public void setWorked(int worked){
		this.worked = worked;
	}

	public int getWorked(){
		return worked;
	}

	public void setVillager(int villager){
		this.villager = villager;
	}

	public int getVillager(){
		return villager;
	}

	@Override
 	public String toString(){
		return 
			"VillagerWorkerAdmin{" + 
			"worked = '" + worked + '\'' + 
			",villager = '" + villager + '\'' + 
			"}";
		}
}