package echecs.jeu;

import java.util.Collections;
import java.util.List;

public class Plateau {
	private int hauteur;
	private int largeur;
	private List<Piece> pieces;
	private List<Joueur> joueurs;
	private Piece[][]grille;
	
	
	public Plateau(int hauteur, int largeur, List<Piece> pieces, List<Joueur> joueurs) {
		this.hauteur=hauteur;
		this.largeur=largeur;
		this.pieces=pieces;
		this.grille=tableauGrille();
		this.joueurs=joueurs;
	}
	
	public List<Piece> getPieces(){
		return pieces;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	public int getLargeur() {
		return largeur;
	}
	
	public void joueurSuivant() {
		Collections.swap(joueurs,0,1);
	}
	
	
	
	public void refreshGrille() {
		this.grille=tableauGrille();
	}
	
	
	
	public Piece[][] tableauGrille() {
		Piece[][] grille= new Piece[hauteur][largeur];
		for(int i=0;i<pieces.size();++i) {
			grille[pieces.get(i).getLigne()][pieces.get(i).getColonne()]=pieces.get(i);
		}
		return grille;
	}
	
	public void checkDeath() {
		int i=0;
		while(i<pieces.size()) {
			if(pieces.get(i).isDead()) {
				System.out.println(pieces.get(i).getSymbole()+" a �t� mang�.");
				pieces.remove(i);
			}
			else i++;
		}
	}
	
	public StringBuilder getColonnes() {
		char lettre ='A';
		StringBuilder colonnes=new StringBuilder("   ");
		String sep="";
		for(int i=0;i<largeur;++i) {
			colonnes.append(sep+lettre);
			sep="   ";
			++lettre;
		}
		return colonnes;
	}
	
	
	
	public StringBuilder interligne() {
		StringBuilder interligne=new StringBuilder(" ");
	//cr�ation du String interligne de la largeur du plateau
		for(int j=0;j<largeur;++j) {
		interligne.append(" ---");
		}
		return interligne;
	}
	
	public StringBuilder getGrille() {
		int chiffre =hauteur;
		StringBuilder lignes=new StringBuilder("");
		StringBuilder ligneGrille=new StringBuilder("|");
		StringBuilder interligne=interligne();
		for(int i=0;i<hauteur;++i) {
			ligneGrille.setLength(0);
			ligneGrille.append("|");
			for(int j=0;j<largeur;++j) {
				//cr�ation d'un emplacement vide
				if (grille[i][j]==null) {
					ligneGrille.append("   |");}
				//cr�ation d'un emplacement contenant une pi�ce
				else {
					ligneGrille.append(" "+grille[i][j].getSymbole()+" |");
				}
			}
			//assemblage de la ligne
			lignes.append(interligne+"\n"+chiffre+ligneGrille+chiffre+"\n");
			--chiffre;	
		}
		//ajour du dernier interligne
		lignes.append(interligne+"\n");
		return lignes;
	}
	
	public String getPlateau() {
		String plateau=(getColonnes()+"\n"+getGrille()+getColonnes());
		return plateau;
	}
	
	public boolean deuxRoisVivants() {
		int roiVivant=0;
		for(Piece element : pieces) {
			if(element.estRoi()&& !element.isDead()) {
				++roiVivant;
				}
		}
		return roiVivant==2;
	}
	
	public Piece getCase(int ligne, int colonne) {
		return grille[ligne][colonne];
	}
	
	
	
	
	

}
