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
		IG.miseAJourAffichage();
	}

}
