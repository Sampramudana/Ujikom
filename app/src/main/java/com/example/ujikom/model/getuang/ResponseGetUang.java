package com.example.ujikom.model.getuang;

import com.google.gson.annotations.SerializedName;

public class ResponseGetUang{

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

	@SerializedName("created")
	private String created;

	@SerializedName("id_uang")
	private String idUang;

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

	public void setCreated(String created){
		this.created = created;
	}

	public String getCreated(){
		return created;
	}

	public void setIdUang(String idUang){
		this.idUang = idUang;
	}

	public String getIdUang(){
		return idUang;
	}

	@Override
 	public String toString(){
		return 
			"ResponseGetUang{" + 
			"id_santri = '" + idSantri + '\'' + 
			",selisih_uang = '" + selisihUang + '\'' + 
			",keterangan = '" + keterangan + '\'' + 
			",uang = '" + uang + '\'' + 
			",status_uang = '" + statusUang + '\'' + 
			",created = '" + created + '\'' + 
			",id_uang = '" + idUang + '\'' + 
			"}";
		}
}