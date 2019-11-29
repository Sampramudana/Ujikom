package com.example.ujikom.model.register;

import com.google.gson.annotations.SerializedName;

public class ResponseRegister{

	@SerializedName("vsusername")
	private String vsusername;

	@SerializedName("nama")
	private String nama;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

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
			"ResponseRegister{" + 
			"vsusername = '" + vsusername + '\'' + 
			",nama = '" + nama + '\'' + 
			",kelas = '" + kelas + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}