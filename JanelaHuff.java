import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.*;



public class JanelaHuff {

	private JFrame frame;
	float[] frequencia  = null; 
	String caminho;
	long tamanho;
	String nome;
	String[][] tabelaHex ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaHuff window = new JanelaHuff();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cria a aplicação 
	 */
	public JanelaHuff() {
		initialize();
	}

	/**
	 * Inicialização dos componentes do Frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(800, 600 );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 784, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmFechar = new JMenuItem("Fechar");
		mntmFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		mnNewMenu.add(mntmFechar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 61, 350, 402);
		frame.getContentPane().add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(424, 61, 350, 402);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane.setViewportView(textArea_1);
		
		
		JButton btnNewButton = new JButton("Selecionar Arquivo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Readbyte read = new Readbyte();
				
				try {
				frequencia =	read.readbytes();
				caminho = read.caminho;
				tamanho = read.n_bytes;
				nome = read.nomedoarquivo;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
				
				
			  
			
		});
		btnNewButton.setBounds(126, 498, 151, 34);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnComprimir = new JButton("Comprimir");
		btnComprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				if(tamanho == 0){
					textArea.setText("Nenhum arquivo selecionado :(");
				}else{
					Huffman_Tree huffman = new Huffman_Tree(frequencia);
					Entropia entropia = new Entropia();
				try {
					
					tabelaHex = entropia.tabela(frequencia);
					textArea.setText(" A entropia do arquivo é: " +Float.toString(entropia.entropia(frequencia)));
					textArea_1.append("Hex\tFrequencia\n");
					for(int i = 0;i < 256;i++){
							
							textArea_1.append(tabelaHex[0][i]+ "\t"+ tabelaHex[1][i]+ "\n");
							
						
					}//fim laço for
					
					float comp = huffman.comprimir(caminho,frequencia, tamanho, nome);
					textArea.append("\nComprimento médio: " + comp);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		btnComprimir.setBounds(350, 498, 111, 34);
		frame.getContentPane().add(btnComprimir);
		
		
		
		JButton btnExpandir = new JButton("Expandir");
		btnExpandir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Huffman_Tree huffman = new Huffman_Tree();
				if(!caminho.endsWith(".hue")){
					textArea.setText("É necessário um arquivo .hue para expansão\n");
				} else {
					try{
						huffman.expandir(caminho);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btnExpandir.setBounds(546, 498, 111, 34);
		frame.getContentPane().add(btnExpandir);
		
		
		
		
		
		
	}
}
