import java.io.*;

/*ReadByte on demand para ler arquivos grandes sem estourar memória*/ 
public class Readbyte {
	FileInputStream input = null;
	float[] frequencia = new float[256]; 
	long n_bytes = 0  ;
	public float[] readbytes() throws IOException{
		//comentario teste
		/*Le arquivo byte a byte e calcula fequencia */
		try{
			
		input  = new FileInputStream("teste.txt");
		int b;
		
		while ((b = input.read()) != -1) {
            frequencia[b] += 1;
            n_bytes++;//n_bytes += frequencia[b]; tinha treta aqui, acumulando valores absurdos
        }
   }finally{
	   if (input != null) {
           input.close();
           System.out.println(n_bytes);
       }
   }//fim finally
		/*		
		for(int i =0;i<256;i++){
			System.out.println(frequencia[i]);
		}
		*/
		for(int i = 0;i < 256;i++){
			
			frequencia[i] = frequencia[i]/n_bytes;
			System.out.println(frequencia[i]);
		}
		return frequencia;
  }//fim do readbytes
}//fim da função Readbyte