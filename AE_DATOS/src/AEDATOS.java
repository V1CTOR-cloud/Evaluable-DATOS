import java.io.File;
import java.util.Scanner;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class AEDATOS {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean salir = true;
		int num;
		String file = args[0];
		System.out.println("** Benvinguts **");
		
		do {
			System.out.print("Tria una opcio:		");
			System.out.println("\n--------------");
			System.out.println("1. getInformacio");
			System.out.println("2. CreaCarpeta");
			System.out.println("3. CreaFitxer");
			System.out.println("4. Elimina");
			System.out.println("5. Renomena");		
			num = sc.nextInt();
			File f = new File(file);
			switch(num) {
				case 1:
					getInformacio(f);
					break;
					
				case 2:
					creaCarpeta(f);
					break;
					
				case 3:
					creaFichero(f);
					break;
					
				case 4: 
					elimina(f);
					break;
					
				case 5:
					renombra(f);
					break;
			}			
		}while(salir == false);
		
		
	}	
	
	public static void getInformacio(File f) {
		System.out.println("Nom del fitxer:		" + f.getName());		
		System.out.println("-----------------------------");
		if(f.exists()) { 
			String[] lista = f.list();
			if(f.isDirectory()) {
				System.out.println("Es un directori");
				System.out.println("---------------------");
				
				for(String elemento : lista) {
					System.out.println("---------------------");
					System.out.println(elemento);
					System.out.println("---------------------");
				}
				System.out.println("Espai disponible:		" + f.getUsableSpace() / 1024 / 1024 / 1024 + "GB");
				System.out.println("Espai lliure:		" + f.getFreeSpace() / 1024 / 1024 / 1024 + "GB");
				System.out.println("Espai total:		" + f.getTotalSpace()/ 1024 / 1024 / 1024 + "GB");
				
			}else {
				System.out.println("Es un fitxer");
				System.out.println("---------------------");
				System.out.println("Grandaria:			" + f.length() + "Bytes");
			}
			System.out.println("---------------------");
			System.out.println("Ruta completa:		" + f.getAbsolutePath());
			System.out.println("Ultima modificacio:	" + new Date(f.lastModified()));
				
				if(f.isHidden()) {
					System.out.println("El archiu está ocult");
				}else {
					System.out.println("El archiu no está ocult");
				}
		}else {
			System.out.println("El archiu no existeix");
		}		
		System.out.flush();
	}
	
	public static void creaCarpeta(File f) {
		String directorio;
		String ruta2;
		System.out.flush();
		System.out.println("-------------------");
		System.out.println("Nom del directori? ");
		System.out.println("-------------------");
		directorio = sc.next();
		ruta2 = f + "/" + directorio.toString();
		File ruta = new File(ruta2);
		
		if(!ruta.exists()) {
			if(ruta.mkdirs()) {
				System.out.flush();
				System.out.println("------------------------------------");
				System.out.println("El directori ha sigut creat amb exit");
				System.out.println("------------------------------------");
			}else {
				System.out.flush();
				System.out.println("------------------------");
				System.out.println("Error al crear directori");
				System.out.println("------------------------");
			}
		}		
	}
	
	public static void creaFichero(File f) {
		try {
			String fichero;
			String file2;
			String contenido = "------------------------------------\nEl fitxer ha sigut creat amb exit\n------------------------------------";
			System.out.flush();
			System.out.println("-------------------");
			System.out.println("Nom del fitxer? ");
			System.out.println("-------------------");
			fichero = sc.next();
			file2 = f + "/" + fichero + ".txt";
			File file = new File(file2);
			
			if(!file.exists()) {				
				file.createNewFile();
				System.out.flush();
				System.out.println("------------------------------------");
				System.out.println("El fitxer ha sigut creat amb exit");
				System.out.println("------------------------------------");
				FileWriter fw = new FileWriter(file);
	            BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(contenido);
	            bw.close();
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void elimina(File f) {
		String directorio;
		String ruta2;
		System.out.flush();
		System.out.println("--------------------------");
		System.out.println("Nom del directori/fitxer? ");
		System.out.println("--------------------------");
		directorio = sc.next();
		ruta2 = f + "/" + directorio.toString();
		File ruta = new File(ruta2);
		
		if(!ruta.exists()) {			
			
		}else {
			if (ruta.delete()) {
				System.out.println("---------------------------");
				System.out.println("El fitxer ha sigut eliminat");
				System.out.println("---------------------------");
			}else {
				System.out.println("-----------------------------");
				System.out.println("El fitxer no pot ser eliminat");
				System.out.println("-----------------------------");
			}
		}
	}
	
	public static void renombra(File f) {
		String f1;
		String f2;
		String ruta2;
		System.out.flush();
		System.out.println("--------------------------");
		System.out.println("Nom del directori/fitxer a renomenar? ");
		System.out.println("--------------------------");
		f1 = sc.next();
		System.out.flush();
		System.out.println("---------");
		System.out.println("Nou nom:	 ");
		System.out.println("---------");
		f2 = f + "/" + sc.next();
		File f2r = new File(f2);
		if(f2r.isDirectory()) {
			ruta2 = f + "/" + f1;
			File ruta = new File(ruta2);
			
			if(ruta.renameTo(f2r)) {
				System.out.println("-----------------------------");
				System.out.println("Se ha renomenat exitosament");
				System.out.println("-----------------------------");
			}else {
				System.out.println("-----------------------------");
				System.out.println("No se ha pogut renomenar");
				System.out.println("-----------------------------");
			}
		}else {
			ruta2 = f + "/" + f1;
			File ruta = new File(ruta2);
			File f3 = new File(f2 + ".txt");
			if(ruta.renameTo(f3)) {
				System.out.println("-----------------------------");
				System.out.println("Se ha renomenat exitosament");
				System.out.println("-----------------------------");
			}else {
				System.out.println("-----------------------------");
				System.out.println("No se ha pogut renomenar");
				System.out.println("-----------------------------");
			}
		}
	}
}
