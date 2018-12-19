package metier.structures;

import controleur.Controleur;
import metier.IExecutable;


public class Si extends Structure {
	
	/**
	 * numéro de la ligne sinon ( vaut 0 si il n'y a pas de sinon )
	 */
	private int numSinon;
	
	public Si( int numDebut, String condition)
	{
		super(numDebut, condition.replaceAll("=", "=="));
		this.numSinon = 0;
	}

	/**
	 * met a jour le numero de ligne si la condition est valide
	 */
	public void setNumSinon( int value )
	{
		this.numSinon = value;
	}
	
	public String toString()
	{
		return "si " + condition + " alors";
	}
	
	@Override
	public void executer()
	{
		if( (boolean) Controleur.getInstance().getCode().getDonnees().resoudre( this.condition ) )
			IExecutable.code.setLigneExec( this.numDebut+1 );
		else
			if( this.numSinon != 0 )
				IExecutable.code.setLigneExec( this.numSinon+1 );
			else
				IExecutable.code.setLigneExec( this.numFin+1 );
		
	}
}
