package com.example.h2osynchro.modele;

public class Station {
	private long idS;
	private String nomS;
	
	
	public Station(long idS, String nomS) {
		this.idS = idS;
		this.nomS = nomS;
	}
	
	public Station(String nomS) {
		this.idS = -1;
		this.nomS = nomS;
	}
	
	public long getIdS() {
		return idS;
	}
	
	public void setIdS(long idS) {
		this.idS = idS;
	}
	
	public String getNomS() {
		return nomS;
	}
	
	public void setNomS(String nomS) {
		this.nomS = nomS;
	}

	public String toString(){
		return "idS="+idS+",nomS="+nomS;
	}
}
