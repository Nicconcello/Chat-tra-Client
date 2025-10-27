package c;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ascoltatorechat implements ActionListener{

		private JTextField input;
		private JTextField output;
		private boolean connesso = false;
		private Scanner sc;
		private PrintWriter pw;
		private Socket socket;
		public String nome;
		private BufferedReader in;
		
	public ascoltatorechat(JTextField input, JTextField output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if(comando.equals("SEND")) {
			if(connesso) {
				String msg = input.getText();
				pw.println(msg);
				input.setText("");
				
				if(msg.equals("close")) {
					try {
					pw.close();
					sc.close();
					socket.close();
					connesso = false;
					output.setText("Server terminato");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			else {
				output.setText("Non connesso");
			}
		}
		else if(comando.equals("CONNECT")) {
			if(connesso) {
				output.setText("Utente già connesso");
			}
			else {
				try {
					int porta = Integer.parseInt(input.getText());
					socket = new Socket("localhost", porta);
					sc = new Scanner(socket.getInputStream());
					pw = new PrintWriter(socket.getOutputStream(), true);
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					connesso = true;
					
					output.setText("Connesso");
					input.setText("");
					
					new Thread(() -> {
	                    String msg;
	                    try {
	                        while ((msg = in.readLine()) != null) {
	                            String finalMsg = msg;
	                            SwingUtilities.invokeLater(() -> output.setText(finalMsg + "\n"));
	                        }
	                    } catch (IOException ex) {
	                        ex.printStackTrace();
	                        SwingUtilities.invokeLater(() -> output.setText("Connessione persa!\n"));
	                    }
	                }).start();

	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(comando.equals("DISCONNECT")) {
			if(connesso) {
				try {
					pw.close();
					sc.close();
					socket.close();
					connesso = false;
					output.setText("Sei uscito");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				output.setText("Server già chiuso");
			}
		}
	}

}
