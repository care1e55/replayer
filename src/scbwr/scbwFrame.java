package scbwr;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledDocument;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class scbwFrame  extends JFrame  {

	public JPanel contentPane;
	private JTextField PageTxT;
	public JTextPane textPane;
	public StyledDocument doc;

	static String bwexec = "StarCraft.bat";
	static String canrepexec = "CanRep.exe";

	
	public scbwFrame() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStartWatching = new JButton("Start watching");
		btnStartWatching.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				Runtime rt = Runtime.getRuntime();
				System.out.println(bwexec);
				rt.exec("cmd /C start "+bwexec);
					rt.exec(canrepexec);
				Watcher watcher = new Watcher(Integer.parseInt(PageTxT.getText()));
				Thread myThready = new Thread(watcher);
				myThready.start(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			});
		btnStartWatching.setBounds(10, 11, 134, 23);
		contentPane.add(btnStartWatching);
		
		PageTxT = new JTextField();
		PageTxT.setText("23");
		PageTxT.setBounds(68, 46, 47, 22);
		contentPane.add(PageTxT);
		PageTxT.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("Page \u2116");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 45, 63, 23);
		contentPane.add(lblNewLabel);
		
		textPane = new JTextPane();
		doc = textPane.getStyledDocument();
		textPane.setBounds(0, 0, 422, 183);
		contentPane.add(textPane);
		
		JScrollPane jsp = new JScrollPane(textPane);
		jsp.setBounds(10, 79, 422, 183);
		contentPane.add(jsp);
		
		
	}
}
