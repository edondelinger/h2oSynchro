package com.example.h2osynchro.modele;

public class Mois {
	private long numM;
	private long numA;
	private long idS;
	private String remarquesM;	
	
	public Mois(long numM, long numA, long idS, String remarquesM) {
		this.numM = numM;
		this.numA = numA;
		this.idS = idS;
		this.remarquesM = remarquesM;
	}

	public Mois(long numM, long numA, long idS) {
		this.numM = numM;
		this.numA = numA;
		this.idS = idS;
		this.remarquesM = "";
	}
	
	public long getNumM() {
		return numM;
	}

	public void setNumM(long numM) {
		this.numM = numM;
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

	public String getRemarquesM() {
		return remarquesM;
	}

	public void setRemarquesM(String remarqueM) {
		this.remarquesM = remarqueM;
	}


	public String toString(){
		return "numM="+numM+", numA="+numA+", idS="+idS+", remarquesM="+remarquesM;
	}
}
