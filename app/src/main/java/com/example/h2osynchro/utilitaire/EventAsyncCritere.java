package com.example.h2osynchro.utilitaire;

import com.example.h2osynchro.modele.Critere;

import java.util.ArrayList;



// Force la classe qui l'implemente a avoir les methodes indiquees ci-dessous
// Cette methode sera appelee lorsque la tache asynchrone sera terminee
public interface EventAsyncCritere { 
	public void onTacheTerminee(String resultat);
	public void onTacheTerminee(ArrayList<Critere> resultat);
	public void onTacheTerminee(Critere resultat);

}
