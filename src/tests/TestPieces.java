package tests;
import grafix.interfaceGraphique.IG;

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
        Piece[] Tableau = Piece.nouvellesPieces();

		int pos = 0;
        IG.changerPieceHorsPlateau(Tableau[Tableau.lenght()].getModelePiece(), Tableau[Tableau.lenght()].getOrientationPiece());
		for (int i = 0; i<7;i++){
			for (int j = 0; j<7;j++){
				IG.changerPiecePlateau(i,j,Tableau[pos].getModelePiece(),Tableau[pos].getOrientationPiece());
				pos ++;
			}
		}
		IG.miseAJourAffichage();


		for (int i=0; i<4; i++){
			IG.attendreClic();
			for(int j=0; j<Tableau.lenght()-2;j++){
				Tableau[j].rotation();
				System.out.println(Tableau[j].toString());
			}
			pos = 0;
			for (int n = 0; i<7;i++){
				for (int j = 0; j<7;j++){
					IG.changerPiecePlateau(n,j,Tableau[pos].getModelePiece(),Tableau[pos].getOrientationPiece());
					pos ++;
				}
			}
		    IG.changerPieceHorsPlateau(Tableau[Tableau.lenght()].getModelePiece(), Tableau[Tableau.lenght()].getOrientationPiece());
			IG.miseAJourAffichage();
			System.out.println(Tableau[Tableau.lenght()-1].toString());
		}
        IG.attendreClic();
        IG.fermerFenetreJeu();

}
