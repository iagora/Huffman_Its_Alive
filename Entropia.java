
public class Entropia {
	 String[][] hex_tab = new String [2][256];
	 
	 //
	public String[][] tabela(long [] frequencia){
		for(int i = 0;i <2;i++){
			for(int j = 0;j < 256;j++){
				
				//Converte tipo inteiro para tipo String, usando base 16 toHexString e base 10
				hex_tab[0][j] = Long.toHexString(j);
				hex_tab[1][j] = Long.toString(frequencia[j], 10);
				
			}
		}//fim laço for
		return hex_tab;
	}//fim funcao tabela(int[] frequencia)
	
	/*Calcula entropia, medida de informacao de Shannon da fonte utilizando vetor frequencia*/
	public long entropia(long[] frequencia){
		long H=0,acumulador;
		for(int i = 0; i < 256;i++){
		
	  //Tratamento de caso limite onde log10 0 é indeterminado
		if(frequencia[i] == 0){
			
		}else{
			H +=  (long) (frequencia[i]*(Math.log10(frequencia[i]))/(Math.log10(2)));
		}
		
		
	  }//fim for
		return H;
	}//fim entropia
}//fim de classe 
