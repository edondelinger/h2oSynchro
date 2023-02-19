package com.example.h2osynchro.modele;

public class Annee {
	private long numA;
	private long idS;
	private String introA;
	private String conclusionA;
	
	
	public Annee(long numA, long idS, String introA, String conclusionA) {
		this.numA = numA;
		this.idS = idS;
		this.introA = introA;
		this.conclusionA = conclusionA;
	}

	public Annee(long numA, long idS) {
		this.numA = numA;
		this.idS = idS;
		this.introA = "";
		this.conclusionA = "";
	}
	
	public long getNumA() {
		return numA;
	}
	
	public void setNumA(long numA) {
		this.numA = numA;
	}
	
	public long getIdS() {
		return idS;
	}

	public void setIdS(long idS) {
		this.idS = idS;
	}
	
	public String getIntroA() {
		return introA;
	}
	
	public void setIntroA(String introA) {
		this.introA = introA;
	}
	
	public String getConclusionA() {
		return conclusionA;
	}
	
	public void setConclusionA(String conclusionA) {
		this.conclusionA = conclusionA;
	}
	
	public String toString(){
		return "numA="+numA+", idS="+idS+", introA="+introA+", conclusionA="+conclusionA;
	}
}
