package composants;

/**
 * 
 * Cette classe permet de représenter les pièces du jeu de modèle 2.
 *
 */
public class PieceM2 extends Piece {

	/**
	 * Faite le 28/04/2021 par fardoux benoit
	 * 
	 * Constructeur permettant de construire une pièce de modèle 2 et d'orientation 0.
	 */
	public PieceM2() {
		super(2,true,true,false,true); 
	}
	/**
	 * Faite le 28/04/2021 par fardoux benoit
	 * 
	 * Méthode permettant de créer une copie de la pièce (un nouvelle objet Java).
	 * @return Une copie de la pièce.
	 */
	public Piece copy(){
		Piece piece= new PieceM2();
		piece.setPointsEntree(this.getPointEntree(0), this.getPointEntree(1), this.getPointEntree(2), this.getPointEntree(3));
		return piece;
	}
}
