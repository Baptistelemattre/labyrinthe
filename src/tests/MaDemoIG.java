package tests;
import grafix.interfaceGraphique.IG;

public class MaDemoIG {

	public static void main(String[] args) {
		// set param
		Object param[];
		param = IG.saisirParametres();
		int nombreDeJoueur = ((Integer)param[0]).intValue();
		IG.creerFenetreJeu("Demo groupe bricodeur",nombreDeJoueur);
		IG.rendreVisibleFenetreJeu();
		for (int i = 0;i<nombreDeJoueur;i++) {
			IG.changerNomJoueur(i,((String)param[(i*3)+1].toString())+" ("+((String)param[(i*3)+2])+")");
			IG.changerImageJoueur(i, ((int)param[(i*3)+3]));
			
		}
		if (nombreDeJoueur == 2) {
			for (int i=0;i<6;i++){
				IG.changerObjetJoueur(0,i,i);
				IG.changerObjetJoueur(1,i+7,i);
			}
		}
		if (nombreDeJoueur == 3) {
			for (int i=0;i<6;i++){
				IG.changerObjetJoueur(0,i,i);
				IG.changerObjetJoueur(1,i+7,i);
				IG.changerObjetJoueur(2,i+12,i);
			}
		}
		IG.changerPieceHorsPlateau(1, 0);
		IG.miseAJourAffichage();
		
	}

}
