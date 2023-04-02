package echecs.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import echecs.jeu.Coup;
import echecs.jeu.Joueur;
import echecs.jeu.Piece;
import echecs.jeu.Plateau;
import echecs.pieces.Fou;
import echecs.pieces.Roi;
import echecs.pieces.Tour;

class TestEchec {
	
	//coups à entrer dans l'ordre :
	// F6G6
	// H8G7
	// H8H9
	// D4B6
	// B6B5
	// C1C3
	// C3D2
	

	@Test
	void test() {
		List<Piece>pieces=new ArrayList<Piece>();
		List<Joueur> joueurs=new ArrayList<Joueur>();
		Plateau plateau=new Plateau(8, 8, pieces, joueurs);
		Piece rb=new Roi('R', 2, 5, plateau);
		Piece rn=new Roi('r', 0, 7, plateau);
		pieces.add(rb);
		pieces.add(rn);
		plateau.refreshGrille();
		assertEquals(pieces, plateau.getPieces());
		assertEquals(8, plateau.getHauteur());
		assertEquals(8, plateau.getLargeur());
		System.out.println(plateau.getPlateau());
		assertTrue(plateau.deuxRoisVivants());
		assertEquals(rb, plateau.getCase(2, 5));
		//tests plateau ok
		assertTrue(rb.estRoi());
		assertFalse(rb.isDead());
		assertEquals('R',rb.getSymbole());
		assertEquals(5,rb.getColonne());
		assertEquals(2,rb.getLigne());
		assertEquals("blanc",rb.getCouleur());
		assertFalse(((Roi) rb).enEchec(plateau));
		Joueur j1=new Joueur("Martine","blanc");
		Joueur j2=new Joueur("Michel","noir");
		joueurs.add(j1);
		joueurs.add(j2);
		Scanner scanner=new Scanner(System.in);
		Coup coup=new Coup(j1,plateau,scanner);
		System.out.println(coup.getStartCol());
		System.out.println(coup.getStartLi());
		//rentrer F6G6
		boolean coupValide=coup.jouerCoup();
		assertFalse(rn.peutAller(6, 2, plateau));
		assertTrue(coupValide);
		System.out.println(plateau.getPlateau());
		coup=new Coup(j2,plateau,scanner);
		//rentrer H8G7
		coupValide=coup.jouerCoup();
		assertFalse(coupValide);
		coup=new Coup(j2,plateau,scanner);
		//rentrer H8H9
		coupValide=coup.jouerCoup();
		assertFalse(coupValide);
		rb.seFaitManger();
		assertTrue(rb.isDead());
		plateau.checkDeath();
		assertFalse(plateau.deuxRoisVivants());
		Piece fb=new Fou('F', 4, 3, plateau);
		pieces.add(fb);
		plateau.refreshGrille();
		System.out.println(plateau.getPlateau());
		assertEquals('F',fb.getSymbole());
		assertEquals(3,fb.getColonne());
		assertEquals(4,fb.getLigne());
		assertEquals("blanc",fb.getCouleur());
		coup=new Coup(j1,plateau,scanner);
		//rentrer D4B6	
		coupValide=coup.jouerCoup();
		assertTrue(coupValide);
		System.out.println(plateau.getPlateau());
		coup=new Coup(j1,plateau,scanner);
		//rentrer B6B5
		coupValide=coup.jouerCoup();
		assertFalse(coupValide);
		Piece tn=new Tour('t', 7, 2, plateau);
		pieces.add(tn);
		plateau.refreshGrille();
		System.out.println(plateau.getPlateau());
		assertEquals('t',tn.getSymbole());
		assertEquals(2,tn.getColonne());
		assertEquals(7,tn.getLigne());
		assertEquals("noir",tn.getCouleur());
		coup=new Coup(j2,plateau,scanner);
		//rentrer C1C3	
		coupValide=coup.jouerCoup();
		assertTrue(coupValide);
		System.out.println(plateau.getPlateau());
		coup=new Coup(j2,plateau,scanner);
		//rentrer C3D2
		coupValide=coup.jouerCoup();
		assertFalse(coupValide);
	}

}
