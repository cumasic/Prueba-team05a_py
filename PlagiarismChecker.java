import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class PlagiarismChecker {
	
	String[] Files = new String[3];
	String ComparateText;
	
	public void loadText(String t) {//Constructor que recibe el texto que se manda
		this.ComparateText = t;
	}
	/*
	 * 
	 * @param paths: Rutas de los archivos que forman la BD.
	 * 
	 * @return : Booelano informando que no hubo errores en la lectura.
	 */
	public boolean loadFiles(String[] paths) {
		// Lectura del archivo (recomendado)
		boolean verif=true;
		String texto;
		for(int i=0;i<paths.length;i++) {
			System.out.println("Abriendo el archivo "+(i+1));
			texto = Lectura(paths[i]);
			if(texto==null) {
				verif=false;
			}
			else {
				// Llenas las estructuras (recomendado)
				Files[i]=texto;
				System.out.println("Abierto con éxito\n");
			}
		}
		return verif;
	}
	
	/*
	 * @param path: Rutas del archivo donde colocaremos el texto con/sin plagio.
	 * 
	 * @return : Resultados del sistema de detección de plagio.
	 */
	public ResultChecker verifyPlagiarism(String path) {
		ResultChecker result = new ResultChecker();
		result.getFiles(ComparateText,Files);
		/*for(int i=0;i<Files.length;i++) {
			CrearArchivo(path,Files[i]);
		}*/ //Este bucle era para pasar los archivos revisados a otra carpeta pero no le vi el caso por eso lo dejo 
		// Retornar resultados del sistema (obligatorio)
		return result;
	}
    /* 
	public void CrearArchivo(String nombre,String texto) {
		try {
			File f =new File(nombre);
			FileWriter w =new FileWriter(f);
			BufferedWriter bw =new BufferedWriter(w);
			PrintWriter wr =new PrintWriter(bw);
			
			wr.append(texto+"\n Texto Revisado");
			
			wr.close();
			bw.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ha sucedido un error"+e);
		}
		
	}
    *///Esta función era para la creación de ese archivo pero ya no le vi el caso.
	public String Lectura(String nombreArchivo) {
		try {
			File archivo = new File(nombreArchivo);
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			
			String linea,texto="";
			while((linea=br.readLine())!=null){
				texto+=linea+" ";
				System.out.println(linea);
			}
			br.close();
			fr.close();
			return texto;
			
		} catch (Exception e) {
			System.out.println("Ha sucedido un error al abrir el archivo: "+e);
			return null;
		}
	}
}