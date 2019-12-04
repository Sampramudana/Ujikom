package com.example.ujikom.model.getAdminKelas;

import com.google.gson.annotations.SerializedName;

public class ResponseGetKelas{

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("id")
	private String id;

	public void setKelas(String kelas){
		this.kelas = kelas;
	}

	public String getKelas(){
		return kelas;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"ResponseGetKelas{" + 
			"kelas = '" + kelas + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}