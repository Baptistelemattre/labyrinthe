package joueurs;

import composants.Objet;
import composants.Plateau;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import partie.ElementsPartie;

/**
 *
 * Cette classe permet de reprÃ©senter un joueur ordinateur de type T1.
 *
 * @author Jean-FranÃ§ois Condotta - 2021
 *
 */

public class JoueurOrdinateurT1 extends joueurs.JoueurOrdinateur {

    /**
     *
     * Constructeur permettant de crÃ©er un joueur.
     *
     * @param numJoueur Le numÃ©ro du joueur.
     * @param nomJoueur Le nom du joueur.
     * @param numeroImagePersonnage Le numÃ©ro de l'image reprÃ©sentant le joueur.
     * @param posLignePlateau La ligne du plateau sur laquelle est positionnÃ©e le joueur.
     * @param posColonnePlateau La colonne du plateau sur laquelle est positionnÃ©e le joueur.

     */
    public JoueurOrdinateurT1(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
        super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
    }

    @Override
    public String getCategorie() {
        return "OrdiType1";
    }


    @Override
    public Joueur copy(Objet objets[]){
        Joueur nouveauJoueur=new JoueurOrdinateurT1(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
        nouveauJoueur.setObjetsJoueur(this.getObjetsJoueurGeneral(objets));
        while (nouveauJoueur.getNombreObjetsRecuperes()!=this.getNombreObjetsRecuperes())
            nouveauJoueur.recupererObjet();
        return nouveauJoueur;
    }
    
    @Override
    public int[] choisirCaseArrivee(ElementsPartie elementsPartie) {
    	int diffL = 48;
    	int diffC = 48;
    	int[] result = new int[2];
    	Plateau plateau =  elementsPartie.getPlateau();
    	// Parcours du tableau a la recherche du chemin rapprochant le plus le joueur au premiere objet. 
    	for(int i = 0; i < 7; i++ ){
    		for(int j = 0; j < 7; j++) {
    			int[][] chemin = plateau.calculeChemin(this.getPosLigne(), this.getPosColonne(), i, j);
    			//Verification si le joueur possede un chemin vers l'objet.
    			if(chemin != null && i == this.getProchainObjet().getPosLignePlateau() && i == this.getProchainObjet().getPosColonnePlateau()) {
    				result[0] = i;
    				result[1] = j;
    				return result;
    			}
    			//Verifie si il y a un chemin entre le joueur et une case. Et verifie si elle a un nombre de sortie egale � 1 / est une impasse.
    			else if(chemin != null && plateau.nbPassagePossiblePiece(i, j) == 1){
    				//Verification si le joueur est sur une ligne et une colonne differente de l'objet.
    				if(i != this.getProchainObjet().getPosLignePlateau() && j != this.getProchainObjet().getPosColonnePlateau()) {
        				//Calcul la valeur absolue de la diff�rence entre le position de l'objet et celle du joueur.
        				int calcDiffL = Math.abs(this.getProchainObjet().getPosLignePlateau()-chemin[chemin.length-1][0]); 
        				int calcDiffC = Math.abs(this.getProchainObjet().getPosColonnePlateau()-chemin[chemin.length-1][1]);
        				//Verification si le chemin actuellement en calcul est le plus proche qui mene a l'objet rechercher.
        				if(calcDiffL < diffL && calcDiffC < diffC) {
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
    public int[] choisirOrientationEntree(ElementsPartie elementsPartie) {
    	int[] result = new int[2];
        Plateau plateau =  elementsPartie.getPlateau();
        
        
    	// Verifie si il existe un chemin actuelle vers l'objet
        int[][] chemin = plateau.calculeChemin(this.getPosLigne(), this.getPosColonne(), this.getProchainObjet().getPosLignePlateau(), this.getProchainObjet().getPosColonnePlateau());
        if(chemin != null) {
        	int countFleche = 0;
        	//Verification que la piece pousser ne detruit pas le chemin pris
        	for(int i = 0; i <= 7; i++) {
        		for(int[] couple : chemin) {
        			//Verification si les fleches des colonnes ne sont pas dans le chemin
        			if(countFleche != couple[1]) {
        				result[0] = countFleche;
        			}
        			//Verification si les fleches des lignes ne sont pas dans le chemin
        			else if (countFleche-i != couple[0]) {
        				result[0] = countFleche-i;
        			}
        		}
        	}
        	//Une fois toutes les lignes/colonnes de fleches visiter insere une piece ou il ne brisera pas le chemin.
			result[1] = Utils.genererEntier(3);
			return result;
        }
        
        
        
        // L'ordinateur va inserer la piece a l'endroit qui le rapprochera de l'objet
        int[] diffPosJoueurObjet = new int[2];
        diffPosJoueurObjet[0] = this.getProchainObjet().getPosLignePlateau()-this.getPosLigne();
        diffPosJoueurObjet[1] = this.getProchainObjet().getPosColonnePlateau()-this.getPosColonne();
    	//Verification si le joueur est plus proche sur les colonnes
    	if(Math.abs(diffPosJoueurObjet[0]) > Math.abs(diffPosJoueurObjet[1])) {
    		//Verification si l'ajout doit etre par le haut
    		if(diffPosJoueurObjet[1] >= 0) {
    			for(int fleche = 0; fleche < 7;fleche++){
    				if(fleche == this.getPosColonne()) {
        				result[0] = fleche;
        				break;
    				}
    			}		
    		}
    		// Si l'ajout est vers le bas
    		else {
    			for(int fleche = 0; fleche < 7;fleche++){
    				if(20-fleche == this.getPosColonne()) {
        				result[0] = 20-fleche;
        				break;
    				}
    			}	
    		}
    	}
    	//Cas ou le joueur est plus proche sur la ligne
    	else if (Math.abs(diffPosJoueurObjet[0]) < Math.abs(diffPosJoueurObjet[1])){
    		//Verification si l'ajout est par la gauche
    		if(diffPosJoueurObjet[0] >= 0) {
    			for(int fleche = 0; fleche < 7;fleche++){
    				if(fleche-7 == this.getPosLigne()) {
        				result[0] = fleche+7;
        				break;
    				}
    			}		
    		}
    		//Verification si l'ajout est par la droite
    		else {
    			for(int fleche = 0; fleche < 7;fleche++){
    				if(27-fleche == this.getPosLigne()) {
        				result[0] = 27-fleche;
        				break;
    				}
    			}	
    		}
    	}
		result[1] = Utils.genererEntier(3);
		return result;
    	

    }
}
