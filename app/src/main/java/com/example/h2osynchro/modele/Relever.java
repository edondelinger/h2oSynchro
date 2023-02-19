package com.example.h2osynchro.modele;

public class Relever {
	private long numM;
	private long numA;
	private long idS;
	private long idC;
	private long numJ;
	private int qteEntreeR;
	private int qteSortieR;
	
	
	public Relever(long numM, long numA, long idS, long idC, long numJ, int qteEntreeR, int qteSortieR) {
		this.numM = numM;
		this.numA = numA;
		this.idS = idS;
		this.idC = idC;
		this.numJ = numJ;
		this.qteEntreeR = qteEntreeR;
		this.qteSortieR = qteSortieR;
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

	public long getIdC() {
		return idC;
	}

	public void setIdC(long idC) {
		this.idC = idC;
	}

	public long getNumJ() {
		return numJ;
	}

	public void setNumJ(long numJ) {
		this.numJ = numJ;
	}

	public int getQteEntreeR() {
		return qteEntreeR;
	}

	public void setQteEntreeR(int qteEntreeR) {
		this.qteEntreeR = qteEntreeR;
	}

	public int getQteSortieR() {
		return qteSortieR;
	}

	public void setQteSortieR(int qteSortieR) {
		this.qteSortieR = qteSortieR;
	}

	public String toString(){
		return "numA="+numA+",idS="+idS+",idC="+idC+",numJ="+numJ+",qteEntreeR="+qteEntreeR+",qteSortieR="+qteSortieR;
	}
}
