import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class JanelaHuff {

	private JFrame frame;
	float[] frequencia = new float[256]; 
	String caminho;
	long tamanho;
	String nome;
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
	 * Create the application.
	 */
	public JanelaHuff() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmFechar = new JMenuItem("Fechar");
		mntmFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnNewMenu.add(mntmFechar);
		
		
		 
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
		btnNewButton.setBounds(31, 238, 151, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnComprimir = new JButton("Comprimir");
		btnComprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Huffman_Tree huffman = new Huffman_Tree(frequencia);
				try {
					huffman.comprimir(caminho,frequencia, tamanho, nome);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnComprimir.setBounds(192, 238, 95, 23);
		frame.getContentPane().add(btnComprimir);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 24, 197, 203);
		frame.getContentPane().add(textPane);
		
		JButton btnExpandir = new JButton("Expandir");
		btnExpandir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Huffman_Tree huffman = new Huffman_Tree();
				if(!caminho.endsWith(".hue")){
					textPane.setText("É necessário um arquivo .hue para expansão\n");
				} else {
					try{
						huffman.expandir(caminho);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btnExpandir.setBounds(297, 238, 102, 23);
		frame.getContentPane().add(btnExpandir);
	}
}
