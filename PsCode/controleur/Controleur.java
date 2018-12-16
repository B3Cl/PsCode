package fr.pcentreprise.pcode.controleur;

import fr.pcentreprise.pcode.metier.Code;
import fr.pcentreprise.pcode.metier.Donnees;
import fr.pcentreprise.pcode.metier.commandes.Affecter;

/**
 * La classe Controleur permet la gestion de l'ensemble du programme
 * @author  Equipe 11
 * @version 1.0, 2018-12-17
 */
public class Controleur
{
	/**
	 * Instance du controleur
	 */
	private static Controleur instance = new Controleur();
	
	/**
	 * Attribut contenant le code
	 */
	private Code code;
	
	/**
	 * Attribut contenant les données
	 */
	private Donnees donnees;
	
	private Controleur()
	{
		this.code = new Code();
		this.donnees = new Donnees();
	}
	
	/**
	 * Methode qui retourne l'instance des donnees
	 */
	public Donnees getDonnees()
	{
		return this.donnees;
	}
	
	/**
	 * Méthode static permettant de récuperer l'instance de l'interpreteur
	 */
	public static Controleur getInstance()
	{
		return Controleur.instance;
	}
	
	public static void main(String[] args)
	{
		instance.donnees.creer("A", "entier");
		instance.donnees.creer("A", "entier");
		instance.donnees.creer("B", "entier");
		System.out.println( instance.donnees );
		System.out.println( "-----------------" );
		
		new Affecter( "A", 5).executer();
		new Affecter( "B", 7).executer();
		
		System.out.println( instance.donnees );
		System.out.println( "-----------------" );
		
		/*for( String s:instance.code.getLignes() )
			System.out.println(s);
		*/
	}
}