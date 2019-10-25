package java_skyline;

import java.io.*;

public class Entrada {
    private FileReader fr = null;
    private BufferedReader br = null;
	
	public Entrada ( String filename ) throws FileNotFoundException {
		File archivo = new File (filename);
		this.fr = new FileReader (archivo);
		this.br = new BufferedReader(this.fr);
	}
	
	public String leeLinea() throws IOException { 
		String linea= null;
		try {
			linea=this.br.readLine();
		} catch (IOException e) {
			throw (e);
		}
		return linea;			
	}

	public void cierra () {
		try{                    
			if( null != this.fr ){   
				this.fr.close();
			}
         } catch (Exception e){ 
        	 e.printStackTrace();
         }
	}	

}
