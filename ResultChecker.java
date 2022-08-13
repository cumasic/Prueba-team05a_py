public class ResultChecker {
	
	String[] paths;//array de los textos en la base de datos.
	String text;//texto que se comparara.
	public void getFiles(String text,String[] paths) {
		this.text = text;
		this.paths=paths;
	}
	
	public boolean[] result() {//Devuelve un arreglo de booleanos que es los resultados como tal de todos los textos
		boolean[] bool = new boolean[paths.length];
		for(int i=0;i<this.paths.length;i++) {
			if(PlagioTotal(this.paths[i],text)) {//Primer caso plagio total
				bool[i] = true;
			}
			else if(PlagioParcial(this.paths[i],text)) {//Segundo caso plagio parcial
				bool[i] = true;
			}
			else
				bool[i] = false;
		}
		return bool;
			
	}	
	public boolean PlagioParcial(String text,String textComparate) {//Sus funciones 
		if(compararTextos(text,textComparate)) 
			return true;
		else 
			return false;
	}
	public boolean PlagioTotal(String text,String textComparate) {//De ambos plagios
		if(text.equals(textComparate)) 
			return true;
		else 
			return false;
	}
	private boolean compararTextos(String texto,String textComparate) {//Compara los textos con el texto que ingresemos
		String[] oraciones =obtenerOraciones(textComparate);
		texto = preProcesamiento(texto);
		for(int i=0; i<oraciones.length;i++) {
			oraciones[i] = preProcesamiento(oraciones[i]);
			int resultado = KMPMatch(texto,oraciones[i]);
			if(resultado != -1)
				return true;
		}
		return false;
	}
	public String preProcesamiento(String texto) {//Quita los puntos, comas y parentesis que encuentra y los remplaza con ""
		texto = texto.replaceAll("[.]","");
		texto = texto.replaceAll(",","");
		texto = texto.replaceAll("[(]","");
		texto = texto.replaceAll("[)]","");
		return texto;
	}
	public int KMPMatch(String T, String P) { //Comparación por oracion en un texto
        int[] F = FailureFunction(P);
        int i = 0;
        int j = 0;
        while (i < T.length()) {
            if (T.charAt(i) == P.charAt(j)) {
                if (j == P.length()-1) return i-j;
                else {
                    i++;
                    j++;
                }
            }
            else {
                if (j > 0) j = F[j-1];
                else i++;
            }
        }
        return -1;
    }
    private int[] FailureFunction(String P) {//Metodo necesario para el anterior
        int m = P.length();
        int [] F = new int[m];
        F[0] = 0;
        int i = 1;
        int j = 0;
        while (i < m) {
            if (P.charAt(i) == (P.charAt(j))) {
                F[i] = j+1;
                i++;
                j++;
            }
            else if (j > 0) 
                j = F[j-1];
            else {
                F[i] = 0;
                i++;
            }
        }
        return F;
    }
	private String[] obtenerOraciones(String Texto) {//Obtiene las oraciones del texto que ingresamos
		int NOracion;
		int finOracion;
		String Oracion;
		String Texto1 = Texto;
		String Texto2 = Texto;
		for(NOracion=0;!Texto1.equals("");NOracion++) {//bucle que obtiene el número de oraciones
			finOracion = Texto1.indexOf(".");
			Oracion = Texto1.substring(0,finOracion);
			Texto1 = Texto1.substring(finOracion+1,Texto1.length());
		}
		String[] oraciones = new String[NOracion];
		for(int i=0;!Texto2.equals("");i++) { //bucle que agrega las oracioens a un array
			finOracion = Texto2.indexOf(".");
			oraciones[i] = Texto2.substring(0,finOracion);
			Texto2 = Texto2.substring(finOracion+1,Texto2.length());
		}
		return oraciones;
	}
	public String toString() {//ToString para que podamos ver los resultados de las comparaciones.
		String resul="[ ";
		for(int i=0;i<paths.length;i++) {
			resul += result()[i]+", ";
		}
		resul += " ]"; 
		return resul;
	}
	// Clase que debe poseer la propiedad "result" (obligatorio) 
	// y los métodos que se consideren necesarios
	
}