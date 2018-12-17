package fr.pcentreprise.pcode.metier.commandes;

import fr.pcentreprise.pcode.metier.Console;
import fr.pcentreprise.pcode.metier.IExecutable;
import fr.pcentreprise.pcode.metier.Interpreteur;

/**
 * La classe Ecriture gere la fonction Ecrire
 * @author Equipe 11
 * @version 1.0, 2018-12-17
 */
public class Ecriture implements IExecutable
{
	/**
	 * Expression à écrire
	 */
	private String expression;
	
	/**
	 * Constructeur
	 * @param expression
	 *    expression à écrire
	 */
	public Ecriture( String expression )
	{
		this.expression = expression;
	}
	
	@Override
	public void executer()
	{
		Console.getInstance().add( (String)Interpreteur.resoudre(this.expression) );
		IExecutable.code.setLigneExec( IExecutable.code.getLigneExec()+1 );
	}
	
	public String toString()
	{
		return "ecrire ( " + this.expression + " )"; 
	}
}
