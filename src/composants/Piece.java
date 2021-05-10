package composants;

/**
 *
 * Cette classe permet de reprÃ©senter les diffÃ©rentes piÃ¨ces du jeu.
 *
 */
abstract public class Piece {

	private int modelePiece; 		// Le modÃ¨le de la piÃ¨ce
	private int orientationPiece; 	// L'orientation de la piÃ¨ce
	private boolean[] pointsEntree; // Les points d'entrÃ©e indice 0 pour le haut, 1 pour la droite, 2 pour le bas et 3 pour la gauche.

	/**
	 * Faite le 28/04/2021 par fardoux benoit
	 *
	 * Constructeur permettant de crÃ©er une piÃ¨ce d'un modÃ¨le avec l'orientation 0.
	 * @param modelePiece Le modÃ¨le de la piÃ¨ce.
	 * @param pointEntreeHaut Un boolÃ©en indiquant si la piÃ¨ce a un point d'entrÃ©e en haut.
	 * @param pointEntreeDroite Un boolÃ©en indiquant si la piÃ¨ce a un point d'entrÃ©e Ã  droite.
	 * @param pointEntreeBas Un boolÃ©en indiquant si la piÃ¨ce a un point d'entrÃ©e en bas.
	 * @param pointEntreeGauche Un boolÃ©en indiquant si la piÃ¨ce a un point d'entrÃ©e Ã  gauche.
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
	 * MÃ©thoide retournant un String reprÃ©sentant la piÃ¨ce.
	 */
	@Override
	public String toString() {
		return "[m:"+modelePiece+"|o:"+orientationPiece+"|pe:"+pointsEntree[0]+" "+pointsEntree[1]+" "+pointsEntree[2]+" "+pointsEntree[3]+"]";
	}

	/**
	 * Faite le 28/04/2021 par fardoux benoit
	 *
	 * MÃ©thode permettant de rotationner une piÃ¨ce dans le sens d'une horloge.
	 */
	public void rotation(){
		if (orientationPiece !=3) {
			orientationPiece++;
		}else orientationPiece = 0;
		modifierEntree();
	}

	/**
	 * Faite le 28/04/2021 par fardoux benoit
	 *
	 * MÃ©thode permettant d'orienter une piÃ¨ce vers une orientation spÃ©cifique.
	 * @param orientationPiece Un entier correspondant Ã  la nouvelle orientation de la piÃ¨ce.
	 */
	public void setOrientation(int orientationPiece){
		while (this.orientationPiece != orientationPiece)rotation();
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
	 * MÃ©thode retournant le modÃ¨le de la piÃ¨ce.
	 * @return Un entier corrspondant au modÃ¨le de la piÃ¨ce.
	 */
	public int getModelePiece() {
		return modelePiece;
	}

	/**
	 * Faite le 28/04/2021 par fardoux benoit
	 *
	 * MÃ©thode retournant l'orientation de la piÃ¨ce.
	 * @return un entier retournant l'orientation de la piÃ¨ce.
	 */
	public int getOrientationPiece() {

		return orientationPiece;
	}
	public void setPointsEntree(boolean pointEntreeHaut,boolean pointEntreeDroite,boolean pointEntreeBas,boolean pointEntreeGauche) {
		this.pointsEntree[0] = pointEntreeHaut;
		this.pointsEntree[1] = pointEntreeDroite;
		this.pointsEntree[2] = pointEntreeBas;
		this.pointsEntree[3] = pointEntreeGauche;
	}
	/**
	 * Faite le 28/04/2021 par fardoux benoit
	 *
	 * MÃ©thode indiquant si il existe un point d'entrÃ©e Ã  une certaine position (0: en haut, 1: Ã  droite, 2: en bas, 3: Ã  gauche).
	 * @param pointEntree L'indice/la position du point d'entrÃ©e.
	 * @return true si il y a un point d'entrÃ©e, sinon false.
	 */
	public boolean getPointEntree(int pointEntree){
		return this.pointsEntree[pointEntree];
	}
	/*
       Méthode privée permettant d'ajouter à l'index d'un tableau une pièce qu'importe son modèle
       ,cette dernière obtiendra une orientation aléatoire entre 0 et 3 puis elle incrémente le compteur
       associé.

       @param tabPiece correspond au tableau de l'interface, piece c'est le modèle de piece choisi
       index c'est l'index actuelle où on se situe dans tabPiece, counter le compteur actuelle de la pièce choisi
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
	 *
	 *
	 * MÃ©thode permettant de crÃ©er un tableau contenant toutes les piÃ¨ces du jeu (les 50 piÃ¨ces).
	 * Le tableau contiendra 20 piÃ¨ces du modÃ¨le 0, 12 piÃ¨ces du modÃ¨le 1 et 18 piÃ¨ces du modÃ¨le 2.
	 * L'orientation de chaque piÃ¨ce sera alÃ©atoire.
	 * @return Un tableau contenant toutes les piÃ¨ces du jeu.
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
	 * MÃ©thode permettant de crÃ©er une copie de la piÃ¨ce (un nouvelle objet Java).
	 * @return Une copie de la piÃ¨ce.
	 */
	public abstract Piece copy();
}
