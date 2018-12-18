package fr.pcentreprise.pcode.metier;

import fr.pcentreprise.pcode.controleur.Controleur;

/**
 * L'interface IExecutable rend une classe executable par l'interpreteur
 * @author  Equipe 11
 * @version 1.0, 2018-12-17
 */
public interface IExecutable
{
	public Code code = Controleur.getInstance().getCode();
	public void executer();
}
