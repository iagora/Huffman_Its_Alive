import java.io.*;

import javax.swing.JFileChooser;

/*
 * Iago Passos da Silva					13/0028151
 * Lucas Rodrigues de Freitas Ribeiro 	11/0129377
 */

/*ReadByte on demand para ler arquivos grandes sem estourar memória*/ 
public class Readbyte {
	FileInputStream input = null;
	float[] frequencia = new float[256]; 
	long n_bytes = 0  ;
	String caminho,nomedoarquivo, filePath ;
	public float[] readbytes() throws IOException{
		//comentario teste
		/*Le arquivo byte a byte e calcula fequencia */
		try{
			try {
			     JFileChooser chooser = new JFileChooser();
			     int retorno = chooser.showOpenDialog(null);
			 
			     if (retorno == JFileChooser.APPROVE_OPTION) {
			    	 caminho = chooser.getSelectedFile().getAbsolutePath();
			    	 filePath = caminho. substring(0,caminho.lastIndexOf(File.separator));
			    	 nomedoarquivo = chooser.getSelectedFile().getName();
			    	 System.out.println(nomedoarquivo);
			        input = new FileInputStream(chooser.getSelectedFile());
			        
			     }
			   } catch (FileNotFoundException es) {
			     es.printStackTrace();
			   }
			
		
		int b;
		
		while ((b = input.read()) != -1) {
            frequencia[b] += 1;
            n_bytes++;//n_bytes += frequencia[b]; 
        }
   }finally{
	   if (input != null) {
           input.close();
           //System.out.println(n_bytes);
       }
   }
		for(int i = 0;i < 256;i++){
			
			frequencia[i] = frequencia[i]/n_bytes;
			
		}
		
		return frequencia;
  }//fim do readbytes
}//fim da função Readbyte