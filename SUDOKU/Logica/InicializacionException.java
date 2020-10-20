package Logica;
/**
 * Clase InicializacionException.
 * Implementaci�n para el tipo de excepciones InicializacionException, esto significa que la inicializaci�n a paritr de un archivo no pudo ser completada.
 * @author Fermin Alvarez.
 */
public class InicializacionException extends Exception{/**
	* Recibe un mensaje que se mostrar� cuando se arroje la excepci�n.
	* @param msg Mensaje que mostrar� al momento de arrojar la exepci�n.
	*/
	public InicializacionException(String msg) {
		super (msg);
	}
}
