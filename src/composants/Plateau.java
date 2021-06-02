package composants;

import java.util.Vector;

/**
 * Cette classe permet de gÃ©rer un plateau de jeu constituÃ© d'une grille de piÃ¨ces (grille de 7 lignes sur 7 colonnes).
 *
 */
public class Plateau {

	private Piece plateau[][]; // La grille des piÃ¨ces.

	/**
	 * 17/05/2021 Victor Fini (Quand Qui Statut)
	 * 
	 * Constructeur permettant de construire un plateau vide (sans piÃ¨ces) et d'une taille de 7 lignes sur 7 colonnes.
	 */
	public Plateau() {
		// A ComplÃ©ter
		this.plateau = new Piece[7][7];

	}

	/**
	 * 17/05/2021 Victor Fini (Quand Qui Statut)
	 * 
	 * MÃ©thode permettant de placer une piÃ¨ce sur le plateau.
	 * 
	 * @param piece La piÃ¨ce Ã  placer.
	 * @param lignePlateau La ligne du plateau sur laquelle sera placÃ©e la piÃ¨ce (un entier entre 0 et 6).
	 * @param colonnePlateau La colonne du plateau sur laquelle sera placÃ©e la piÃ¨ce (une entier entre 0 et 6).
	 */
	public void positionnePiece(Piece piece,int lignePlateau,int colonnePlateau){
		// A ComplÃ©ter
		plateau[lignePlateau][colonnePlateau] = piece;
	}

	/**
	 * 17/05/2021 Victor Fini (Quand Qui Statut)
	 * 
	 * MÃ©thode retournant une piÃ¨ce se trouvant sur le plateau Ã  un emplacement spÃ©cifique.
	 * 
	 * @param lignePlateau La ligne du plateau  (un entier entre 0 et 6).
	 * @param colonnePlateau La colonne du plateau (un entier entre 0 et 6).
	 * @return La piÃ¨ce se trouvant sur la ligne lignePlateau et la colonne colonnePlateau. Si il n'y a pas de piÃ¨ce, null est retournÃ©.
	 */
	public Piece getPiece(int lignePlateau,int colonnePlateau){
		if (lignePlateau <= 6 && lignePlateau >= 0 && colonnePlateau <= 6 && colonnePlateau >= 0) {
			return plateau[lignePlateau][colonnePlateau]; // A Modfier
		}
		else {
			return null;
		}
	}

	/**
	 * 
	 * 17/05/2021 Victor Fini (Quand Qui Statut)
	 *  
	 * MÃ©thode permettant de placer alÃ©atoirment 49 piÃ¨ces du jeu sur le plateau.
	 * L'orientation des piÃ¨ces est alÃ©atoire. Les piÃ¨ces utilisÃ©es doivent Ãªtre des nouvelles piÃ¨ces gÃ©nÃ©rÃ©es Ã  partir de la mÃ©thode Piece.nouvellesPieces.
	 * Parmi les 50 piÃ¨ces du jeu, la piÃ¨ce qui n'a pas Ã©tÃ© placÃ©e sur le plateau est retournÃ©e par la mÃ©thode.
	 * 
	 * @return La seule piÃ¨ce qui n'a pas Ã©tÃ© placÃ©e sur le plateau
	 */
	public Piece placerPiecesAleatoierment(){
		Piece[] tmp = Piece.nouvellesPieces();
		int[] tabIndex = Utils.genereTabIntAleatoirement(50);
		int count = 0;
		for(int i = 0; i < 7; i++) {
			for(int n = 0; n < 7; n ++ ) {
				plateau[i][n] = tmp[tabIndex[count]];
				count++;
			}
		}
		// A ComplÃ©ter
		return tmp[49]; // A Modfier
	}

