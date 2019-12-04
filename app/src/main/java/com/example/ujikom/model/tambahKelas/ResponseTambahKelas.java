package com.example.ujikom.model.tambahKelas;

import com.google.gson.annotations.SerializedName;

public class ResponseTambahKelas{

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

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
			"ResponseTambahKelas{" + 
			"kelas = '" + kelas + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}