package fr.pcentreprise.pcode.metier;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Console gere la sortie du programme
 * @author Equipe 11
 * @version 1.0, 2018-12-17
 */
public class Console
{
	/**
	 * Instance de la console
	 */
	private static Console instance = new Console();
	
	/**
	 * Liste des sorties du programme
	 */
	private List<String> listSortie;
	
	/**
	 * Constructeur
	 */
	private Console()
	{
		this.listSortie = new ArrayList<String>();
	}
	
	/**
	 * @return la liste des sorties
	 */
	public List<String> getSortie()
	{
		return this.listSortie;
	}
	
	/**
	 * Ajoute une ligne à la console
	 */
	public void add( String expression )
	{
		this.listSortie.add( expression );
	}
	
	/**
	 * @return l'instance de la console
	 */
	public static Console getInstance()
	{
		return Console.instance;
	}
	
	public String toString()
	{
		String res = "";
		
		for( String s:this.listSortie )
			res += s + "\n";
		
		return res;
	}
}
