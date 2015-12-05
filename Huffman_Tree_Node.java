
public class Huffman_Tree_Node {
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
}
