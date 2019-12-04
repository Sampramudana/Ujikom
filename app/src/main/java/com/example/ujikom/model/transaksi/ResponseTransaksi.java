package com.example.ujikom.model.transaksi;

import com.google.gson.annotations.SerializedName;

public class ResponseTransaksi{

	@SerializedName("id_santri")
	private String idSantri;

	@SerializedName("selisih_uang")
	private String selisihUang;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("uang")
	private String uang;

	@SerializedName("status_uang")
	private String statusUang;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setIdSantri(String idSantri){
		this.idSantri = idSantri;
	}

	public String getIdSantri(){
		return idSantri;
	}

	public void setSelisihUang(String selisihUang){
		this.selisihUang = selisihUang;
	}

	public String getSelisihUang(){
		return selisihUang;
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setUang(String uang){
		this.uang = uang;
	}

	public String getUang(){
		return uang;
	}

	public void setStatusUang(String statusUang){
		this.statusUang = statusUang;
	}

	public String getStatusUang(){
		return statusUang;
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
			"ResponseTransaksi{" + 
			"id_santri = '" + idSantri + '\'' + 
			",selisih_uang = '" + selisihUang + '\'' + 
			",keterangan = '" + keterangan + '\'' + 
			",uang = '" + uang + '\'' + 
			",status_uang = '" + statusUang + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}