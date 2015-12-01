import java.io.*;
import java.util.Arrays;


public class Read {
	File file = new File ("/home/lucas/Dropbox/2.2015/TI/Trabti/os_maias_queiros.txt");
	byte[] bytes = new byte[(int) file.length()];
	byte[] original = new byte[(int) file.length()];
	FileInputStream fileinputstream = null; 
	int[][] f = new int[2][256];
	
	public void leitura(){
		
		//Lê arquivo e armazena os bytes no vetor bytes
		try{
			fileinputstream = new FileInputStream(file);
			fileinputstream.read(bytes);
			fileinputstream.close();
			original = bytes;
											
	    System.out.println("Done");
        }catch(Exception e){
        	e.printStackTrace();
        }
		
    } //fim leitura()
	
  public void frequencia(){
	  
	  int teste,j = 0;
	  teste = bytes[0];
	  Arrays.sort(bytes);
	  System.out.println(bytes[0] + " " + bytes[bytes.length -1] );
	  
	  //Normaliza os valores de bytes, que em java vao -128 até 127
	  //e calcula acumula na posição do vetor para calcular a frequencia de cada um
		for(int i =0;i < bytes.length ; i++){
			f[1][bytes[i] + 128] += 1;
			
		}
		
		for(int i =0;i < 256 ; i++){
			
		}
		
	}//fim frequencia() Alteração para testar o github
	
	
   
}//fim da classe
