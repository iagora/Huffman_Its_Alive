import java.util.ArrayList;

public class Huffman_Tree {
	float[] frequencia;
	ArrayList<Huffman_Tree_Node> allNodes = new ArrayList<Huffman_Tree_Node>();
	
	public Huffman_Tree(float[] freq){
		this.frequencia = freq;
	}
	
	public Huffman_Tree_Node createTree(){
		for (int i = 0; i < frequencia.length; i++)
			if (frequencia[i]> 0)
				allNodes.add(new Huffman_Tree_Node(frequencia[i], i)); //mapeia todos os nós
		
		//TESTE! pra ver se foram eliminados os caracteres q nao aparecem
		//for(int j = 0; j < temp.size(); j++){
		//	Huffman_Tree_Node aux = temp.get(j);
		//	System.out.println(aux.c);
		//}
		//SUCESSO: o arquivo usado no teste continha apenas o alfabeto e temp tinha apenas os nós validos.
		
		//TODO: organizar a lista auxiliar 'temp' pra começar a linkar os nodes
		//estou pensando em usar sublist pra linkar, to pensando na logica.
		while (allNodes.size() > 1) {
			int k = getIndexOfMin(allNodes);
            Huffman_Tree_Node f  = allNodes.remove(k);
            k = getIndexOfMin(allNodes);
            Huffman_Tree_Node f1 = allNodes.remove(k);
            Huffman_Tree_Node pai = new Huffman_Tree_Node(f, f1);
            allNodes.add(pai);
        }
        return allNodes.get(1);
		
	}
	
	public int getIndexOfMin(ArrayList<Huffman_Tree_Node> data) {
	    float min = Float.MAX_VALUE;
	    int index = -1;
	    for (int i = 0; i < data.size(); i++) {
	        Float f = data.get(i).p;
	        if (Float.compare(f.floatValue(), min) < 0) {
	            min = f.floatValue();
	            index = i;
	        }
	    }
	    return index;
	}
}
