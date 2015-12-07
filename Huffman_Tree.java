import java.util.ArrayList;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Huffman_Tree {
	ArrayList<Huffman_Tree_Node> allNodes = new ArrayList<Huffman_Tree_Node>();
	Readbyte ler = new Readbyte();
	Huffman_Tree_Node tree = null;
	private ObjectOutputStream tab;
	
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
		int c;		//qual o byte
		Huffman_Tree_Node filho = null;
		Huffman_Tree_Node filho1= null;
		
		public Huffman_Tree_Node(float a, int c){ //construtor pra folhas
			this.p = a;
			this.c = c;
		}
		
		public Huffman_Tree_Node(Huffman_Tree_Node f, Huffman_Tree_Node f1){ //construtor pra nós
			this.p = f.p + f1.p;
			this.filho = f;
			this.filho1 = f1;
		}
		
		public boolean isLeaf (){
			//assert ((filho == null) && (filho1 == null)) || ((filho != null) && (filho1 != null));
	        return (filho == null) && (filho1 == null);
	    }
	}

		
	public void compress() throws Exception {
        // read the input
        FileInputStream s = new FileInputStream("teste.txt");
        Vector<Integer> input = new Vector<Integer>();
        int c;
        try{
        	while((c = s.read()) != -1) {
        		input.add(c);
        	}
        } finally{
        	s.close();
        }
        //char[] input = s.toCharArray();

        // tabulate frequency counts
        float[] freq = new float[256];
        freq = ler.readbytes();

        Huffman_Tree root = new Huffman_Tree(freq); //cria a árvore

        // build code table
        String[] st = new String[256];
        buildCode(st, root.tree, "");
        
        FileOutputStream out = new FileOutputStream("teste_comprimido.hue");
        tab = new ObjectOutputStream(out);
        tab.writeObject(freq);
        
        // codificando a saida
        int k =0;
        String b = "";
        byte a;
        boolean flag = false;
        for (int i = 0; i < input.size(); i++) {
            String code = st[(int)input.get(i)];
	            for (int j = 0; j < code.length(); j++) {
	            	flag=false;
	            	k++;
	                if (code.charAt(j) == '0') {
	                    b = b + '0';
	                    System.out.print('0'); //tirar
	                }
	                else if (code.charAt(j) == '1') {
	                    b = b + '1';
	                    System.out.print('1');//tirar
	                }
	                else throw new IllegalStateException("Illegal state");
	                
	                if(k % 8 == 0){
	                	a = strToByteArray(b);
	    	        	out.write(a);
	    	        	b = "";
	    	        	flag = true;
	    	        }
	            }
        }
        if(!flag){
        	while (b.length() < 8){
        		b = b + '0';
        	}
        	a = strToByteArray(b);
        	out.write((int)a);
        }
        
        tab.close();
        out.close();
    }
	
	public void expand() throws Exception {
		
		float[] freq = new float[256];
		FileOutputStream out = new FileOutputStream("teste_descomprimido.txt");
		FileInputStream in = new FileInputStream("teste_comprimido.hue");
		ObjectInputStream tab = new ObjectInputStream(in);
		freq =(float[])tab.readObject();
		
        Huffman_Tree root = new Huffman_Tree(freq); //cria a árvore
        
        Huffman_Tree_Node x = root.tree;
        int b;
        System.out.print('\n');//tirar
        while((b = in.read()) != -1) {
        	
            String s = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            System.out.print(s);
            
            for (int i = 0; i<8; i++) {
            	if (x.isLeaf()){
            		out.write(x.c);
            		x = root.tree;
            		break;
            	}
                char bit = s.charAt(i);
                if (bit==0) x = x.filho;
                else     x = x.filho1;
                
            }
        }
        
        
        //TODO:usar o código para expandir
        out.close();
        in.close();
        tab.close();
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
	
	private byte strToByteArray(String s) {
        byte[] bval;
        try {
            bval = new BigInteger(s, 2).toByteArray();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            bval = new BigInteger("00000000", 2).toByteArray();
        }
        return bval[0];
        
    }

}
