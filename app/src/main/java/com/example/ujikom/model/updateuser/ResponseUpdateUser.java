package com.example.ujikom.model.updateuser;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdateUser{

	@SerializedName("password")
	private String password;

	@SerializedName("nama")
	private String nama;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	@SerializedName("username")
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
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

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"ResponseUpdateUser{" + 
			"password = '" + password + '\'' + 
			",nama = '" + nama + '\'' + 
			",kelas = '" + kelas + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}