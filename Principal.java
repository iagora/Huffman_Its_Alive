import java.io.IOException;

public class Principal {

	public static void main(String[] args) {
		Readbyte ler = new Readbyte();
		long[] frequencia = new long[256];
				
		try {
			
			frequencia = ler.readbytes();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        
	}

}
