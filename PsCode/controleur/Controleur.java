package fr.pcentreprise.pcode.controleur;

import fr.pcentreprise.pcode.metier.Code;
import fr.pcentreprise.pcode.metier.TraceExec;
import fr.pcentreprise.pcode.vue.Console;

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
	private static Controleur instance;
	
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
	
	private Controleur(String fichierAlgo)
	{
		this.code = new Code(fichierAlgo);
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
		Controleur.instance = new Controleur(args[0]);
		
		while( instance.code.next() ) {}
		System.out.println(Console.afficher());
		
	}
}
