package composants;

/**
 * 
 * Cette classe permet de représenter les différentes pièces du jeu.
 * 
 */
abstract public class Piece {

	private int modelePiece; 		// Le modèle de la pièce
	private int orientationPiece; 	// L'orientation de la pièce
	private boolean[] pointsEntree; // Les points d'entrée indice 0 pour le haut, 1 pour la droite, 2 pour le bas et 3 pour la gauche.

	/**
	 * Faite le 28/04/2021 par fardoux benoit
	 * 
	 * Constructeur permettant de créer une pièce d'un modèle avec l'orientation 0.
	 * @param modelePiece Le modèle de la pièce.
	 * @param pointEntreeHaut Un booléen indiquant si la pièce a un point d'entrée en haut.
	 * @param pointEntreeDroite Un booléen indiquant si la pièce a un point d'entrée à droite.
	 * @param pointEntreeBas Un booléen indiquant si la pièce a un point d'entrée en bas.
	 * @param pointEntreeGauche Un booléen indiquant si la pièce a un point d'entrée à gauche.
	 */
	public Piece(int modelePiece,boolean pointEntreeHaut,boolean pointEntreeDroite,boolean pointEntreeBas,boolean pointEntreeGauche){
		this.modelePiece = modelePiece;
		this.pointsEntree = new boolean[4];
		this.orientationPiece = 0;
		this.pointsEntree[0] = pointEntreeHaut;
		this.pointsEntree[1] = pointEntreeDroite;
		this.pointsEntree[2] = pointEntreeBas;
		this.pointsEntree[3] = pointEntreeGauche;
		
		
		
	}
	
	/**
	 * Méthoide retournant un String représentant la pièce.
	 */
	@Override
	public String toString() {
		return "[m:"+modelePiece+"|o:"+orientationPiece+"|pe:"+pointsEntree[0]+" "+pointsEntree[1]+" "+pointsEntree[2]+" "+pointsEntree[3]+"]";
	}
	
	/**
	 * Faite le 28/04/2021 par fardoux benoit
	 * 
	 * Méthode permettant de rotationner une pièce dans le sens d'une horloge.
	 */
	public void rotation(){
		if (orientationPiece !=3) {
			orientationPiece++;
		}else if (orientationPiece == 3)orientationPiece = 0;
		
	}
	
	/**
	 * Faite le 28/04/2021 par fardoux benoit
	 * 
	 * Méthode permettant d'orienter une pièce vers une orientation spécifique.
	 * @param orientationPiece Un entier correspondant à la nouvelle orientation de la pièce.
	 */
	public void setOrientation(int orientationPiece){
		this.orientationPiece = orientationPiece; 
	}

	/**
	 * Faite le 28/04/2021 par fardoux benoit
	 * 
	 * Méthode retournant le modèle de la pièce.
	 * @return Un entier corrspondant au modèle de la pièce.
	 */
	public int getModelePiece() {
		return modelePiece;
	}

	/**
	 * Faite le 28/04/2021 par fardoux benoit
	 * 
	 * Méthode retournant l'orientation de la pièce.
	 * @return un entier retournant l'orientation de la pièce.
	 */
	public int getOrientationPiece() {
		
		return orientationPiece;
	}

	/**
	 * Faite le 05/05/2021 par baptiste lemattre
	 * 
	 * Méthode indiquant si il existe un point d'entrée à une certaine position (0: en haut, 1: à droite, 2: en bas, 3: à gauche).
	 * @param pointEntree L'indice/la position du point d'entrée.
	 * @return true si il y a un point d'entrée, sinon false.
	 */
	public boolean getPointEntree(int pointEntree){
		return this.pointsEntree[pointEntree];
	}
	
	/**
	 * faite le 05/05/2021 par baptiste lemattre
	 * 
	 * Méthode permettant de créer un tableau contenant toutes les pièces du jeu (les 50 pièces).
	 * Le tableau contiendra 20 pièces du modèle 0, 12 pièces du modèle 1 et 18 pièces du modèle 2.
	 * L'orientation de chaque pièce sera aléatoire.
	 * @return Un tableau contenant toutes les pièces du jeu.
	 */
	public static Piece[] nouvellesPieces(){
		Piece pieces[]= null;
		Piece[] pieces = new Piece[50]; //Cr�er un tableau de 50 pi�ces.
	    //D�finit les constantes, le nombre de pi�ces maximum pour chaque mod�le.  
	        final int maxM0 = 20;
	        final int maxM1 = 12;
	        final int maxM2 = 18;
	    //Les compteurs pour les pi�ces de mod�le respectifs.
	        int nombreM0 = 0;
	        int nombreM1 = 0;
	        int nombreM2 = 0;
	        boolean isAdded = false;

	        for (int i = 0; i < pieces.length; i++){
	            int choixModele;
	            do{ // V�rification si le nombre de pi�ce du mod�le choisi n'est pas atteint.
	                choixModele = (int)(Math.random()*3);
	                if(choixModele == 0 && countM0 != maxM0){
	                    isAdded = true;
	                }
	                else if (choixModele == 1 && countM1 != maxM1){
	                    isAdded = true;
	                }
	                else if (choixModele == 2 && countM2 != maxM2){
	                    isAdded = true;
	                }
	            }while(!isAdded);
	            
	            //Ajoute et fait la rotation � l'index choisi.
	            if (choixModele == 0 && countM0 < maxM0){
	                Piece.addPieceAndRotation(pieces, new PieceM0(), i, countM0);
	            }
	            else if (choixModele == 1 && countM1 < maxM1){
	                Piece.addPieceAndRotation(pieces, new PieceM1(), i, countM1);
	            }
	            else if (choixModele == 2 && countM2 < maxM2){
	                Piece.addPieceAndRotation(pieces, new PieceM2(), i, countM2);
	            }
	        }
		return pieces;
	}
    /**
    M�thode priv�e permettant d'ajouter � l'index d'un tableau une pi�ce qu'importe son mod�le
    ,cette derni�re obtiendra une orientation al�atoire entre 0 et 3 puis elle incr�mente le compteur 
    associ�.

    @param tabPiece correspond au tableau de l'interface, piece c'est le mod�le de piece choisi
    index c'est l'index actuelle o� on se situe dans tabPiece, counter le compteur actuelle de la pi�ce choisi
    @return void.
    */
    private static void addPieceAndRotationAndRotate(Piece[] tabPiece, Piece piece, int index,int counter){
        counter++;
        int choixOrientation;
        choixOrientation = (int)(Math.random()*4);
        piece.setOrientation(0);
        tabPiece[index] = piece; 
    }
	/**
	 * Méthode permettant de créer une copie de la pièce (un nouvelle objet Java).
	 * @return Une copie de la pièce.
	 */
	public abstract Piece copy;
}
