package c;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientChatframe extends JFrame{

	public ClientChatframe() {
		super("CLIENT");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pano = new JPanel(new GridLayout(2,1));
		JLabel eto = new JLabel("OUTPUT");
		JTextField output = new JTextField();
		output.setEditable(false);
		pano.add(eto);
		pano.add(output);
		add(pano, BorderLayout.NORTH);
		
		JPanel pani = new JPanel(new GridLayout(2,1));
		JLabel eti = new JLabel("INPUT");
		JTextField input = new JTextField();
		pani.add(eti);
		pani.add(input);
		add(pani, BorderLayout.CENTER);
		
		JPanel pb = new JPanel();
		JButton bc = new JButton("CONNECT");
		JButton bs = new JButton("SEND");
		JButton bd = new JButton("DISCONNECT");
		pb.add(bc);
		pb.add(bs);
		pb.add(bd);
		add(pb, BorderLayout.SOUTH);
		
		ascoltatorechat al = new ascoltatorechat(input, output);
		bc.addActionListener(al);
		bs.addActionListener(al);
		bd.addActionListener(al);
		
		output.setText("Inserisci la porta di connesione");
		input.setText("");
		
		setVisible(true);

		
	}
}
