import java.io.IOException;

public class Principal {

	public static void main(String[] args) {
		Readbyte ler = new Readbyte();
				
		try {
			ler.readbytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
