package fr.pcentreprise.pcode.metier.commandes;

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
	protected Object valeur;
	
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
	
	protected Affecter( String var )
	{
		this.var = var;
	}
	
	@Override
	public void executer()
	{
		IExecutable.code.getDonnees().affecter(this.var, this.valeur);
		IExecutable.code.setLigneExec( IExecutable.code.getLigneExec()+1 );
	}
	
	public String toString()
	{
		return var + " <-- " + valeur;
	}


	public int getNextLigne()
	{
		return  code.getLigneExec()+1 ;
	}
}
