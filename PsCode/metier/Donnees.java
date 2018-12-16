package fr.pcentreprise.pcode.metier;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * La classe Donnees contient les données du programme
 * @author  Equipe 11
 * @version 1.0, 2018-12-17
 */
public class Donnees
{
	/**
	 * Tableau associatif des variables avec leur valeur
	 */
	private Map<String, Object> listVariables;
	
	/**
	 * Constructeur qui initialise le tableau associatif listVariables
	 */
	public Donnees()
	{
		this.listVariables = new HashMap<String, Object>();
	}
	
	/**
	 * Méthode qui créer une nouvelle variable
	 */
	public void creer( String var, String type )
	{
		if( !this.listVariables.containsKey(var) )
			switch( type )
			{
				case "entier"    : this.listVariables.put( var, new Integer(0)     ); break;
				case "reel"      : this.listVariables.put( var, new Double(0.0)    ); break;
				case "boolean"   : this.listVariables.put( var, new Boolean(false) ); break;
				case "caractere" : this.listVariables.put( var, new Character(' ') ); break;
				case "chaine"    : this.listVariables.put( var, new String("")     ); break;
			}
		else
			System.out.println( "Erreur: la variable existe deja!!" );
	}
	
	/**
	 * Méthode qui affecte une nouvelle valeur à une variable
	 */
	public void affecter( String var, Object valeur )
	{
		if( this.listVariables.containsKey(var) )
			if( valeur.getClass() == this.listVariables.get(var).getClass() )
				this.listVariables.put( var, valeur );
			else
				System.out.println("Erreur de type!!");
		else
			System.out.println("Erreur: la variable n'existe pas!!");
	}
	
	/**
	 * @return la valeur de la variable entrée en parametre
	 */
	public Object get( String var )
	{
		return this.listVariables.get(var);
	}
	
	/**
	 * @return la liste des variables
	 */
	public Set<String> getListVariables()
	{
		return this.listVariables.keySet();
	}
	
	/**
	 * @return une chaine de caractere avec la liste des variables suivi de leur valeur
	 */
	@Override
	public String toString()
	{
		String res = "";
		
		for( String var:this.listVariables.keySet() )
			res += var + " vaut " + this.listVariables.get(var) + "\n";
		
		return res;
	}
}
