import java.util.ArrayList;

public class Huffman_Tree {
	ArrayList<Huffman_Tree_Node> allNodes = new ArrayList<Huffman_Tree_Node>();
	
	public Huffman_Tree(float[] freq){
		this.createTree(freq);
	}
	
	private Huffman_Tree_Node createTree(float[] frequencia){
		for (int i = 0; i < frequencia.length; i++)
			if (frequencia[i]> 0)
				allNodes.add(new Huffman_Tree_Node(frequencia[i], i)); //mapeia todos os nós
		
		while (allNodes.size() > 1) {
			int k = getIndexOfMin(allNodes);
            Huffman_Tree_Node f  = allNodes.remove(k);
            k = getIndexOfMin(allNodes);
            Huffman_Tree_Node f1 = allNodes.remove(k);
            Huffman_Tree_Node pai = new Huffman_Tree_Node(f, f1);
            allNodes.add(pai);
        }
        return allNodes.get(0);
		
	}
	
	private int getIndexOfMin(ArrayList<Huffman_Tree_Node> data) {
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
	
	private class Huffman_Tree_Node {
		float p; 	//qual a frequencia
		byte c;		//qual o byte
		Huffman_Tree_Node filho = null;
		Huffman_Tree_Node filho1= null;
		
		public Huffman_Tree_Node(float a, int c){ //construtor pra folhas
			this.p = a;
			this.c =(byte) c;
		}
		
		public Huffman_Tree_Node(Huffman_Tree_Node f, Huffman_Tree_Node f1){ //construtor pra nós
			this.p = f.p + f1.p;
			this.filho = f;
			this.filho1 = f1;
		}

		public float compareTo(Huffman_Tree_Node o) {
			return this.p - o.p;
		}
		
		public boolean isLeaf (){
			assert ((filho == null) && (filho1 == null)) || ((filho != null) && (filho1 != null));
	        return (filho == null) && (filho1 == null);
	    }
	}

}
