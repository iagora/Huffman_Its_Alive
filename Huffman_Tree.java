import java.util.ArrayList;

public class Huffman_Tree {
	float[] frequencia;
	ArrayList<Huffman_Tree_Node> allNodes = new ArrayList<Huffman_Tree_Node>();
	
	public Huffman_Tree(float[] freq){
		this.frequencia = freq;
	}
	
	public void createTree(){
		for (int i = 0; i < frequencia.length; i++)
			if (frequencia[i]!= 0)
				allNodes.add(new Huffman_Tree_Node(frequencia[i], i)); //mapeia todos os nós
		ArrayList<Huffman_Tree_Node> temp = allNodes;
		
		//teste pra ver se foram eliminados os caracteres q nao aparecem
		for(int j = 0; j < temp.size(); j++){
			Huffman_Tree_Node aux = temp.get(j);
			System.out.println(aux.c);
		}
		//SUCESSO: o arquivo usado no teste continha apenas o alfabeto e temp tinha apenas os nós validos.
		
		//TODO: organizar a lista auxiliar 'temp' pra começar a linkar os nodes
		//estou pensando em usar sublist pra linkar, to pensando na logica.
	}
}
