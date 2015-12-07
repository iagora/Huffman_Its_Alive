import java.io.IOException;

public class Principal {

	public static void main(String[] args) {
		Readbyte ler = new Readbyte();
		Entropia H = new Entropia();
		
		float[] frequencia = new float[256];
				
		try {
			
			frequencia = ler.readbytes();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}//fim bloco try catch
		H.tabela(frequencia);
		H.entropia(frequencia);
		Huffman_Tree Ht = new Huffman_Tree(frequencia);
		try{
			Ht.comprimir(ler.caminho, frequencia, ler.n_bytes, ler.nomedoarquivo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Ht.expandir("teste.hue");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
