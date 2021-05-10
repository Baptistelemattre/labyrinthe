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
		modifierEntree();
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
	
	private void modifierEntree() {
		boolean temp[] = pointsEntree;
		pointsEntree[0] = temp[3];
		pointsEntree[1] = temp[0];
		pointsEntree[2] = temp[1];
		pointsEntree[3] = temp[2] ;
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
	 * Faite le 28/04/2021 par fardoux benoit
	 * 
	 * Méthode indiquant si il existe un point d'entrée à une certaine position (0: en haut, 1: à droite, 2: en bas, 3: à gauche).
	 * @param pointEntree L'indice/la position du point d'entrée.
	 * @return true si il y a un point d'entrée, sinon false.
	 */
	public boolean getPointEntree(int pointEntree){
		return this.pointsEntree[pointEntree];
	}
	
	/**
	 * 
	 * 
	 * Méthode permettant de créer un tableau contenant toutes les pièces du jeu (les 50 pièces).
	 * Le tableau contiendra 20 pièces du modèle 0, 12 pièces du modèle 1 et 18 pièces du modèle 2.
	 * L'orientation de chaque pièce sera aléatoire.
	 * @return Un tableau contenant toutes les pièces du jeu.
	 */
	public static Piece[] nouvellesPieces(){
		Piece pieces[]= null;
		// A Compléter (A Faire après les classes PieceM0, PieceM1 et PieceM2)
		return pieces;
	}
	
	/**
	 * Méthode permettant de créer une copie de la pièce (un nouvelle objet Java).
	 * @return Une copie de la pièce.
	 */
	public abstract Piece copy();
}
