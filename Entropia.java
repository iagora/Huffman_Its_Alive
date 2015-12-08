/*
 * Iago Passos da Silva					13/0028151
 * Lucas Rodrigues de Freitas Ribeiro 	11/0129377
 */


public class Entropia {
	 String[][] hex_tab = new String [2][256];
	 
	 //
	public String[][] tabela(float [] frequencia){
		for(int i = 0;i <2;i++){
			for(int j = 0;j < 256;j++){
				
				//Converte tipo inteiro para tipo String, usando base 16 toHexString 
				hex_tab[0][j] = Long.toHexString(j);
				hex_tab[1][j] = Float.toString(frequencia[j]);
				//System.out.println(hex_tab[0][j]);
			}
		}//fim la�o for
		return hex_tab;
	}//fim funcao tabela(int[] frequencia)
	
	/*Calcula entropia, medida de informacao de Shannon da fonte utilizando vetor frequencia*/
	public float entropia(float[] frequencia){
		float H=0;
		for(int i = 0; i < 256;i++){
		
	  //Tratamento de caso limite onde log10 0 � indeterminado
		if(frequencia[i] == 0){
			
		}else{
			H +=  frequencia[i]*(Math.log10(1/frequencia[i]))/(Math.log10(2));
		}
		
		
	  }//fim for
		System.out.println("Entropia do arquivo: "+H);
		return H;
	}//fim entropia
}//fim de classe 
