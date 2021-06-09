package partie;

import composants.Objet;
import composants.Piece;
import composants.Plateau;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import joueurs.Joueur;
import joueurs.JoueurOrdinateur;
import joueurs.JoueurOrdinateurT2;

public class Partie {
        static double version=0.0;


        private ElementsPartie elementsPartie; // Les Ã©lÃ©ments de la partie.

        /**
         *
         * A Faire (Quand Qui Statut) 02/05/2021 Victor Fini
         *
         * Constructeur permettant de crÃ©er et d'initialiser une nouvelle partie.
         */
        public Partie(){
            // Initialisation de la partie
            parametrerEtInitialiser();

            // On affiche l'ensemble des Ã©lÃ©ments
            
            
            //Places les pieces du jeux
    		for (int i=0;i<7;i++) { 
    			for (int j=0;j<7;j++) {
    				IG.changerPiecePlateau(i,j,elementsPartie.getPlateau().getPiece(i,j).getModelePiece(),elementsPartie.getPlateau().getPiece(i, j).getOrientationPiece());
    				}
    			}
    		IG.changerPieceHorsPlateau(elementsPartie.getPieceLibre().getModelePiece(),elementsPartie.getPieceLibre().getOrientationPiece());
    		
    		//Places les objets sur le plateau
    		for(int i=0;i<18;i++) {
    			IG.placerObjetPlateau(elementsPartie.getObjets()[i].getNumeroObjet(), elementsPartie.getObjets()[i].getPosLignePlateau(),elementsPartie.getObjets()[i].getPosColonnePlateau());
    		}
    		
    		//Donnes les objets aux joueurs
    		for(int i=0;i<18/elementsPartie.getNombreJoueurs();i++) {
    			IG.changerObjetJoueur(0, elementsPartie.getJoueurs()[0].getObjetsJoueur()[i].getNumeroObjet(), i);
    			IG.changerObjetJoueur(1, elementsPartie.getJoueurs()[1].getObjetsJoueur()[i].getNumeroObjet(), i);
    			if(elementsPartie.getNombreJoueurs() == 3) {
    				IG.changerObjetJoueur(2, elementsPartie.getJoueurs()[2].getObjetsJoueur()[i].getNumeroObjet(), i);
    			}
    		}
    		
    		//Places les joueurs sur le plateau
            for (int i = 0; i < elementsPartie.getNombreJoueurs(); i++) {
            	IG.changerNomJoueur(i, elementsPartie.getJoueurs()[i].getNomJoueur() + " (" + elementsPartie.getJoueurs()[i].getCategorie() + ")");
            	IG.changerImageJoueur(i, elementsPartie.getJoueurs()[i].getNumeroImagePersonnage());
            	IG.placerJoueurSurPlateau(i, elementsPartie.getJoueurs()[i].getPosLigne(), elementsPartie.getJoueurs()[i].getPosColonne());
            }
    		
            IG.miseAJourAffichage();

            // A ComplÃ©ter

            IG.rendreVisibleFenetreJeu();
        }

        /**
         * MÃ©thode permettant de paramÃ¨trer et initialiser les Ã©lÃ©ments de la partie.
         */
        private void parametrerEtInitialiser(){
            // Saisie des diffÃ©rents paramÃ¨tres
            Object parametresJeu[];
            parametresJeu=IG.saisirParametres();
            int nombreJoueurs=((Integer)parametresJeu[0]).intValue();
            IG.creerFenetreJeu("- Version "+version, nombreJoueurs);
            // CrÃ©ation des joueurs
            Joueur joueurs[]=Joueur.nouveauxJoueurs(parametresJeu);
            // CrÃ©ation des Ã©lÃ©ments de la partie
            elementsPartie=new ElementsPartie(joueurs);
        }


