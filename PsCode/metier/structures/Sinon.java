package fr.pcentreprise.pcode.metier.structures;

import fr.pcentreprise.pcode.metier.IExecutable;

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
