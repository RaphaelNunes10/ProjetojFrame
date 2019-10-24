package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * CRIAR TABELA:
	 * 
	 * Usuario ----------------------------------------------
	 * id int(11) NOT NULL
	 * PRIMARY KEY AUTO_INCREMENT Nome varchar(50) NOT NULL Email varchar(50) NOT
	 * NULL Posicao varchar(50) NOT NULL Cargo varchar(50) NOT NULL
	 * ----------------------------------------------
	 */

	public static void main(String[] args) {

		try {
			Class.forName(Interface.driver);

			Interface.definirEventos();

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JFrame frame = new Interface();

					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation((tela.width - frame.getSize().width) / 2,
							(tela.height - frame.getSize().height) / 2);
					frame.setVisible(true);

					Botoes.atualizar();
				}
			});
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Driver n�o encontrado");
		}
	}
}
