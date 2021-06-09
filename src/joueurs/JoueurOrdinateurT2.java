package joueurs;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import composants.Utils;
import partie.ElementsPartie;

/**
 *
 * Cette classe permet de reprÃ©senter un joueur ordinateur de type T2.
 *
 * @author Jean-FranÃ§ois Condotta - 2021
 *
 */

public class JoueurOrdinateurT2 extends JoueurOrdinateur {

    /**
     * Constructeur permettant de crÃ©er un joueur.
     *
     * @param numJoueur Le numÃ©ro du joueur.
     * @param nomJoueur Le nom du joueur.
     * @param numeroImagePersonnage Le numÃ©ro de l'image reprÃ©sentant le joueur.
     * @param posLignePlateau La ligne du plateau sur laquelle est positionnÃ©e le joueur.
     * @param posColonnePlateau La colonne du plateau sur laquelle est positionnÃ©e le joueur.

     */
    public JoueurOrdinateurT2(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
        super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
    }

    @Override
    public String getCategorie() {
        return "OrdiType2";
    }

    public int entreePourEmbeterJoueur(ElementsPartie elementsPartie){
        // recuperation des éléments
        Joueur[] joueurs = elementsPartie.getJoueurs();
        Plateau plateau = elementsPartie.getPlateau();
        // on prend un joueur par défaut et on récupère sa distance avec son objet
        Joueur joueurTmp = joueurs[0];
        if (joueurTmp.equals(this)) joueurTmp = joueurs[1];
        double distanceTmp = joueurTmp.distanceAvecSonObjet();
        // compare avec les autres joueurs qui est le plus proche et on retourne ce joueur (methode joueur.distanceAvecSonObjet crée spécialement pour être utilisé ici
        for (Joueur i:joueurs ) {
            if (!(i.equals(this)) && distanceTmp > i.distanceAvecSonObjet()) {
                distanceTmp = i.distanceAvecSonObjet();
                joueurTmp = i;
            }
        }
        return 0;
    }


    @Override
    public Joueur copy(Objet objets[]){
        Joueur nouveauJoueur=new JoueurOrdinateurT2(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
        nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
        while (nouveauJoueur.getNombreObjetsRecuperes()!=this.getNombreObjetsRecuperes())
            nouveauJoueur.recupererObjet();
        return nouveauJoueur;
    }

}
