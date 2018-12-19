package metier;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Console gere la sortie du programme
 * @author Equipe 11
 * @version 1.0, 2018-12-17
 */
public class TraceExec
{
	/**
	 * Instance de la console
	 */
	private static TraceExec instance = new TraceExec();
	
	/**
	 * Liste des sorties du programme
	 */
	private List<String> listSortie;
	
	/**
	 * Constructeur
	 */
	private TraceExec()
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
	public static TraceExec getInstance()
	{
		return TraceExec.instance;
	}
	
	public String toString()
	{
		String res = "";
		
		for( String s:this.listSortie )
			res += s + "\n";
		
		return res;
	}
}
