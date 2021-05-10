package tests;
import grafix.interfaceGraphique.IG;
import composants;

public class TestPieces{
    public static void main(String[] args){
		Object parametres[];
		parametres=IG.saisirParametres(); 
		
		int nbJoueurs=((Integer)parametres[0]).intValue();
		IG.creerFenetreJeu("Bricodeurs de l'IUT de Lens",nbJoueurs);
		IG.rendreVisibleFenetreJeu();  

		String message[]={
					"",
					"",
					"Cliquer pour continuer ...",
					""
		};
		IG.afficherMessage(message); 
		IG.miseAJourAffichage(); 
		IG.attendreClic();  
        Piece[] tableau = Piece.nouvellesPieces();

		int pos = 0;
        IG.changerPieceHorsPlateau(tableau[tableau.length()-1].getModelePiece(), tableau[tableau.length()-1].getOrientationPiece());
		for (int i = 0; i<7;i++){
			for (int j = 0; j<7;j++){
				IG.changerPiecePlateau(i,j,tableau[pos].getModelePiece(),tableau[pos].getOrientationPiece());
				pos ++;
			}
		}
		IG.miseAJourAffichage();


		for (int i=0; i<4; i++){
			IG.attendreClic();
			for(int j=0; j<tableau.length()-2;j++){
				tableau[j].rotation();
				System.out.println(tableau[j].toString());
			}
			pos = 0;
			for (int n = 0; n<7;n++){
				for (int j = 0; j<7;j++){
					IG.changerPiecePlateau(n,j,tableau[pos].getModelePiece(),tableau[pos].getOrientationPiece());
					pos ++;
				}
			}
		    IG.changerPieceHorsPlateau(tableau[tableau.length()]-1.getModelePiece(), tableau[tableau.length()-1].getOrientationPiece());
			IG.miseAJourAffichage();
			System.out.println(tableau[tableau.length()-1].toString());
		}
        IG.attendreClic();
        IG.fermerFenetreJeu();

}