	/**
	 * 
	 * MÃ©thode utilitaire permettant de tester si les positions passÃ©es en paramÃ¨tre sont les positions de deux cases diffÃ©rentes et adjacentes d'une grille de 7 lignes sur 7 colonnes.
	 *  
	 * @param posLigCase1 Un entier quelconque.
	 * @param posColCase1 Un entier quelconque.
	 * @param posLigCase2 Un entier quelconque.
	 * @param posColCase2 Un entier quelconque.
	 * @return true si les les positions passÃ©es en paramÃ¨tre sont les positions de deux cases diffÃ©rentes et adjacentes d'une grille de 7 lignes sur 7 colonnes, false sinon.
	 */
	private static boolean casesAdjacentes(int posLigCase1,int posColCase1,int posLigCase2,int posColCase2){
		if ((posLigCase1<0)||(posLigCase2<0)||(posLigCase1>6)||(posLigCase2>6)) return false;
		if ((posColCase1<0)||(posColCase2<0)||(posColCase1>6)||(posColCase2>6)) return false;
		int distLigne=posLigCase1-posLigCase2;
		if (distLigne<0) distLigne=-distLigne;
		int distColonne=posColCase1-posColCase2;
		if (distColonne<0) distColonne=-distColonne;
		if ((distLigne>1)||(distColonne>1)||((distColonne+distLigne)!=1))
			return false;
		return true;
	}

