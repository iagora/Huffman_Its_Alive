import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Huffman_Tree {
	ArrayList<Huffman_Tree_Node> allNodes = new ArrayList<Huffman_Tree_Node>();
	Readbyte ler = new Readbyte();
	Huffman_Tree_Node tree = null;
	private ObjectOutputStream tab;
	
	public Huffman_Tree(float[] freq){
		tree = this.createTree(freq);	//já monta a árvore no construtor msm
	}
	
	private Huffman_Tree_Node createTree(float[] frequencia){ //monta a árvore, entregando a raiz, funciona em um estilo recursivo
		for (int i = 0; i < frequencia.length; i++)
			if (frequencia[i]> 0)
				allNodes.add(new Huffman_Tree_Node(frequencia[i], i)); //mapeia todos as folhas
		
		while (allNodes.size() > 1) {
			int k = getIndexOfMin(allNodes);
            Huffman_Tree_Node f  = allNodes.remove(k);
            k = getIndexOfMin(allNodes);
            Huffman_Tree_Node f1 = allNodes.remove(k);
            Huffman_Tree_Node pai = new Huffman_Tree_Node(f, f1); //constroi nós a partir de nós e/ou folhas
            allNodes.add(pai);
        }
        return allNodes.get(0); //retorna a raiz, que nesse estágio contém todos os nós e folhas
		
	}
	
	private int getIndexOfMin(ArrayList<Huffman_Tree_Node> data) { //método auxiliar utilizado pra achar o índice dos nós com menores probabilidades
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
			//verifica se é folha, ou seja se não tem filhos
	        return (filho == null) && (filho1 == null);
	    }
	}

		
	public void comprimir(String caminho, float[] freq, long tam, String nome) throws Exception {
        FileInputStream s = new FileInputStream(caminho);
        Vector<Integer> input = new Vector<Integer>();
        int c;
        // lê a arquivo byte a byte e coloca no Vector
        try{
        	while((c = s.read()) != -1) {
        		input.add(c);
        	}
        } finally{
        	s.close();
        }

        // acha a frequencia de cada byte
        //float[] freq = new float[256];
        //freq = ler.readbytes();
        //long tam = ler.n_bytes;
        
        
        Huffman_Tree root = new Huffman_Tree(freq); //cria a árvore

        // constrói uma tabela de conversão direta
        String[] tabela = new String[256];
        buildCode(tabela, root.tree, "");
        FileOutputStream out = new FileOutputStream(caminho.substring(0,caminho.indexOf('.'))+".hue");
        tab = new ObjectOutputStream(out);
        tab.writeObject(freq);
        tab.writeObject(tam); //manda o tamanho para tratar o zero-padding do ultimo byte na descompressao
        tab.writeObject(nome);
        
        // codificando a saida
        int k =0;
        String b = "";
        byte a;
        boolean flag = false;
        for (int i = 0; i < input.size(); i++) {
            String code = tabela[(int)input.get(i)];
	            for (int j = 0; j < code.length(); j++) {
	            	flag=false;
	            	k++;
	                if (code.charAt(j) == '0') {
	                    b = b + '0';
	                    //System.out.print('0'); //TESTE: printa a sequencia obtida na compressão no console
	                }
	                else if (code.charAt(j) == '1') {
	                    b = b + '1';
	                    //System.out.print('1');//TESTE: printa a sequencia obtida na compressão no console
	                }
	                else throw new IllegalStateException("Illegal state");
	                
	                if(k % 8 == 0){
	                	a = (byte) Integer.parseInt(b,2);
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
        	
        	a = (byte) Integer.parseInt(b,2);
        	out.write((int)a);
        }
        //System.out.print('\n'); //TESTE: quebra a linha pra separar a sequencia obtida na compressao da sequencia da expansao
        tab.close();
        out.close();
    }
	
	public void expandir(String local) throws Exception {
		
		float[] freq = new float[256];
		
		FileInputStream in = new FileInputStream(local);
		ObjectInputStream tab = new ObjectInputStream(in);
		freq =(float[])tab.readObject();
		long tam = (long)tab.readObject();
		String nome =(String)tab.readObject();
		
		FileOutputStream out = new FileOutputStream(nome);
		
        Huffman_Tree root = new Huffman_Tree(freq); //cria a árvore
        
      //DECODING: decodifica instantaneamente por meio de um buffer
        Huffman_Tree_Node x = root.tree;
        int b;
        int i = 0;
        char c;
        Vector<Character> buffer = new Vector<Character>(); 
        while((b = in.read()) != -1 & i<tam) {
        	/*
        	 * Decodificação funcionando*;
        	 * *TODO:tratar o padding do último byte;
        	 */
            String s = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            //System.out.print(s);
            
            for(int j = 0; j<s.length();j++){
            	buffer.add(s.charAt(j));
            }
            while (buffer.size() != 0){
	            while (!x.isLeaf()){
	            	c = buffer.remove(0);
	            	if (c == '0')
	            		x = x.filho;
	            	else
	            		x = x.filho1;
	            	if (buffer.size()==0)
	            		break;
	            }
	            if(x.isLeaf()){
	            	i++;
	            	out.write(x.c);
	            	x = root.tree;
	            	if (i==tam){
	            		out.close();
	                    in.close();
	                    tab.close();
	            		return;
	            	}
	            }
            }
            
        }
        //DECODING:fim
        
        out.close();
        in.close();
        tab.close();
	}
	

	//método auxiliar responsável por criar a tabela de tradução por meio de recursividade
	private void buildCode(String[] tabela, Huffman_Tree_Node notRoot, String s) { 
        if (!notRoot.isLeaf()) {
            buildCode(tabela, notRoot.filho,  s + '0');
            buildCode(tabela, notRoot.filho1, s + '1');
        }
        else {
            tabela[notRoot.c] = s;
        }
    }

}
