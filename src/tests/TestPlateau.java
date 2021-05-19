package tests;
import composants.Plateau;

import java.util.Vector;

import composants.Piece;
import grafix.interfaceGraphique.IG;


// Partie par Habib 19/05/2021 FINI
public class TestPlateau { 
    public static void main(String[] args){
        Object[] parametres;
        parametres = IG.saisirParametres();
        int nbJoueurs = (Integer) parametres[0];
        IG.creerFenetreJeu("Bricodeurs de l'IUT de Lens", nbJoueurs);
        IG.rendreVisibleFenetreJeu();
        String[] message = {
                "",
                "",
                "Cliquer pour continuer ...",
                ""
        };
        IG.afficherMessage(message);
        IG.miseAJourAffichage();
        IG.attendreClic();

        Plateau plateau=new Plateau();
		Piece pieceHorsPlateau=plateau.placerPiecesAleatoierment();
        
		for (int i=0;i<7;i++) {
			for (int j=0;j<7;j++) {
				IG.changerPiecePlateau(i,j,plateau.getPiece(i,j).getModelePiece(),plateau.getPiece(i, j).getOrientationPiece());
				}
			}
		IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(),pieceHorsPlateau.getOrientationPiece());
		IG.miseAJourAffichage();
		IG.attendreClic();
		
		//Variables pour déterminer le chemin le plus long  et le sauvegarder
		int longueurMax = 0;
		Vector<int[]> cheminMax = new Vector<>();
		
		for (int i=0;i<7;i++) {
			for (int j=0;j<7;j++) {			
				int[][] chemin = plateau.calculeChemin(3, 3, i, j);
				if (chemin != null && (i != 3 || j != 3)) {
					Vector<int[]> cheminActuelle = new Vector<>();
					// Ecrit dans la console le chemin entre deux pièces
					System.out.println("\nChemin entre les cases (3,3) et ("+i+","+j+") : " );
					for(int n = 0; n < chemin.length; n++) {
						System.out.print("(" + chemin[n][0] + "," + chemin[n][1] + ") ");
						cheminActuelle.add(chemin[n]);
					}
					// Vérifie si le chemin actuelle est le plus grand, si oui il le remplace pour l'affichage des billes
					if(cheminActuelle.size() > longueurMax){
						cheminMax.clear();
						longueurMax = cheminActuelle.size();
						for(int n = 0; n < cheminActuelle.size(); n++) {
							cheminMax.add(cheminActuelle.get(n));
						}
					}
				}
				if(i == 6 && j == 6) {
					for (int w = 0; w < cheminMax.size() ; w++) {		
						IG.placerBilleSurPlateau(cheminMax.get(w)[0], cheminMax.get(w)[1], 1, 1, 2);
					}
					IG.miseAJourAffichage();
				}
			}
		}


        
    }    
}
