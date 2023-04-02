package echecs.jeu;


import java.util.Scanner;

import echecs.pieces.Roi;

public class Coup {
	private Joueur joueur;
	private Plateau plateau;
	private String coup;
	private int startCol;
	private int startLi;
	private int destCol;
	private int destLi;
	
	
	public Coup(Joueur joueur,Plateau plateau, Scanner scanner) {
		this.joueur=joueur;
		this.plateau=plateau;
		this.coup=askCoup(scanner);
		this.startCol=getStartCol();
		this.startLi=getStartLi();
		this.destCol=getDestCol();
		this.destLi=getDestLi();
		
	}
	
	public String askCoup(Scanner scanner) {
		System.out.println(joueur.getNom()+", saisis ton coup :");
	    System.out.println("(Exemple : pour déplacer sa pièce de A1 vers B2, saisir A1B2)");
	    return scanner.nextLine();
	}
	
	public int getStartCol() {
		return (int)coup.charAt(0)-65;
	}
	
	public int getStartLi() {
		return plateau.getHauteur()-((int)coup.charAt(1)-48);
	}
	
	public int getDestCol() {
		return (int)coup.charAt(2)-65;
	}
	
	public int getDestLi() {
		return plateau.getHauteur()-((int)coup.charAt(3)-48);
	}
	
	public Piece[][] getPlateau(){
		return plateau.tableauGrille();
	}
	
	public boolean coupValide() {
		if(startCol<0 || startCol>=plateau.getLargeur() || 
				destCol<0 || destCol>=plateau.getLargeur() ||
				startLi<0 || startLi>=plateau.getHauteur() ||
				destLi<0 || destLi>=plateau.getHauteur() ) {
			return false;
		}
		if (plateau.tableauGrille()[startLi][startCol]==null) {
			return false;
		}
		Piece pieceADeplacer=plateau.tableauGrille()[startLi][startCol];
		if(plateau.tableauGrille()[destLi][destCol]!=null) {
			if(plateau.tableauGrille()[destLi][destCol].getCouleur()==pieceADeplacer.getCouleur()) {
				return false;
			}
		}
		return (pieceADeplacer.getCouleur()==joueur.getCouleur());
	}
	
	
	
	public boolean jouerCoup() {
		if(!coupValide()) {
			return false;
		}
		int startCol=getStartCol();
		int startLi=getStartLi();
		int destCol=getDestCol();
		int destLi=getDestLi();
		Piece p=plateau.getCase(startLi, startCol);
		if(!p.estRoi()&&!p.peutAller(destCol, destLi, plateau)) {
			return false;
		}
		if(p.estRoi()) {
			if(!p.peutAller(destCol, destLi, plateau) || ((Roi) p).versEchec( destCol,  destLi,  plateau)) {
				return false;
			}
		}
		
		
			p.changerDeCase(this);
			plateau.checkDeath();
			plateau.refreshGrille();
			return true;
		
		
	}

}
