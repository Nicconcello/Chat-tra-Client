package c;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.SwingUtilities;

public class serverChat {

	public static void main(String[] args) throws IOException {
		boolean connesso = true;
		FrameChat finestra = new FrameChat();
		ServerSocket ss = new ServerSocket(1234);
		
		while(true) {
			SwingUtilities.invokeLater(() -> finestra.testo.setText("Il server sta aspettando......"));
			Socket s = ss.accept();
			
			ChatThread ct = new ChatThread(connesso, finestra, s);
			Thread t = new Thread(ct);
			
			t.start();
		}

	}

}
