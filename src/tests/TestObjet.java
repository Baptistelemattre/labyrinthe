package tests;

import composants.Objet;
import grafix.interfaceGraphique.IG;

public class TestObjet {
    public static void main(String[] args) {
        Object[] parametres;
        parametres = IG.saisirParametres();
        int nbJoueurs = ((Integer) parametres[0]).intValue();
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

        Objet[] tab = Objet.nouveauxObjets();
        for (Objet objet : tab) {
            IG.placerObjetPlateau(objet.getNumeroObjet(), objet.getPosLignePlateau(), objet.getPosColonnePlateau());
        }
        IG.miseAJourAffichage();
        IG.attendreClic();
        IG.fermerFenetreJeu();
    }
}
