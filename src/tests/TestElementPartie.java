package tests;


import joueurs.Joueur;
import partie.ElementsPartie;
import composants.Objet;
import composants.Piece;
import composants.Plateau;
import grafix.interfaceGraphique.IG;


public class TestElementPartie {
    public static void main(String[] args) {
        Object parametresJeu[];
        parametresJeu = IG.saisirParametres();
        int nbJoueurs = (Integer) parametresJeu[0];
        IG.creerFenetreJeu("- TestElementsPartie", nbJoueurs);
        Joueur joueurs[] = Joueur.nouveauxJoueurs(parametresJeu);
        ElementsPartie elementsPartie = new ElementsPartie(joueurs);

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

        Plateau plateau = new Plateau();
        Piece pieceHorsPlateau = plateau.placerPiecesAleatoierment();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                IG.changerPiecePlateau(i, j, plateau.getPiece(i, j).getModelePiece(), plateau.getPiece(i, j).getOrientationPiece());
            }
        }
        IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(), pieceHorsPlateau.getOrientationPiece());
        IG.miseAJourAffichage();
        IG.attendreClic();

        for (int i = 0; i < nbJoueurs; i++) {
            IG.placerJoueurSurPlateau(i, joueurs[i].getPosLigne(), joueurs[i].getPosColonne());
            IG.changerImageJoueur(i, joueurs[i].getNumeroImagePersonnage());
            IG.changerNomJoueur(i, joueurs[i].getNomJoueur() + " (" + joueurs[i].getCategorie() + ")");

        }
        Objet[] obj = Objet.nouveauxObjets();
        for (int i = 0; i < obj.length; i++) {
            IG.placerObjetPlateau(obj[i].getNumeroObjet(), obj[i].getPosLignePlateau(), obj[i].getPosColonnePlateau());

        }

        int nombreObj = 18 / nbJoueurs;
        for (int i = 0; i < nombreObj; i++) {
            IG.changerObjetJoueur(0, obj[i].getNumeroObjet(), i);
            IG.changerObjetJoueur(1, obj[i + nombreObj].getNumeroObjet(), i);
            if (nbJoueurs == 3) IG.changerObjetJoueur(2, obj[i + nombreObj * 2].getNumeroObjet(), i);
        }
        IG.miseAJourAffichage();
        IG.attendreClic();
        for (int i =0;i<4;i++){
            int[] rep;
            rep = elementsPartie.getJoueurs()[0].choisirOrientationEntree(elementsPartie);
            IG.changerPieceHorsPlateau(elementsPartie.getPieceLibre().getModelePiece(), rep[0]);
            elementsPartie.insertionPieceLibre(rep[1]);
            for(int n=0;n<elementsPartie.getNombreJoueurs();n++) {
                IG.placerJoueurSurPlateau(n, elementsPartie.getJoueurs()[n].getPosLigne(), elementsPartie.getJoueurs()[n].getPosColonne());
            }

            for (int n=0;n<7;n++) {
                for (int j=0;j<7;j++) {
                    IG.changerPiecePlateau(n,j,elementsPartie.getPlateau().getPiece(n,j).getModelePiece(),elementsPartie.getPlateau().getPiece(n, j).getOrientationPiece());
                    IG.enleverObjetPlateau(n, j);
                }
            }
            IG.changerPieceHorsPlateau(elementsPartie.getPieceLibre().getModelePiece(),elementsPartie.getPieceLibre().getOrientationPiece());

            for(int j=0;j<18;j++) {
                if(elementsPartie.getObjets()[j].surPlateau()) {
                    IG.placerObjetPlateau(elementsPartie.getObjets()[j].getNumeroObjet(), elementsPartie.getObjets()[j].getPosLignePlateau(),elementsPartie.getObjets()[j].getPosColonnePlateau());
                }
            }

            IG.miseAJourAffichage();
            IG.attendreClic();
            System.out.println(i);
        }

        IG.miseAJourAffichage();
        IG.attendreClic();
        System.exit(0);

    }
}