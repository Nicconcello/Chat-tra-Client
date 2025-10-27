package c;

import java.awt.BorderLayout;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class FrameChat extends JFrame{
	public JTextField testo;
	private Socket socket;

	public FrameChat() {
		
		super("CHAT");
		setSize(400,120);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		testo = new JTextField();
		testo.setEditable(false);
		add(testo, BorderLayout.CENTER);
		
		setVisible(true);
	}

}