        /**
         *
         * A Faire (Quand Qui Statut) 02/05/2021 Victor Fini
         *
         * MÃ©thode permettant de lancer une partie.
         */
        public void lancer(){
        	boolean win = false;
            // A ComplÃ©ter
        	while(!win) {
	        	for (int i = 0; i < elementsPartie.getNombreJoueurs(); i++) {
	        		
                    String[] messageInit = {
                            "",
                            "Au tour de " + (String)elementsPartie.getJoueurs()[i].getNomJoueur(),
                            "Selectionner une fleche et",
                            "modifier la rotation"
                    };
	        		IG.afficherMessage(messageInit);
					IG.changerObjetSelectionne(elementsPartie.getJoueurs()[i].getProchainObjet().getNumeroObjet());
					IG.changerJoueurSelectionne(i);
	        		IG.miseAJourAffichage();
	        		
	        		//Demande au joueurs, de modifier l'orientation et de donner une entr�e
		        		int[] rep;
		        		if(elementsPartie.getJoueurs()[i].getCategorie() != "Humain") {
		        			IG.pause(500);
		        			rep = elementsPartie.getJoueurs()[i].choisirOrientationEntree(elementsPartie);
		        			if (elementsPartie.getJoueurs()[i].getCategorie().equals("OrdiType2")) {
		        				JoueurOrdinateurT2 bot2 = (JoueurOrdinateurT2) elementsPartie.getJoueurs()[i];
		        				rep[0] = Utils.genererEntier(3);
		        				rep[1] = bot2.entreePourEmbeterJoueur(elementsPartie);
		        				System.out.println(rep[1]);
							}
		        		}
		        		else {
		        			rep = elementsPartie.getJoueurs()[i].choisirOrientationEntree(null);
		        		}
		        		IG.changerPieceHorsPlateau(elementsPartie.getPieceLibre().getModelePiece(), rep[0]);
		        		elementsPartie.insertionPieceLibre(rep[1]);
	        		//Remodifie la position des elements post-insertion
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
	        		
	        		
	            	//Demande au Joueurs, si ils sont humain un chemin a prendre.
		            	int[] posJoueur = new int[2];
		            	int[][] cheminPris;
		            	int[][] cheminPrisFinal;
		            	do {
		            		if(elementsPartie.getJoueurs()[i].getCategorie() != "Humain") {
		            			posJoueur = elementsPartie.getJoueurs()[i].choisirCaseArrivee(elementsPartie);        			
		            		}
		            		else {
		                        String[] messageTurn = {
		                                "",
		                                "Au tour de " + (String)elementsPartie.getJoueurs()[i].getNomJoueur(),
		                                "Selectionner une case ...",
		                                ""
		                        };
		                        IG.afficherMessage(messageTurn);
		                        IG.miseAJourAffichage();
		            			posJoueur = elementsPartie.getJoueurs()[i].choisirCaseArrivee(null);
		            			
		            		}
		            		cheminPris = elementsPartie.getPlateau().calculeChemin(elementsPartie.getJoueurs()[i].getPosLigne(), elementsPartie.getJoueurs()[i].getPosColonne(), posJoueur[0], posJoueur[1]);
		            	}while(cheminPris == null);
	            	
	            	//Marque le chemin pris par le Joueur.
		            	cheminPrisFinal =  elementsPartie.getPlateau().calculeCheminDetaille(cheminPris, i);
		            	for(int n = 0; n < cheminPrisFinal.length; n++) {
		            		IG.placerBilleSurPlateau(cheminPrisFinal[n][0], cheminPrisFinal[n][1], cheminPrisFinal[n][2], cheminPrisFinal[n][3], i);
		            	}
		            	IG.placerJoueurSurPlateau(i,posJoueur[0] , posJoueur[1]);
		            	elementsPartie.getJoueurs()[i].setPosition(posJoueur[0], posJoueur[1]);
		            	IG.miseAJourAffichage();
	            	
	            	
	            	//Detruit le chemin petit � petit
		            	for(int x=0;x<cheminPrisFinal.length;x++) {
		
		            		IG.supprimerBilleSurPlateau(cheminPrisFinal[x][0], cheminPrisFinal[x][1], cheminPrisFinal[x][2], cheminPrisFinal[x][3]);
		            		IG.miseAJourAffichage();
		            	}
	            	
	            	
	            	
	            	//Verification si le joueur est sur un objet, si oui l'enleve du plateau et confirme qu'il a trouve l'objet
		            	Objet objet = elementsPartie.getJoueurs()[i].getProchainObjet();
		            	if(objet.getPosLignePlateau() == posJoueur[0] && objet.getPosColonnePlateau() == posJoueur[1]) {
	            			IG.enleverObjetPlateau(objet.getPosLignePlateau(), objet.getPosColonnePlateau());
	            			objet.enleveDuPlateau();
	            			IG.changerObjetJoueurAvecTransparence(i, objet.getNumeroObjet(), elementsPartie.getJoueurs()[i].getNombreObjetsRecuperes());
	            			elementsPartie.getJoueurs()[i].recupererObjet();
		            	}
		            	IG.miseAJourAffichage();
		            	
	            	//Verification si le joueur a gagner
		            	if(elementsPartie.getJoueurs()[i].getNombreObjetsRecuperes() == 18/elementsPartie.getNombreJoueurs() || elementsPartie.getJoueurs()[i].getProchainObjet() == null) {
		            		IG.afficherGagnant(i);
		                    String[] messageEnd = {
		                            "",
		                            (String)elementsPartie.getJoueurs()[i].getNomJoueur() + " a gagne !!",
		                            "Cliquer pour quitter",
		                            ""
		                    };
		                    IG.afficherMessage(messageEnd);
		                    IG.miseAJourAffichage();
		                    IG.attendreClic();
		                    IG.fermerFenetreJeu();
		                    win = true;
		            	}
	        	}
        	}
        	System.exit(0);
        }

        /**
         *
         * Programme principal permettant de lancer le jeu.
         *
         * @param args Les arguments du programmes.
         */
        public static void main(String[] args) {
            while(true){
                Partie partie=new Partie();
                partie.lancer();
            }
        }

    }

