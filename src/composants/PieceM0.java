package composants;

/**
 *
 * Cette classe permet de reprÃ©senter les piÃ¨ces du jeu de modÃ¨le 0.
 *
 */
public class PieceM0 extends Piece {

	/**
	 * Faite le 28/04/2021 par fardoux benoit
	 *
	 * Constructeur permettant de construire une piÃ¨ce de modÃ¨le 0 et d'orientation 0.
	 */
	public PieceM0() {

		super(0,false,true,true,false);
	}
	/**
	 * Faite le 28/04/2021 par fardoux benoit
	 *
	 * MÃ©thode permettant de crÃ©er une copie de la piÃ¨ce (un nouvelle objet Java).
	 * @return Une copie de la piÃ¨ce.
	 */
	public Piece copy(){
		Piece piece= new PieceM0();
		piece.setPointsEntree(this.getPointEntree(0), this.getPointEntree(1), this.getPointEntree(2), this.getPointEntree(3));
		return piece;
	}
}
