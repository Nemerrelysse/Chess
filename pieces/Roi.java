package echecs.pieces;

import echecs.jeu.Piece;
import echecs.jeu.Plateau;

public class Roi extends Piece{
	public boolean echec;
	public Roi(char symbole,int ligne,int colonne, Plateau plateau) {
		super(symbole,ligne,colonne,plateau);
		couronnement();
		this.echec=false;
	}

	@Override
	public boolean peutArriver(int dc, int dl) {
		int sc=getColonne();
		int sl=getLigne();
		return dc==sc+1 && dl==sl||
				dc==sc+1 && dl==sl+1 ||
				dc==sc+1 && dl==sl-1 ||
				dc==sc-1 && dl==sl ||
				dc==sc-1 && dl==sl+1 ||
				dc==sc-1 && dl==sl-1 ||
				dl==sl+1 && dc==sc ||
				dl==sl-1 && dc==sc;	
	}
	
	
	
	public boolean versEchec(int dc, int dl, Plateau plateau) {
		Piece pieceADeplacer=plateau.tableauGrille()[getLigne()][getColonne()];
		for(Piece p : plateau.getPieces()) {
			if(p!=pieceADeplacer && (pieceADeplacer.getCouleur()!=p.getCouleur()) && p.peutAller(dc,dl,plateau)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	

	public boolean enEchec(Plateau plateau) {
		for(Piece p : plateau.getPieces()) {
			if((this.getCouleur()!=p.getCouleur()) && p.peutAller(getColonne(),getLigne(),plateau)) {
				return true;
			}
		}
		return false;
	}
	
	

	@Override
	public boolean chemin(int dc, int dl, Plateau plateau) {
		return true;
	}
	
	
	
	
	
	

	
	
	

}
