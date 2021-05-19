package tests;
import grafix.interfaceGraphique.IG;

public class MaDemoIG {

	public static void main(String[] args) {
		// set param
		Object param[];
		param = IG.saisirParametres();
		int nombreDeJoueur = (Integer) param[0];
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
		for (int i = 0; i<7;i++) {
			for (int j = 0; j<7;j++) {
				IG.changerPiecePlateau(i, j, 2, 0);
			}
		}
		for (int i =0;i<18;i++) {
			if (i<7) {
				IG.placerObjetPlateau(i, 0, i);
			}else if (i<14) {
				IG.placerObjetPlateau(i, 1, i-7);
			}else {
				IG.placerObjetPlateau(i, 2, i-14);
			}
		}
		String message[]={
				"",
				"Bonjour !",
				"Cliquez pour continuer",
				""
		};
		IG.afficherMessage(message);
		IG.placerJoueurPrecis(0, 3, 0,1,0);
		IG.placerJoueurPrecis(1, 3, 6,1,2);
		IG.changerPieceHorsPlateau(1, 0);
		IG.miseAJourAffichage();
		for (int i = 0;i<4;i++) {
			IG.attendreClic();
			IG.changerObjetJoueurAvecTransparence(0, i, i);
			IG.enleverObjetPlateau(0, i);
			String messClic[]={
					"",
					"Après le clic "+(i+1),
					"Cliquez pour continuer",
					""
			};
			IG.afficherMessage(messClic);
			
			if (i<3) {
				IG.placerJoueurPrecis(0, 3, 0, 1, i+1);
				IG.placerBilleSurPlateau(3, 0, 1, i, 0);
				IG.placerJoueurPrecis(1, 3, 6, 1, 2-(i+1));
				IG.placerBilleSurPlateau(3, 6, 1, 2-i, 0);
				IG.changerPieceHorsPlateau(1, 0+(i+1));
				for (int j = 0; j<7;j++) {
					for (int k = 0; k<7;k++) {
						IG.changerPiecePlateau(j, k, 2, 1+i);
						
					}
				}
			}else {
				IG.placerJoueurPrecis(0, 3, 1, 1, 1);
				IG.placerJoueurPrecis(1, 3, 5,1,1);
				IG.placerBilleSurPlateau(3, 1, 1, 0, 0);
				IG.placerBilleSurPlateau(3, 5, 1, 2, 0);
				IG.changerPieceHorsPlateau(1, 0);
				for (int j = 0; j<7;j++) {
					for (int k = 0; k<7;k++) {
						IG.changerPiecePlateau(j, k, 2, 0);
					}
				}
			}
			IG.miseAJourAffichage();
			
			
		}
		IG.attendreClic();
		IG.afficherGagnant(0);
		String messFin[]={
				"",
				"Cliquez sur une flêche ",
				"pour quitter",
				""
		};
		IG.afficherMessage(messFin);
		IG.miseAJourAffichage();
		IG.attendreChoixEntree();
		IG.pause(2000);
		IG.fermerFenetreJeu();
	}

}
