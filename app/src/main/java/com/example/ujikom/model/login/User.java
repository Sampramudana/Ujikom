package com.example.ujikom.model.login;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("vsusername")
	private String vsusername;

	@SerializedName("nama")
	private String nama;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("vspassword")
	private String vspassword;

	@SerializedName("id_user")
	private String idUser;

	public void setVsusername(String vsusername){
		this.vsusername = vsusername;
	}

	public String getVsusername(){
		return vsusername;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setKelas(String kelas){
		this.kelas = kelas;
	}

	public String getKelas(){
		return kelas;
	}

	public void setVspassword(String vspassword){
		this.vspassword = vspassword;
	}

	public String getVspassword(){
		return vspassword;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"vsusername = '" + vsusername + '\'' + 
			",nama = '" + nama + '\'' + 
			",kelas = '" + kelas + '\'' + 
			",vspassword = '" + vspassword + '\'' + 
			",id_user = '" + idUser + '\'' + 
			"}";
		}
}