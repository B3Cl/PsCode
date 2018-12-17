package fr.pcentreprise.pcode.controleur;

import fr.pcentreprise.pcode.metier.Code;
import fr.pcentreprise.pcode.metier.Console;
import fr.pcentreprise.pcode.metier.Donnees;
import fr.pcentreprise.pcode.metier.Interpreteur;
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
	 * accesseur au code
	 */
	public Code getCode()
	{
		return this.code;
	}
	
	private Controleur()
	{
		this.code = Code.fabriqueCode();
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
		while( instance.code.next() ) {}
		System.out.println( Console.getInstance() );
		System.out.println( instance.code.getDonnees() );
	}
}