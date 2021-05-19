package tests;
import composants.Plateau;
import composants.Piece;
import grafix.interfaceGraphique.IG;

public class TestPlateau {
    public static void main(String[] args) {
        // Une petite démonstration conernant l'interface graphique

    
        // différents paramétres par défaut
        Object parametres[];
        parametres=IG.saisirParametres(); // On ouvre la fenêtre de paramétrage pour la saisie
        
        // Création de la fenêtre de jeu et affichage de la fenêtre 
        int nbJoueurs=((Integer)parametres[0]).intValue(); // Récupération du nombre de joueurs
        IG.creerFenetreJeu("Démo Librairie IG version 1.9",nbJoueurs); // On crée la fenêtre
        IG.rendreVisibleFenetreJeu();  // On rend visible la fenêtre de jeu

                // Affichage d'un message
        String message[]={
            "",
            "Cliquer pour continuer ...",
            ""
        };
        IG.afficherMessage(message); // On change de message de la fenêtre de jeu
        IG.miseAJourAffichage(); // On effectue le rafraichissement de la fenêtre de jeu
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