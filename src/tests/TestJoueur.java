package tests;

import composants.*;
import partie.ElementsPartie;
import joueurs.*;
import grafix.interfaceGraphique.IG;


// Victor Sin/Habib Erfani 24/05/2021 FINI
public class TestJoueur {    
    public static void main(String[] args) {
            // Met en place les parametres et le plateau
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
        
            Plateau plateau=new Plateau();
            Piece pieceHorsPlateau=plateau.placerPiecesAleatoierment();
            
            for (int i=0;i<7;i++) {
                for (int j=0;j<7;j++) {
                    IG.changerPiecePlateau(i,j,plateau.getPiece(i,j).getModelePiece(),plateau.getPiece(i, j).getOrientationPiece());
                    }
                }
            IG.changerPieceHorsPlateau(pieceHorsPlateau.getModelePiece(),pieceHorsPlateau.getOrientationPiece());
        
            
            //Stocker les informations des joueurs dans une variable et mettre a jour l'affichage relatif aux joueurs selon leurs informaitons.
            Joueur joueurs[]=Joueur.nouveauxJoueurs(parametres);
            for (int i = 0; i < nbJoueurs; i++) {
            	IG.changerNomJoueur(i, joueurs[i].getNomJoueur() + " (" + joueurs[i].getCategorie() + ")");
            	IG.changerImageJoueur(i, joueurs[i].getNumeroImagePersonnage());
            	IG.placerJoueurSurPlateau(i, joueurs[i].getPosLigne(), joueurs[i].getPosColonne());
            }
    		IG.miseAJourAffichage();

    		
    		
            for (int i = 0; i < nbJoueurs; i++) {
            	//Demande au Joueurs, si ils sont humain un chemin a prendre.
            	int[] posJoueur = new int[2];
            	int[][] cheminPris;
            	do {
            		if(joueurs[i].getCategorie() != "Humain") {
            			posJoueur[0] = joueurs[i].getPosLigne();
            			posJoueur[1] = joueurs[i].getPosColonne();
            		}
            		else {
                        String[] messageTurn = {
                                "",
                                "Au tour de " + (String)joueurs[i].getNomJoueur(),
                                "S�lectionner une case ...",
                                ""
                        };
                        IG.afficherMessage(messageTurn);
                        IG.miseAJourAffichage();
            			posJoueur = joueurs[i].choisirCaseArrivee(null);
            			
            		}
            		cheminPris = plateau.calculeChemin(joueurs[i].getPosLigne(), joueurs[i].getPosColonne(), posJoueur[0], posJoueur[1]);
            	}while(cheminPris == null);
            	
            	//Marque le chemin pris par le Joueur.
            	for(int n = 0; n < cheminPris.length; n++) {
            		IG.placerBilleSurPlateau(cheminPris[n][0], cheminPris[n][1], 1, 1, i);
            	}
            	IG.placerJoueurSurPlateau(i,posJoueur[0] , posJoueur[1]);
            	IG.miseAJourAffichage();
            }
            
            String[] messageFin = {
                    "",
                    "",
                    "Cliquer pour quitter ...",
                    ""
            };
            IG.afficherMessage(messageFin);
        	IG.miseAJourAffichage();
        	IG.attendreClic();
    		IG.fermerFenetreJeu();
    		System.exit(0);
        }
        
    
        
    
        
    }
