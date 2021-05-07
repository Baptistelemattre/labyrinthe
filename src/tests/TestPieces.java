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

        IG.changerPieceHorsPlateau(Tableau[Tableau.lenght()-1].getModelePiece(), Tableau[Tableau.lenght()-1].getOrientationPiece());
		IG.miseAJourAffichage();

		for (int i=0; i<4; i++){
			IG.attendreClic();
			for(int j=0; j<Tableau.lenght()-2;j++){
				Tableau[j].rotation();
				System.out.println(Tableau[j].toString());
			}
		    IG.changerPieceHorsPlateau(Tableau[Tableau.lenght()-1].getModelePiece(), Tableau[Tableau.lenght()-1].getOrientationPiece());
			IG.miseAJourAffichage();
			System.out.println(Tableau[Tableau.lenght()-1].toString());
		}
        IG.attendreClic();
        IG.fermerFenetreJeu();

}
