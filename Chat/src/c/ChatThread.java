package c;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingUtilities;


public class ChatThread implements Runnable{
	private boolean c;
	private FrameChat f;
	private Socket s;
	private static final List<PrintWriter> clientWriters = Collections.synchronizedList(new ArrayList<>());


	public ChatThread(boolean c, FrameChat f, Socket s) {
		this.c = c;
		this.f = f;
		this.s = s;

	}

	@Override
	public void run() {
		try {
            Scanner sc = new Scanner(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            clientWriters.add(pw);

            c = true;

            SwingUtilities.invokeLater(() -> f.testo.setText("Utente connesso"));
            
        	SwingUtilities.invokeLater(() -> f.testo.setText("Inserisci il tuo nickname"));
        	String nome = sc.nextLine();
        	SwingUtilities.invokeLater(() -> f.testo.setText(nome + " è online!"));

        	while (c && sc.hasNextLine()) {
                String cv = sc.nextLine().trim();
                if (!cv.isEmpty()) {
                    String msg = nome + ": " + cv;
                    SwingUtilities.invokeLater(() -> f.testo.setText(msg + "\n"));
                    
                    for (PrintWriter writer : clientWriters) {
                        writer.println(msg);
                    }
                }
            }

            sc.close();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
