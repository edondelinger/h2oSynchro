package com.example.h2osynchro.utilitaire;

import com.example.h2osynchro.modele.Relever;

import java.util.ArrayList;



// Force la classe qui l'implemente a avoir les methodes indiquees ci-dessous
// Cette methode sera appelee lorsque la tache asynchrone sera terminee
public interface EventAsyncRelever { 
	public void onTacheTerminee(String resultat);
	public void onTacheTerminee(ArrayList<Relever> resultat);
	public void onTacheTerminee(Relever resultat);

}
