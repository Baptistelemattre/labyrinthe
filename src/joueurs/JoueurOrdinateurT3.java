package joueurs;

import composants.Objet;
import composants.Plateau;
import composants.Utils;
import partie.ElementsPartie;

/**
 *
 * Cette classe permet de reprÃ©senter un joueur ordinateur de type T3.
 *
 * @author Jean-FranÃ§ois Condotta - 2021
 *
 */

public class JoueurOrdinateurT3 extends JoueurOrdinateur {

    /**
     * Constructeur permettant de crÃ©er un joueur.
     *
     * @param numJoueur Le numÃ©ro du joueur.
     * @param nomJoueur Le nom du joueur.
     * @param numeroImagePersonnage Le numÃ©ro de l'image reprÃ©sentant le joueur.
     * @param posLignePlateau La ligne du plateau sur laquelle est positionnÃ©e le joueur.
     * @param posColonnePlateau La colonne du plateau sur laquelle est positionnÃ©e le joueur.

     */
    public JoueurOrdinateurT3(int numJoueur,String nomJoueur, int numeroImagePersonnage,int posLignePlateau,int posColonnePlateau) {
        super(numJoueur,nomJoueur, numeroImagePersonnage,posLignePlateau,posColonnePlateau);
    }

        @Override
    public String getCategorie() {
        return "OrdiType3";
    }


    @Override
    public Joueur copy(Objet objets[]){
        Joueur nouveauJoueur=new JoueurOrdinateurT3(getNumJoueur(),getNomJoueur(), getNumeroImagePersonnage(),getPosLigne(),getPosColonne());
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
    
    public int[] choisirOrientationEntree(ElementsPartie elementsPartie){
        // recuperation des éléments
    	int[] result = new int[2];
        Plateau plateau =  elementsPartie.getPlateau();
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
                if (joueurTmp.getProchainObjet().getPosColonnePlateau()-joueurTmp.getPosColonne()< joueurTmp.getProchainObjet().getPosLignePlateau()-joueurTmp.getPosLigne()){
                    result[1] = joueurTmp.getPosLigne();
                	result[0] = Utils.genererEntier(3);
                    return result;
                }else{
                	result[1] = joueurTmp.getPosColonne()+7;
                	result[0] = Utils.genererEntier(3);
                    return result;
                }
            }
            else {
            	// Verifie si il existe un chemin actuelle vers l'objet
                int[][] chemin = plateau.calculeChemin(this.getPosLigne(), this.getPosColonne(), this.getProchainObjet().getPosLignePlateau(), this.getProchainObjet().getPosColonnePlateau());
                if(chemin != null) {
                	//Verification que la piece pousser ne detruit pas le chemin pris
                	for(int a = 0; a < 7; a++) {
                		for(int[] couple : chemin) {
                			//Verification si les fleches des colonnes ne sont pas dans le chemin
                			if(a != couple[1]) {
                				result[1] = a;
                			}
                			//Verification si les fleches des lignes ne sont pas dans le chemin
                			else if (7+a != couple[0]) {
                				result[1] = 7-a;
                			}
                		}
                	}
                	//Une fois toutes les lignes/colonnes de fleches visiter insere une piece ou il ne brisera pas le chemin.
        			result[0] = Utils.genererEntier(3);
        			System.out.println(result[1]);
        			return result;
                }
                
            	int diffL = 48;
            	int diffC = 48;
                for (int a=0; a<=27;a++){ // test toutes les entr�es du plateau
                    for (int j=0; j<4; j++){ // test toutes les orientation de la piece hors plateau

                        // copie des elements de la partie pour simuler une insertion
                        ElementsPartie copyElementsPartie = elementsPartie.copy();
                        copyElementsPartie.getPieceLibre().setOrientation(j);
                        copyElementsPartie.insertionPieceLibre(a);
                        
                    	Plateau plateauCopy =  copyElementsPartie.getPlateau();
                    	// Parcours du plateau a la recherche du chemin rapprochant le plus le joueur au premiere objet. 
                    	for(int n = 0; n < 7; n++ ){
                    		for(int u = 0; u < 7; u++) {
                    			int[][] cheminTmp = plateauCopy.calculeChemin(copyElementsPartie.getJoueurs()[this.getNumJoueur()].getPosLigne(), copyElementsPartie.getJoueurs()[this.getNumJoueur()].getPosColonne(), n, u);
                    			//Verifie si il y a un chemin entre le joueur et une case. Et verifie si elle a un nombre de sortie egale � 1 / est une impasse.
                    			if(cheminTmp != null){
                    				//Verification si le joueur est sur une ligne et une colonne differente de l'objet.
                    				if(n != copyElementsPartie.getJoueurs()[this.getNumJoueur()].getProchainObjet().getPosLignePlateau() && u != copyElementsPartie.getJoueurs()[this.getNumJoueur()].getProchainObjet().getPosColonnePlateau()) {
                        				//Calcul la valeur absolue de la diff�rence entre le position de l'objet et celle du joueur.
                        				int calcDiffL = Math.abs(copyElementsPartie.getJoueurs()[this.getNumJoueur()].getProchainObjet().getPosLignePlateau()-cheminTmp[cheminTmp.length-1][0]); 
                        				int calcDiffC = Math.abs(copyElementsPartie.getJoueurs()[this.getNumJoueur()].getProchainObjet().getPosColonnePlateau()-cheminTmp[cheminTmp.length-1][1]);
                        				//Verification si le chemin actuellement en calcul est le plus proche qui mene a l'objet rechercher.
                						if(calcDiffL + calcDiffC < diffL + diffC) {
                        					diffL = calcDiffL;
                        					diffC = calcDiffC;
                        					result[1] = a;
                        					result[0] = j;
                        					
                        				}
                    				}
                    				else if (n == copyElementsPartie.getJoueurs()[this.getNumJoueur()].getProchainObjet().getPosLignePlateau() && u == copyElementsPartie.getJoueurs()[this.getNumJoueur()].getProchainObjet().getPosColonnePlateau()) {
                    					result[1] = a;
                    					result[0] = j;
                    					return result;
                    				}
                    			}
                    		}
                    	}
                        
                        
                    }
                }
               
            }
            
        }

        return result;
    }
}
