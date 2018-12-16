package fr.pcentreprise.pcode.metier;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * La classe Code contient le programme et gere si il est valide
 * @author  Equipe 11
 * @version 1.0, 2018-12-17
 */
public class Code
{
	/**
	 * Liste des lignes du programme
	 */
	private List<String> listLigne;
	
	/**
	 * Liste des commandes du programme associées à leur ligne
	 */
	private Map<Integer, IExecutable> listCommandes;
	
	/**
	 * Constructeur qui vérifie si le programme est bien écrit à partir d'une chaine de caractere
	 */
	public Code( String fichier )
	{
		this.listCommandes = new HashMap<Integer, IExecutable>();
		
		this.listLigne = new ArrayList<String>();
		Scanner sc = new Scanner( fichier );
		while( sc.hasNext() )
			this.listLigne.add( sc.nextLine() );
		sc.close();
		/*
		 * faire la fabrique pour vérifier si le programme est valide
		 */
	}
	
	/**
	 * Constructeur qui vérifie si le programme est bien écrit à partir d'un fichier
	 */
	public Code( File fichier )
	{
		//A faire
	}
	
	/**
	 * Constructeur Test
	 * ne pas utiliser pour autre chose que des tests
	 */
	public Code()
	{
		this(
		"ALGORITHME NomAlgo\n" +
		"constante:\n" +
		"\n" +
		"variable:\n" +
		"	x, y : entier\n" +
		"\n" +
		"DEBUT\n" +
		"	x <-- 5\n" +
		"	y <-- 7\n" +
		"\n" +
		"	ecrire ( \"etape 0\" )\n" +
		"\n" +
		"	si x=5 alors\n" +
		"		ecrire ( \"etape 1\" )\n" +
		"\n" +
		"		si y=2 alors\n" +
		"			ecrire ( \"etape 2\" )\n" +
		"		fsi\n" +
		"\n"		 +
		"		ecrire ( \"etape 3\" )\n" +
		"\n"		 +
		"		si y=2 alors\n" +
		"			ecrire ( \"etape 4\" )\n" +
		"		sinon\n" +
		"			ecrire ( \"etape 5\" )\n" +
		"		fsi\n" +
		"\n"		 +
		"	fsi\n" +
		"\n"	 +
		"	si y=7 alors\n" +
		"		ecrire ( \"etape 6\" )\n" +
		"	sinon\n" +
		"		ecrire ( \"etape 7\" )\n" +
		"	fsi\n" +
		"\n"	 +
		"	ecrire ( \"etape 8\" )\n" +
		"\n" +
		"FIN\n");
	}
	
	/**
	 * @return la ligne avec le numero passé en parametre
	 */
	public String getLigne( int num )
	{
		return new String( this.listLigne.get( num-1 ) );
	}
	
	/**
	 * @return la liste des lignes
	 */
	public List<String> getLignes()
	{
		return new ArrayList<String>( this.listLigne );
	}
}
