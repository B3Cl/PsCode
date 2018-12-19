package metier.commandes;

import controleur.Controleur;
import metier.IExecutable;
import metier.TraceExec;


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
		TraceExec.getInstance().add( "" + Controleur.getInstance().getCode().getDonnees().resoudre( this.expression ) );
		IExecutable.code.setLigneExec( IExecutable.code.getLigneExec()+1 );
	}
	
	public String toString()
	{
		return "ecrire ( " + this.expression + " )"; 
	}
}
