package composants;

import java.util.Date;
import java.util.Random;

/**
 * 
 * Classe contenant quelques fonctions utilitaires.
 * 
 */
public class Utils {
	
	private static Random generateur=new Random((new Date().getTime()));

	/**
	 * A Faire (Quand Qui Statut)
	 * 
	 * Méthode permettant de générer aléatoirement un nombre entier.
	 * 
	 * @param max Le nombre entier maximal pouvant être retourné.
	 * @return Un nombre entier compris entre 0 et max (inclus).
	 */
	public static int genererEntier(int max){
		Random random = new Random();
		
		return random.nextInt(max+1);
	}
	/**
	 * A Faire (Quand Qui Statut)
	 * 
	 * Méthode permettant de générer un tableau d'entiers dont la longueur longTab est donnée en paramètre.
	 * Le tableau généré doit contenir chaque entier compris entre 0 et longTab-1. La position de ces entiers
	 * dans le tableau doit être aléatoire.
	 * 
	 * @param longTab La longueur du tableau.
	 * @return Un tableau contenant les entiers 0,...,longTab-1 placés aléatoirement dans le tableau.
	 */
	public static int[] genereTabIntAleatoirement(int longTab){
		int tab[]=null;
		tab = new int[longTab];
		int tmp = 0;
		boolean existe = false;
		for (int i =0;i<longTab;i++) {
			tmp = genererEntier(longTab-1);
			int j =0;
		}
		
		
		return tab;
	}
	/**
	 * Programme testant la méthode genereTabIntAleatoirement.
	 * @param args arguments du programme
	 */
	public static void main(String[] args) {
		// Un petit test ...
		int tab[]=genereTabIntAleatoirement(18);
		for (int i=0;i<tab.length;i++)
			System.out.print(tab[i]+" ");
			
	}

}
