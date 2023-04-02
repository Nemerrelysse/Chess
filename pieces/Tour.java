package echecs.pieces;

import echecs.jeu.Piece;
import echecs.jeu.Plateau;

public class Tour extends Piece{
	public Tour(char symbole,int ligne,int colonne, Plateau plateau) {
		super(symbole,ligne,colonne,plateau);
	}
	
	public boolean peutArriver(int dc, int dl) {
		int sc=getColonne();
		int sl=getLigne();
		return sc==dc || sl==dl;
	}
	
	public boolean chemin(int dc, int dl, Plateau plateau) {
		int sc=getColonne();
		int sl=getLigne();
		if(sc==dc) {
			int distance=dl-sl;
			if (distance<0) {
				for(int i=0;i<distance;++i) {
					if (plateau.getCase(sl+1, sc)!=null) {
						return false;
					}
					++sl;
				}
			}
			else {
				for(int i=0;i>distance;++i) {
					if (plateau.getCase(sl-1, sc)!=null) {
						return false;
					}
					--sl;
				}
			}
			
			}
		else if(sl==dl) {
			int distance=dc-sc;
			if (distance<0) {
				for(int i=0;i<distance;++i) {
					if (plateau.getCase(sc+1, sl)!=null) {
						return false;
					}
					++sc;
				}
			}
			else {
				for(int i=0;i>distance;++i) {
					if (plateau.getCase(sc-1, sl)!=null) {
						return false;
					}
					--sc;
				}
			}
		}
		
		return true;
		
			
	}
	

}
