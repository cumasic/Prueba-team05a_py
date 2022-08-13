import java.util.*;
public class Test {
    public static void main(String[] args){
		Scanner sc = new Scanner (System.in);
		String[] paths = {"./Textos/Texto1.txt","./Textos/Texto2.txt","./Textos/Texto3.txt"};//Base de datos limitada con 3 archivos
		PlagiarismChecker n=new PlagiarismChecker();
		System.out.println("Ingrese el parrafo o texto a verificar");
		String texto = sc.nextLine();
		n.loadText(texto);
		if(n.loadFiles(paths)) {
        	System.out.println("Verficicar si hay flagio");
        	System.out.println(n.verifyPlagiarism(""));
        }
        else {
        	System.out.println("Hubo un error con uno de los archivos corrijalo para hacer el chequeo de plagio");
        }
	}
}
