package metier.structures;

import controleur.Controleur;
import metier.IExecutable;


/**
 * La classe Condition gere les si sinon finsi
 * @author Equipe 11
 * @version 1.0, 2018-12-17
 */
public abstract class Structure implements IExecutable
{
	/**
	 * numero de debut de la ligne de la structure
	 */
	protected int numDebut;
	
	/**
	 * numero de fin de la structure
	 */
	protected int numFin;
	
	/**
	 * La condition d'exécution
	 */
	protected String condition;
	
	/**
	 * <b>Constructeur de structure</b>
	 * @param condition
	 * 	La condition d'exécution
	 */
	public Structure( int numDebut, String condition )
	{
		this.numDebut = numDebut;
		this.condition = condition;
	}
	
	/**
	 * modificateur du numéro de fin
	 */
	public void setNumFin( int value )
	{
		this.numFin = value;
	}
	
	/**
	 * accesseur au numero de fin
	 */
	public int getNumFin()
	{
		return this.numFin;
	}
	
	/**
	 * accesseur au numero de la ligne de la structure
	 */
	public int getNumDebut()
	{
		return this.numDebut;
	}
	
	public boolean estVerifie()
	{
		return (boolean) Controleur.getInstance().getCode().getDonnees().resoudre( this.condition );
	}
}
