package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Botoes extends Funcoes {

	/**
	 * Atualiza os dados no JTable.
	 */
	public static void atualizar() {
		String sql = "SELECT * FROM Usuario";

		try {
			Connection conex = DriverManager.getConnection(Interface.url, Interface.user, Interface.senha);

			Statement statement = conex.createStatement();
			ResultSet result = statement.executeQuery(sql);

			Interface.model.setRowCount(0);

			while (result.next()) {
				String id = result.getString(1);
				String nome = result.getString(2);
				String email = result.getString(3);
				String posicao = result.getString(4);
				String cargo = result.getString(5);

				Interface.model.addRow(new Object[] { id, nome, email, posicao, cargo });
			}

			Interface.tbDados = new JTable(Interface.dados, Interface.nomeColunas);

			conex.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao procurar por dados!");
		}
	}
	
	/**
	 * Insere os dados inseridos nos elementos da janela no banco.
	 */

	public static void inserir() {
		String nome = "", email = "", posicao = "", cargo = "";
		String sql = "INSERT INTO Usuario (Nome,Email,Posicao,Cargo) VALUES (?, ?, ?, ?)";

		try {
			Connection conex = DriverManager.getConnection(Interface.url, Interface.user, Interface.senha);

			PreparedStatement statement = conex.prepareStatement(sql);

			nome = Interface.tfNome.getText();
			statement.setString(1, nome);

			email = Interface.tfEmail.getText();
			statement.setString(2, email);

			posicao = Interface.posicao;
			statement.setString(3, posicao);

			cargo = Interface.cargo[0] + " " + Interface.cargo[1] + " " + Interface.cargo[2];
			statement.setString(4, cargo);

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
				atualizar();
			}

			conex.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados!");
		}
	}

	/**
	 * Busca dados no banco de acordo com os dados inseridos nos elementos da janela.
	 */
	public static void buscar() {
		String sql = "SELECT * FROM Usuario WHERE Id=? OR Nome=? OR Email=?";

		try {
			Connection conex = DriverManager.getConnection(Interface.url, Interface.user, Interface.senha);

			PreparedStatement statement = conex.prepareStatement(sql);

			String id = Interface.tfId.getText();
			statement.setString(1, id);

			String nome = Interface.tfNome.getText();
			statement.setString(2, nome);

			String email = Interface.tfEmail.getText();
			statement.setString(3, email);

			ResultSet result = statement.executeQuery();

			if (!result.next()) {
				atualizar();
			} else {

				Interface.model.setRowCount(0);

				result.beforeFirst();

				while (result.next()) {
					String idUsuario = result.getString(1);
					String nomeUsuario = result.getString(2);
					String emailUsuario = result.getString(3);
					String posicaoUsuario = result.getString(4);
					String cargoUsuario = result.getString(5);

					Interface.model.addRow(
							new Object[] { idUsuario, nomeUsuario, emailUsuario, posicaoUsuario, cargoUsuario });
				}

				Interface.tbDados = new JTable(Interface.dados, Interface.nomeColunas);
			}

			conex.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar!");
		}

	}

	/**
	 * Exibe uma mensagem para alterar nome e e-mail do usuário.
	 */
	public static void alterar() {
		String aux = "";
		String sql = "UPDATE Usuario SET nome=?, email=? WHERE nome=?";

		try {
			Connection conex = DriverManager.getConnection(Interface.url, Interface.user, Interface.senha);

			PreparedStatement statement = conex.prepareStatement(sql);

			aux = JOptionPane.showInputDialog(null, "Insira o nome do usuário a ser alterado: ");
			statement.setString(3, aux);

			aux = JOptionPane.showInputDialog(null, "Insira o novo nome: ");
			statement.setString(1, aux);

			aux = JOptionPane.showInputDialog(null, "Insira o novo e-mail: ");
			statement.setString(2, aux);

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
				atualizar();
			}

			conex.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar dados!");
		}
	}

	/**
	 * Busca e exclui os dados no banco de acordo com os dados inseridos nos elementos da janela.
	 */
	public static void excluir() {

		String sql = "DELETE FROM Usuario WHERE id=? OR nome=? OR email=?";

		try {
			Connection conex = DriverManager.getConnection(Interface.url, Interface.user, Interface.senha);

			PreparedStatement statement = conex.prepareStatement(sql);

			String id = Interface.tfId.getText();
			statement.setString(1, id);

			String nome = Interface.tfNome.getText();
			statement.setString(2, nome);

			String email = Interface.tfEmail.getText();
			statement.setString(3, email);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso!");
				atualizar();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao exluir dados!");
		}
	}

	/**
	 * Encerra a aplicação.
	 */
	public static void sair() {
		System.exit(0);
	}

}
