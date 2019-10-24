package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Interface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static String driver = "com.mysql.jdbc.Driver";
	final static String url = "jdbc:mysql://localhost:3311/empregadora"; // alterar conexão e porta de acordo com o
																			// server mySQL sendo utilizado no momento.
	final static String user = "root";
	final static String senha = "root";

	static String posicao = "";
	static String[] cargo = new String[3];

	static JLabel lbId = new JLabel("Id: ");
	static JLabel lbNome = new JLabel("Nome: ");
	static JLabel lbEmail = new JLabel("E-mail: ");

	static JTextField tfId = new JTextField(11);
	static JTextField tfNome = new JTextField(50);
	static JTextField tfEmail = new JTextField(50);

	static JLabel lbPosicao = new JLabel("Posição: ");

	static JRadioButton rbGerente = new JRadioButton();
	static JRadioButton rbSupervisor = new JRadioButton();
	static JRadioButton rbEstagiario = new JRadioButton();

	static JLabel lbGerente = new JLabel("Gerente");
	static JLabel lbSupervisor = new JLabel("Supervisor");
	static JLabel lbEstagiario = new JLabel("Estagiário");

	static JLabel lbCargo = new JLabel("Cargo: ");

	static JCheckBox cbMedicina = new JCheckBox();
	static JCheckBox cbComercio = new JCheckBox();
	static JCheckBox cbEngenharia = new JCheckBox();

	static JLabel lbMedicina = new JLabel("Medicina");
	static JLabel lbComercio = new JLabel("Comércio");
	static JLabel lbEngenharia = new JLabel("Engenharia");

	static JButton btInserir = new JButton("Inserir");
	static JButton btBuscar = new JButton("Buscar");
	static JButton btAlterar = new JButton("Alterar");
	static JButton btExcluir = new JButton("Excluir");
	static JButton btSair = new JButton("Sair");

	static Object[][] dados = { { "", "", "", "", "" } };

	static String[] nomeColunas = { "Id", "Nome", "E-mail", "Posição", "Cargo" };

	static DefaultTableModel model = new DefaultTableModel();

	static JTable tbDados = new JTable(model);

	static JScrollPane painelScroll = new JScrollPane(tbDados);

	public Interface() {
		setTitle("Controle de Clientes");
		setBounds(0, 0, 950, 500);
		setLayout(null);

		JOptionPane.showMessageDialog(null, "Conexão aberta!");

		criarElementos();

		add(lbId);
		add(lbNome);
		add(lbEmail);

		add(tfId);
		add(tfNome);
		add(tfEmail);

		add(lbPosicao);

		rbGerente.isSelected();
		
		add(rbGerente);
		add(rbSupervisor);
		add(rbEstagiario);		

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rbGerente);
		grupo.add(rbSupervisor);
		grupo.add(rbEstagiario);
		
		rbGerente.setSelected(true);

		add(lbGerente);
		add(lbSupervisor);
		add(lbEstagiario);

		add(lbCargo);

		add(cbMedicina);
		add(cbComercio);
		add(cbEngenharia);

		add(lbMedicina);
		add(lbComercio);
		add(lbEngenharia);

		add(btInserir);
		add(btBuscar);
		add(btAlterar);
		add(btExcluir);
		add(btSair);

		model.addColumn("Id");
		model.addColumn("Nome");
		model.addColumn("E-mail");
		model.addColumn("Posição");
		model.addColumn("Cargo");

		add(painelScroll);

	}

	/**
	 * Cria e posiciona os elementos da janela. 
	 */
	public static void criarElementos() {
		lbId.setBounds(10, 10, 100, 25);
		tfId.setBounds(60, 10, 300, 25);
		lbNome.setBounds(10, 40, 100, 25);
		tfNome.setBounds(60, 40, 300, 25);
		lbEmail.setBounds(10, 70, 100, 25);
		tfEmail.setBounds(60, 70, 300, 25);

		lbPosicao.setBounds(375, 10, 100, 25);

		rbGerente.setBounds(375, 27, 20, 20);
		lbGerente.setBounds(400, 27, 100, 25);
		rbSupervisor.setBounds(375, 44, 20, 20);
		lbSupervisor.setBounds(400, 44, 100, 25);
		rbEstagiario.setBounds(375, 61, 20, 20);
		lbEstagiario.setBounds(400, 61, 100, 25);

		lbCargo.setBounds(475, 10, 100, 25);

		cbMedicina.setBounds(475, 27, 20, 20);
		lbMedicina.setBounds(500, 27, 100, 25);
		cbComercio.setBounds(475, 44, 20, 20);
		lbComercio.setBounds(500, 44, 100, 25);
		cbEngenharia.setBounds(475, 61, 20, 20);
		lbEngenharia.setBounds(500, 61, 100, 25);

		btInserir.setBounds(10, 120, 90, 25);
		btBuscar.setBounds(103, 120, 90, 25);
		btAlterar.setBounds(196, 120, 90, 25);
		btExcluir.setBounds(289, 120, 90, 25);
		btSair.setBounds(382, 120, 90, 25);

		tbDados.setBounds(10, 160, 913, 290);
		tbDados.setFillsViewportHeight(true);

		painelScroll.setBounds(10, 160, 913, 290);
	}
	
	/**
	 * Define as ações de cada elemento na tela.
	 */
	public static void definirEventos() {
		rbGerente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicao = "Gerente";
			}
		});

		rbSupervisor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicao = "Supervisor";
			}
		});

		rbEstagiario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicao = "Estagiário";
			}
		});

		cbMedicina.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object selecao = e.getItemSelectable();

				if (selecao == cbMedicina) {
					cargo[0] = "Medicina";
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					cargo[0] = "-";
				}
			}
		});

		cbComercio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object selecao = e.getItemSelectable();

				if (selecao == cbComercio) {
					cargo[1] = "Comércio";
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					cargo[1] = "-";
				}

			}
		});

		cbEngenharia.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object selecao = e.getItemSelectable();

				if (selecao == cbEngenharia) {
					cargo[2] = "Engenharia";
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					cargo[2] = "-";
				}
			}
		});

		btInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Botoes.inserir();
			}
		});

		btBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Botoes.buscar();
			}
		});

		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Botoes.alterar();
			}
		});

		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Botoes.excluir();
			}
		});

		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Botoes.sair();
			}
		});

	}

}
