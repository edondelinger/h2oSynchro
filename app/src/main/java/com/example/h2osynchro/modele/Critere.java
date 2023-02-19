package com.example.h2osynchro.modele;

public class Critere {
	private long idC;
	private String libelleC;
	private String uniteC;
	
	
	public Critere(long idC, String libelleC, String uniteC) {
		this.idC = idC;
		this.libelleC = libelleC;
		this.uniteC = uniteC;
	}
	
	public Critere(String libelleC, String uniteC) {
		this.idC = -1;
		this.libelleC = libelleC;
		this.uniteC = uniteC;
	}
	
	public long getIdC() {
		return idC;
	}
	
	public void setIdC(long idC) {
		this.idC = idC;
	}
	
	public String getLibelleC() {
		return libelleC;
	}
	
	public void setLibelleC(String libelleC) {
		this.libelleC = libelleC;
	}
	
	public String getUniteC() {
		return uniteC;
	}
	
	public void setUniteC(String uniteC) {
		this.uniteC = uniteC;
	}

	public String toString(){
		return "idC="+idC+",libelleC="+libelleC+",uniteC="+uniteC;
	}
}
