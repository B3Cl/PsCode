package metier.commandes;

import metier.IExecutable;


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
	protected String exp;
	
	/**
	 * Constructeur
	 * @param var
	 *     nom de la variable à modifier
	 * @param valeur
	 *     valeur a affecter
	 */
	public Affecter( String var, String exp )
	{
		this.var    = var;
		this.exp    = exp;
	}
	
	protected Affecter( String var )
	{
		this.var = var;
	}
	
	@Override
	public void executer()
	{
		IExecutable.code.getDonnees().resoudre( var + "=" + exp );
		IExecutable.code.setLigneExec( IExecutable.code.getLigneExec()+1 );
	}
	
	public String toString()
	{
		return var + " <-- " + exp;
	}


	public int getNextLigne()
	{
		return  code.getLigneExec()+1 ;
	}
}
