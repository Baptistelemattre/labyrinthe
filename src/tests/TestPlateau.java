package tests;
import composants.Plateau;
import composants.Piece;
import grafix.interfaceGraphique.IG;

public class TestPlateau {
    public static void main(String[] args) {
        // Une petite d�monstration conernant l'interface graphique

    
        // diff�rents param�tres par d�faut
        Object parametres[];
        parametres=IG.saisirParametres(); // On ouvre la fen�tre de param�trage pour la saisie
        
        // Cr�ation de la fen�tre de jeu et affichage de la fen�tre 
        int nbJoueurs=((Integer)parametres[0]).intValue(); // R�cup�ration du nombre de joueurs
        IG.creerFenetreJeu("D�mo Librairie IG version 1.9",nbJoueurs); // On cr�e la fen�tre
        IG.rendreVisibleFenetreJeu();  // On rend visible la fen�tre de jeu

                // Affichage d'un message
        String message[]={
            "",
            "Cliquer pour continuer ...",
            ""
        };
        IG.afficherMessage(message); // On change de message de la fen�tre de jeu
        IG.miseAJourAffichage(); // On effectue le rafraichissement de la fen�tre de jeu
        IG.attendreClic();

        Plateau plateau = new Plateau();
        Piece pieceHorsPlateau=plateau.placerPiecesAleatoierment();
        for (int i=0; i<7; i++){
            for (int j=0; j<7; j++){
                IG.changerPiecePlateau(i, j, plateau.getPiece(i,j).getModelePiece(), plateau.getPiece(i,j).getOrientationPiece());
            }
        }
        IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(), pieceHorsPlateau.getOrientationPiece());
        IG.miseAJourAffichage();

        System.out.println(plateau.calculeChemin(3,3,4,5));
    }
}