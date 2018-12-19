package metier.structures;

import metier.IExecutable;


public class FinStructure implements IExecutable
{
	private Structure struc;
	
	public FinStructure( Structure struc )
	{
		this.struc = struc;
	}
	
	@Override
	public void executer()
	{
		if( this.struc instanceof Si      ) IExecutable.code.setLigneExec( ((Si)      this.struc).getNumFin() + 1 );
		if( this.struc instanceof Tantque ) IExecutable.code.setLigneExec( ((Tantque) this.struc).getNumDebut()   );
	}
	
	public String toString()
	{
		return "fin " + this.struc.getNumDebut();
	}
}
