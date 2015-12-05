import java.io.*;

/*ReadByte on demand para ler arquivos grandes sem estourar memória*/ 
public class Readbyte {
	FileInputStream input = null;
	int[] frequencia = new int[256]; 
	
	public void readbytes() throws IOException{
		
		
		try{
			
		input  = new FileInputStream("os_maias_queiros.txt");
		int b;
		
		while ((b = input.read()) != -1) {
            frequencia[b] += 1;
        }
   }finally{
	   if (input != null) {
           input.close();
       }
   }//fim finally
  }//fim do readbytes
}//fim da função Readbyte