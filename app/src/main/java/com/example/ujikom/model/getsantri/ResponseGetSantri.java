package com.example.ujikom.model.getsantri;

import com.google.gson.annotations.SerializedName;

public class ResponseGetSantri{

	@SerializedName("nama_santri")
	private String namaSantri;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("id")
	private String id;

	public void setNamaSantri(String namaSantri){
		this.namaSantri = namaSantri;
	}

	public String getNamaSantri(){
		return namaSantri;
	}

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
			"ResponseGetSantri{" + 
			"nama_santri = '" + namaSantri + '\'' + 
			",kelas = '" + kelas + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}