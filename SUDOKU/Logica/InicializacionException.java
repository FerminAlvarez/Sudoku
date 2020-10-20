package Logica;
/**
 * Clase InicializacionException.
 * Implementación para el tipo de excepciones InicializacionException, esto significa que la inicialización a paritr de un archivo no pudo ser completada.
 * @author Fermin Alvarez.
 */
public class InicializacionException extends Exception{/**
	* Recibe un mensaje que se mostrará cuando se arroje la excepción.
	* @param msg Mensaje que mostrará al momento de arrojar la exepción.
	*/
	public InicializacionException(String msg) {
		super (msg);
	}
}