	/**
	 * 
	 * 17/05/2021 Victor Fini (Quand Qui Statut)
	 * 
	 * MÃ©thode permettant de tester si les positions passÃ©es en paramÃ¨tre sont les positions de deux cases diffÃ©rentes et adjacentes 
	 * de la grille de jeu et qu'il est possible de passer d'une cas Ã  l'autre compte tenu des deux piÃ¨ces posÃ©es sur les deux cases du plateau.
	 * 
	 * @param posLigCase1 Un entier quelconque.
	 * @param posColCase1 Un entier quelconque.
	 * @param posLigCase2 Un entier quelconque.
	 * @param posColCase2 Un entier quelconque.
	 * @return true si les positions passÃ©es en paramÃ¨tre sont les positions de deux cases diffÃ©rentes et adjacentes de la grille de jeu et qu'il est possible de passer d'une cas Ã  l'autre compte tenu des deux piÃ¨ces posÃ©es sur les deux cases du plateau, false sinon.
	 */
	private boolean passageEntreCases(int posLigCase1,int posColCase1,int posLigCase2,int posColCase2){
		
		boolean resultat = false;
		if (casesAdjacentes(posLigCase1, posColCase1, posLigCase2, posColCase2)) {
			if(posLigCase1<posLigCase2 && plateau[posLigCase1][posColCase1].getPointEntree(2) && plateau[posLigCase2][posColCase2].getPointEntree(0)) { //position relatif Bas
				resultat = true;
			}
			else if(posColCase1>posColCase2 && plateau[posLigCase1][posColCase1].getPointEntree(3) && plateau[posLigCase2][posColCase2].getPointEntree(1)) { //position relatif Haut
				resultat = true;
			}
			else if (posLigCase1>posLigCase2 && plateau[posLigCase1][posColCase1].getPointEntree(0) && plateau[posLigCase2][posColCase2].getPointEntree(2)) {//position relatif Droite
				resultat = true;
			}
			else if (posColCase1<posColCase2 && plateau[posLigCase1][posColCase1].getPointEntree(1) && plateau[posLigCase2][posColCase2].getPointEntree(3)) { //position relatif Gauche
				resultat = true;
			}
		}
		return resultat;
	}

	
	private int nbPassagePossible(int ligActuelle,int colActuelle, boolean[][] pieceNonVisitee) { //Changer en nombre d'entr�es possible actuelle vers de nouvelles cases
		int nbEntrees = 0;
		if(passageEntreCases(ligActuelle, colActuelle, ligActuelle-1, colActuelle) && pieceNonVisitee[ligActuelle-1][colActuelle])  {
			nbEntrees = nbEntrees + 1;

		}
		if(passageEntreCases(ligActuelle, colActuelle, ligActuelle, colActuelle-1) && pieceNonVisitee[ligActuelle][colActuelle-1]) {
			nbEntrees = nbEntrees + 1;

		}
		if(passageEntreCases(ligActuelle, colActuelle, ligActuelle+1, colActuelle) && pieceNonVisitee[ligActuelle+1][colActuelle]) {
			nbEntrees = nbEntrees + 1;

		}
		if(passageEntreCases(ligActuelle, colActuelle, ligActuelle, colActuelle+1) && pieceNonVisitee[ligActuelle][colActuelle+1]) {
			nbEntrees = nbEntrees + 1;

		}
		return nbEntrees;
	}
	
	
	
	
	
	
	/**
	 * 
	 * 17/05/2021 Victor Fini (Quand Qui Statut)
	 * 
	 * M�thode permettant de retourner un �ventuel chemin entre deux cases du plateau compte tenu des pi�ces pos�es sur le plateau.
     * Dans le cas o� il n'y a pas de chemin entre les deux cases, la valeur null est retourn�e.
     * Dans le cas o� il existe un chemin, un chemin possible est retourn� sous forme d'un tableau d'entiers �  deux dimensions.
     * La premi�re dimension correspond aux cases du plateau �  emprunter pour aller de la case de d�part �  la case d'arriv�e.
     * Dans ce tableau, chaque case est un tableau de deux entiers avec le premier entier qui correspond �  la ligne de la case et
     * le second entier qui correspond �  la colonne de la case. La premi�re case d'un chemin retourn� correspond toujours 
     * �  la case (posLigCaseDep,posColCaseDep) et la derni�re case correspond toujours �  la case (posLigCaseArr,posColCaseArr).
	 *
	 * @param posLigCaseDep La ligne de la case de dÃ©part (un entier compris entre 0 et 6).
	 * @param posColCaseDep La colonne de la case de dÃ©part (un entier compris entre 0 et 6).
	 * @param posLigCaseArr La ligne de la case d'arrivÃ©e (un entier compris entre 0 et 6).
	 * @param posColCaseArr La colonne de la case d'arrivÃ©e (un entier compris entre 0 et 6).
	 * @return null si il n'existe pas de chemin entre les deux case, un chemin sinon.
	 */
	public int[][] calculeChemin(int posLigCaseDep,int posColCaseDep,int posLigCaseArr,int posColCaseArr){
		int resultat[][]=null;
		int ligActuelle = posLigCaseDep;
		int colActuelle = posColCaseDep;
		boolean pieceNonVisitee[][] = new boolean[7][7]; //Tableau permettant de stocker les cases d�j� visit�s
		Vector<int[]> chemin = new Vector<int[]>();
		
		for(int i = 0; i < 7 ; i++) { //Initialise le tableau de true;
			for(int n = 0; n < 7; n++) {
				pieceNonVisitee[i][n] = true;
			}
		}
		
		//Initialisation des attributs pour la premiere piece / case de depart
		pieceNonVisitee[ligActuelle][colActuelle] = false;
		chemin.add(new int[] {posLigCaseDep,posColCaseDep});
		
		while(ligActuelle != posLigCaseArr || colActuelle !=  posColCaseArr) {
			if(nbPassagePossible(ligActuelle,colActuelle,pieceNonVisitee) >= 1) {
				//V�rification si il y a un passage entre les diff�rentes cases, que l'entr�e est diff�rente de la sortie et si cette m�me case n'a �t� visit�
				if(passageEntreCases(ligActuelle, colActuelle, ligActuelle-1, colActuelle) && pieceNonVisitee[ligActuelle-1][colActuelle]) {
					ligActuelle -= 1;
				}
				else if (passageEntreCases(ligActuelle, colActuelle, ligActuelle, colActuelle-1) && pieceNonVisitee[ligActuelle][colActuelle-1]) {
					colActuelle -= 1;
				}
				else if (passageEntreCases(ligActuelle, colActuelle, ligActuelle+1, colActuelle) && pieceNonVisitee[ligActuelle+1][colActuelle]) {
					ligActuelle +=1;
				}
				else if(passageEntreCases(ligActuelle, colActuelle, ligActuelle, colActuelle+1) && pieceNonVisitee[ligActuelle][colActuelle+1]) {
					colActuelle += 1;

				}
				int[] couple = new int[2];
				couple[0] = ligActuelle;
				couple[1] = colActuelle;
				pieceNonVisitee[ligActuelle][colActuelle] = false;
				chemin.add(couple);
			}		
			else {
				chemin.remove(chemin.size()-1);
				if (chemin.size() >= 1) {
					int posL = chemin.get(chemin.size()-1)[0];
					int posC = chemin.get(chemin.size()-1)[1];
					if(ligActuelle-1 == posL) { //V�rification si case du dessus correspond � la derni�re pi�ce visit�, hors actuelle
						ligActuelle = posL;
					}
					else if(colActuelle+1 == posC) { //V�rification si case � droite correspond � la derni�re pi�ce visit�, hors actuelle
						colActuelle = posC;
					}
					else if(ligActuelle+1 == posL) { //V�rification si case du bas correspond � la derni�re pi�ce visit�, hors actuelle
						ligActuelle = posL;
					}
					else if(colActuelle-1 == posC) { //V�rification si case � droite correspond � la derni�re pi�ce visit�, hors actuelle
						colActuelle = posC;
					}
				}
				else {
					return null;
				}

			}
		}

		resultat = new int[chemin.size()][2];
		for(int i = 0; i < chemin.size(); i++) {
			resultat[i][0] = chemin.get(i)[0];
			resultat[i][1] = chemin.get(i)[1];
		}
		return resultat;
	}



