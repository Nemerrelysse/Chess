package echecs.pieces;

import echecs.jeu.Piece;
import echecs.jeu.Plateau;

public class Fou extends Piece{
	public Fou(char symbole,int ligne,int colonne, Plateau plateau) {
		super(symbole,ligne,colonne,plateau);
	}
	
	public boolean peutArriver(int dc, int dl) {
		int sc=getColonne();
		int sl=getLigne();
		return (sc-dc)==(sl-dl)||(dc-sc)==(sl-dl);
	}
	
	public boolean chemin(int dc, int dl, Plateau plateau) {
		int sc=getColonne();
		int sl=getLigne();
		int cDir=dc-sc;
		int lDir=dl-sl;
		if (cDir>0 && lDir>0) {
			for(int i=0;i<cDir;i++) {
				if (plateau.getCase(sl+1, sc+1)!=null) {
					return false;
				}
				++sl;
				++sc;
			}
		}
		else if (cDir<0 && lDir<0) {
			for(int i=0;i>cDir;i--) {
				if (plateau.getCase(sl-1, sc-1)!=null) {
					return false;
				}
				--sl;
				--sc;
			}
		}
		else if (cDir>0 && lDir<0) {
			for(int i=0;i<cDir;i++) {
				if (plateau.getCase(sl-1, sc+1)!=null) {
					return false;
				}
				--sl;
				++sc;
			}
		}
		else if (cDir<0 && lDir>0) {
			for(int i=0;i>cDir;i--) {
				if (plateau.getCase(sl+1, sc-1)!=null) {
					return false;
				}
				++sl;
				--sc;
			}
		}
		return true;
	}

}
