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

    //Benoit Fardoux 09/06/2021 FINI
    //Methode permettant au joueur d'inserer une piece a l'endroit qui generait le joueur etant le plus proche de son prochain objet
    public int[] choisirOrientationEntree(ElementsPartie elementsPartie){
        // recuperation des éléments
    	int[] result = new int[2];
    	result[0] = Utils.genererEntier(3);
        Joueur[] joueurs = elementsPartie.getJoueurs();
        // on prend un joueur par défaut et on récupère sa distance avec son objet
        Joueur joueurTmp = joueurs[0];
        if (joueurTmp.equals(this)) joueurTmp = joueurs[1];
        double distanceTmp = joueurTmp.distanceAvecSonObjet();
        // compare avec les autres joueurs qui est le plus proche (methode joueur.distanceAvecSonObjet crée spécialement pour être utilisé ici
        for (Joueur i:joueurs ) {
            if (!(i.equals(this)) && distanceTmp > i.distanceAvecSonObjet()) {
                distanceTmp = i.distanceAvecSonObjet();
                joueurTmp = i;
            }
        }
        System.out.println("coucou");
        // verifier s'il est plus proche par les colonnes ou par les lignes et retourne ce qui devrait déranger le plus un joueur
        if (Math.abs( joueurTmp.getProchainObjet().getPosColonnePlateau()-joueurTmp.getPosColonne() )< Math.abs( joueurTmp.getProchainObjet().getPosLignePlateau()-joueurTmp.getPosLigne() )){
            result[1] = joueurTmp.getPosLigne();
        }else{
        	result[1] = joueurTmp.getPosColonne()+7;
        	
        }
        return result;
    }
    

    //Victor Sin 09/06/2021 FINI
    //Méthode permettant au joueur de chercher le chemin menant vers la case la plus proche en terme de distance du prochain objet.
    @Override
    public int[] choisirCaseArrivee(ElementsPartie elementsPartie) {
    	
    	int diffL = 48;
    	int diffC = 48;
    	int[] result = new int[2];
    	result[0] = this.getPosLigne();
    	result[1] = this.getPosColonne();
    	Plateau plateau =  elementsPartie.getPlateau();
    	// Parcours du plateau a la recherche du chemin rapprochant le plus le joueur au premiere objet. 
    	for(int i = 0; i < 7; i++ ){
    		for(int j = 0; j < 7; j++) {
    			int[][] chemin = plateau.calculeChemin(this.getPosLigne(), this.getPosColonne(), i, j);
    			//Verification si le joueur possede un chemin vers l'objet.
    			if(chemin != null && i == this.getProchainObjet().getPosLignePlateau() && j == this.getProchainObjet().getPosColonnePlateau()) {
    				result[0] = i;
    				result[1] = j;
    				return result;
    			}
    			//Verifie si il y a un chemin entre le joueur et une case. Et verifie si elle a un nombre de sortie egale � 1 / est une impasse.
    			else if(chemin != null){
    				//Verification si le joueur est sur une ligne et une colonne differente de l'objet.
    				if(i != this.getProchainObjet().getPosLignePlateau() && j != this.getProchainObjet().getPosColonnePlateau()) {
        				//Calcul la valeur absolue de la diff�rence entre le position de l'objet et celle du joueur.
        				int calcDiffL = Math.abs(this.getProchainObjet().getPosLignePlateau()-i); 
        				int calcDiffC = Math.abs(this.getProchainObjet().getPosColonnePlateau()-j);
        				//Verification si le chemin actuellement en calcul est le plus proche qui mene a l'objet rechercher.
						if(calcDiffL + calcDiffC < diffL + diffC) {
        					result[0] = i;
        					result[1] = j;
        					diffL = calcDiffL;
        					diffC = calcDiffC;
        				}
    					
    				}
    			}
    		}
    	}
    	return result;
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
