package echecs.ihm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import echecs.jeu.Coup;
import echecs.jeu.Joueur;
import echecs.jeu.Piece;
import echecs.jeu.Plateau;
import echecs.pieces.Fou;
import echecs.pieces.Roi;
import echecs.pieces.Tour;

public class Main {
	

	public static void main(String[] args) {
		System.out.println("Bienvenue dans cette partie d'échecs !");
		
		List<Piece>pieces=new ArrayList<Piece>();
		List<Joueur> joueurs=new ArrayList<Joueur>();
		Plateau plateau=new Plateau(8, 8, pieces, joueurs);
		Piece rb=new Roi('R', 0, 4, plateau);
		Piece rn=new Roi('r', 7, 3, plateau);
		Piece tb=new Tour('T', 0, 0, plateau);
		Piece tn=new Tour('t', 7, 7, plateau);
		Piece fb1=new Fou('F', 0, 2, plateau);
		Piece fb2=new Fou('F', 0, 5, plateau);
		Piece fn1=new Fou('f', 7, 2, plateau);
		Piece fn2=new Fou('f', 7, 5, plateau);
		pieces.add(rb);
		pieces.add(rn);
		pieces.add(tb);
		pieces.add(tn);
		pieces.add(fb1);
		pieces.add(fb2);
		pieces.add(fn1);
		pieces.add(fn2);
		plateau.refreshGrille();
		System.out.println(plateau.getPlateau());
		Joueur j1=new Joueur("Maxime","blanc");
		Joueur j2=new Joueur("Gontran","noir");
		joueurs.add(j1);
		joueurs.add(j2);
		Scanner scanner=new Scanner(System.in);
		boolean coupOk=false;
		Coup coup;
		
		while(plateau.deuxRoisVivants()) {
			coup=new Coup(joueurs.get(0),plateau,scanner);
			coupOk=coup.jouerCoup();
			if(coupOk) {
				plateau.joueurSuivant();
				System.out.println(plateau.getPlateau());
			}
			else {
				System.out.println("Coup invalide, recommence");
			}
		}
		

	}

}
