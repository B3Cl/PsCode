package metier.structures;

import controleur.Controleur;
import metier.IExecutable;

public class Tantque extends Structure
{
	
	public Tantque( int numDebut, String condition )
	{
		super(numDebut, condition);
	}

	@Override
	public void executer()
	{
		if( (boolean) Controleur.getInstance().getCode().getDonnees().resoudre( this.condition ) )
			IExecutable.code.setLigneExec( this.numDebut+1 );
		else
			IExecutable.code.setLigneExec( this.numFin+1 );
	}
	
}
