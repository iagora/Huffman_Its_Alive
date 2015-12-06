import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Huffman_Tree {
	ArrayList<Huffman_Tree_Node> allNodes = new ArrayList<Huffman_Tree_Node>();
	Readbyte ler = new Readbyte();
	Huffman_Tree_Node tree = null;
	
	public Huffman_Tree(float[] freq){
		tree = this.createTree(freq);
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

		
	public void compress() {
        // read the input
        FileInputStream s = new FileInputStream("teste.txt");
        Vector input = new Vector();
        int c;
        do {
        	c = s.read();
        	input.add((char)s.read());
        } while(c != -1);
        //char[] input = s.toCharArray();

        // tabulate frequency counts
        float[] freq = new float[256];
        freq = ler.readbytes();

        // build Huffman trie
        Huffman_Tree root = new Huffman_Tree(freq);

        // build code table
        String[] st = new String[256];
        buildCode(st, root.tree, "");

        // print trie for decoder
        writeTrie(root);

        // print number of bytes in original uncompressed message
        //BinaryStdOut.write(input.length);

        // use Huffman code to encode input
        for (int i = 0; i < input.size(); i++) {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    BinaryStdOut.write(false);
                }
                else if (code.charAt(j) == '1') {
                    BinaryStdOut.write(true);
                }
                else throw new IllegalStateException("Illegal state");
            }
        }
    }
	
	private void buildCode(String[] st, Huffman_Tree_Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(st, x.filho,  s + '0');
            buildCode(st, x.filho1, s + '1');
        }
        else {
            st[x.c] = s;
        }
    }


}
