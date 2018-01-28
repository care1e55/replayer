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
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class scbwFrame  extends JFrame  {

	public JPanel contentPane;
	private JTextField PageTxT;
	public JTextPane outputConsole;
	public StyledDocument doc;

	static String bwexec = "StarCraft.bat";
	static String canrepexec = "CanRep.exe";
	
//	private JTextField bwPath;
//	private JTextField canrepPath;

	
	public scbwFrame() throws Exception {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		JLabel bwpathLabel = new JLabel("SCBW .bat");
//		JLabel canreppathLabel = new JLabel("Canrep.exe");
		JLabel pageLabel = new JLabel("Page \u2116");
//		bwpathLabel.setBounds(135, 11, 69, 23);
//		canreppathLabel.setBounds(135, 46, 69, 23);
		pageLabel.setBounds(10, 45, 63, 23);
		pageLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		contentPane.add(bwpathLabel);
//		contentPane.add(canreppathLabel);
		contentPane.add(pageLabel);
				
//		bwPath = new JTextField();
//		canrepPath = new JTextField();
		PageTxT = new JTextField();
//		bwPath.setBounds(214, 11, 218, 23);
//		canrepPath.setBounds(214, 47, 218, 20);
		PageTxT.setBounds(68, 46, 55, 22);
//		contentPane.add(bwPath);
//		contentPane.add(canrepPath);
		contentPane.add(PageTxT);
		
		outputConsole = new JTextPane();
		doc = outputConsole.getStyledDocument();
		outputConsole.setBounds(2, 2, 108, 32);
		JScrollPane jsp = new JScrollPane(outputConsole);
		jsp.setBounds(10, 79, 422, 183);
		contentPane.add(jsp);
		
		JButton btnStartWatching = new JButton("Start watching");
		btnStartWatching.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Runtime rt = Runtime.getRuntime();
					System.out.println(bwexec);
					rt.exec("cmd /C start "+bwexec);
					rt.exec(canrepexec);
					Watcher watcher = new Watcher(Integer.parseInt(PageTxT.getText()), doc);
					Thread myThready = new Thread(watcher);
					myThready.start(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnStartWatching.setBounds(10, 11, 115, 23);
		contentPane.add(btnStartWatching);
		
	}
}

