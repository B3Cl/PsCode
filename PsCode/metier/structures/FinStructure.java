package fr.pcentreprise.pcode.metier.structures;

import fr.pcentreprise.pcode.metier.IExecutable;

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
		if( this.struc instanceof Si )
			IExecutable.code.setLigneExec( ((Si)this.struc).getNumFin() + 1 );
	}
	
	public String toString()
	{
		return "fin " + this.struc.getNumDebut();
	}
}
