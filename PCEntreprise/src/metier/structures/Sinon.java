package metier.structures;

import metier.IExecutable;


public class Sinon implements IExecutable
{
	private Si si;
	
	public Sinon( Si si )
	{
		this.si = si;
	}
	
	@Override
	public void executer()
	{
		IExecutable.code.setLigneExec( this.si.getNumFin()+1 );
	}
	
	public String toString()
	{
		return "sinon " + this.si.getNumDebut();
	}
}
