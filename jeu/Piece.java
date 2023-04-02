package echecs.jeu;

public abstract class Piece {
	private char symbole;
	private int colonne;
	private int ligne;
	private String couleur;
	private boolean dead;
	private boolean roi;
	
	public Piece(char symbole,int ligne,int colonne, Plateau plateau){
		this.symbole=symbole;
		this.colonne=colonne;
		this.ligne=ligne;
		if (Character.isUpperCase(symbole)) {
			this.couleur="blanc";
		}
		else{
			this.couleur="noir";
		}
		this.dead=false;
		this.roi=false;
	}
	
	public void couronnement() {
		this.roi=true;
	}
	
	public boolean estRoi() {
		return roi;
	}
	
	public char getSymbole() {
		return this.symbole;
	}
	
	public int getColonne() {
		return this.colonne;
	}
	
	public int getLigne() {
		return this.ligne;
	}
	
	public String getCouleur() {
		return this.couleur;
	}
	
	public void setColonne(int colonne) {
		this.colonne=colonne;
	}
	
	public void setLigne(int ligne) {
		this.ligne=ligne;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void seFaitManger() {
		this.dead=true;
	}
	
	public void changerDeCase(Coup coup) {
		int dc=coup.getDestCol();
		int dl=coup.getDestLi();
		if (coup.getPlateau()[dl][dc]!=null && coup.getPlateau()[dl][dc].getCouleur()!=this.couleur) {
			coup.getPlateau()[dl][dc].seFaitManger();
		}
		this.colonne=dc;
		this.ligne=dl;
		
	}
	
	public boolean peutAller(int dc, int dl, Plateau plateau) {
		return (peutArriver(dc,dl) && chemin(dc,dl, plateau));
	}
	
	
	public abstract boolean peutArriver(int dc, int dl);
	public abstract boolean chemin(int dc, int dl, Plateau plateau);
	
	
	
	
}
