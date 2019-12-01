package com.example.ujikom.model.tambahsantri;

import com.google.gson.annotations.SerializedName;

public class ResponseTambahSantri{

	@SerializedName("nama_santri")
	private String namaSantri;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

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

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseTambahSantri{" + 
			"nama_santri = '" + namaSantri + '\'' + 
			",kelas = '" + kelas + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}