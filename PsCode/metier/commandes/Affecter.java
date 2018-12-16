package fr.pcentreprise.pcode.metier.commandes;

import fr.pcentreprise.pcode.controleur.Controleur;
import fr.pcentreprise.pcode.metier.IExecutable;

/**
 * La classe Affectation gere le symbole <--
 * @author Equipe 11
 * @version 1.0, 2018-12-17
 */
public class Affecter implements IExecutable
{
	/**
	 * nom de la variable à modifier
	 */
	private String var;
	
	/**
	 * valeur à affecter
	 */
	private Object valeur;
	
	/**
	 * Constructeur
	 * @param var
	 *     nom de la variable à modifier
	 * @param valeur
	 *     valeur a affecter
	 */
	public Affecter( String var, Object valeur )
	{
		this.var    = var;
		this.valeur = valeur;
	}
	
	
	@Override
	public void executer()
	{
		Controleur.getInstance().getDonnees().affecter(this.var, this.valeur);
	}

}