	/**
	 * 
	 * MÃ©thode permettant de calculer un chemin dÃ©taillÃ© (chemin entre sous-cases) Ã  partir d'un chemin entre cases.
	 *  
	 * @param chemin Un tableau reprÃ©sentant un chemin de cases.
	 * @param numJoueur Le numÃ©ro du joueur pour lequel nous souaitons construire un chemin dÃ©taillÃ©.
	 * 
	 * @return Le chemin dÃ©taillÃ© correspondant au chemin de positions de piÃ¨ces donnÃ©es en paramÃ¨tre et pour le numÃ©ro de joueur donnÃ©.
	 */
	public int[][] calculeCheminDetaille(int[][] chemin,int numJoueur){
		if (chemin.length==1)
			return new int[0][0];
		int[][] cheminDetaille=new int[chemin.length*5][4];
		int pos=0;
		int col,lig,colS,ligS;
		for (int i=0;i<chemin.length-1;i++){
			lig=chemin[i][0];
			col=chemin[i][1];
			ligS=chemin[i+1][0];
			colS=chemin[i+1][1];
			if (ligS<lig){
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=0;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=ligS;
				cheminDetaille[pos][1]=colS;
				cheminDetaille[pos][2]=2;
				cheminDetaille[pos++][3]=1;
			}
			else if (ligS>lig){
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=2;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=ligS;
				cheminDetaille[pos][1]=colS;
				cheminDetaille[pos][2]=0;
				cheminDetaille[pos++][3]=1;
			} else if (colS<col){
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=0;
				cheminDetaille[pos][0]=ligS;
				cheminDetaille[pos][1]=colS;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=2;
			} else if (colS>col){
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=1;
				cheminDetaille[pos][0]=lig;
				cheminDetaille[pos][1]=col;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=2;
				cheminDetaille[pos][0]=ligS;
				cheminDetaille[pos][1]=colS;
				cheminDetaille[pos][2]=1;
				cheminDetaille[pos++][3]=0;
			}
		}
		cheminDetaille[pos][0]=chemin[chemin.length-1][0];
		cheminDetaille[pos][1]=chemin[chemin.length-1][1];
		cheminDetaille[pos][2]=1;
		cheminDetaille[pos++][3]=1;

		int debut=0;
		if ((numJoueur==0)&&((cheminDetaille[pos-2][2]==0)||(cheminDetaille[pos-2][3]==2))) pos--;
		if ((numJoueur==1)&&((cheminDetaille[pos-2][2]==2)||(cheminDetaille[pos-2][3]==2))) pos--;
		if ((numJoueur==2)&&((cheminDetaille[pos-2][2]==2)||(cheminDetaille[pos-2][3]==0))) pos--;
		if ((numJoueur==0)&&((cheminDetaille[1][2]==0)||(cheminDetaille[0][3]==2))) debut++;
		if ((numJoueur==1)&&((cheminDetaille[1][2]==2)||(cheminDetaille[0][3]==2))) debut++;
		if ((numJoueur==2)&&((cheminDetaille[1][2]==2)||(cheminDetaille[0][3]==0))) debut++;

		int[][] resultat=new int[pos-debut][4];
		for (int i=debut;i<pos;i++)
			for (int j=0;j<4;j++)
				resultat[i-debut][j]=cheminDetaille[i][j];
		return resultat;	
	}

	/**
	 * 
	 * MÃ©thode retournant une copie du plateau avec des copies de ses piÃ¨ces.
	 * 
	 * @return Une copie du plateau avec une copie de toutes ses piÃ¨ces.
	 */
	public Plateau copy(){
		Plateau plateau=new Plateau();
		for (int ligne=0;ligne<7;ligne++)
			for (int colonne=0;colonne<7;colonne++)
				plateau.positionnePiece((this.plateau[ligne][colonne]).copy(), ligne, colonne);
		return plateau;
	}

}
