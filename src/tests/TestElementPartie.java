package tests;


import joueurs.Joueur;
import partie.ElementsPartie;
import composants.Objet;
import composants.Piece;
import composants.PieceM0;
import composants.PieceM1;
import composants.PieceM2;
import composants.Plateau;
import grafix.interfaceGraphique.IG;

public class TestElementPartie {
    public static void main(String[] args) {
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

        for (int i = 0 ; i<7; i++) {
            for (int j=0;j<7;j++) {
                IG.changerPiecePlateau(i,j,plateau.getPiece(i,j).getModelePiece(),plateau.getPiece(i, j).getOrientationPiece());
            }
        }
        IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(),pieceHorsPlateau.getOrientationPiece());
        IG.miseAJourAffichage();
        IG.attendreClic();

        for (int i=0;i<nbJoueurs;i++) {
            IG.placerJoueurSurPlateau(i,joueurs[i].getPosLigne(),joueurs[i].getPosColonne());
            IG.changerImageJoueur(i, joueurs[i].getNumeroImagePersonnage());
            IG.changerNomJoueur(i, joueurs[i].getNomJoueur()+" ("+joueurs[i].getCategorie()+")");

        }
        Objet[] obj=Objet.nouveauxObjets();
        for (int i = 0; i <obj.length ; i++) {
            IG.placerObjetPlateau(obj[i].getNumeroObjet(), obj[i].getPosLignePlateau(), obj[i].getPosColonnePlateau());

        }

        int nombreObj=18/nbJoueurs;
        for(int i = 0; i < nombreObj; i++) {
            IG.changerObjetJoueur(0,obj[i].getNumeroObjet(),i);
            IG.changerObjetJoueur(1,obj[i+nombreObj].getNumeroObjet(),i);
            if (nbJoueurs==3)IG.changerObjetJoueur(2,obj[i+nombreObj*2].getNumeroObjet(),i);
        }
        IG.miseAJourAffichage();
        IG.attendreClic();

        IG.miseAJourAffichage();


    }
}
