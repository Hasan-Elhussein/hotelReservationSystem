package hotelReservationSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField tf_telefonNo;
	private JPasswordField pf_parola;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 550, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Giris");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//(hasan) 3 degiskenin degerleri query kodun icine yazip sql veri tabanina aktarma kodu 	
				String p_no = tf_telefonNo.getText();
				long phone_no = Long.parseLong(p_no);
				String parola = String.valueOf(pf_parola.getPassword());
				int tf_teleonNo_length = tf_telefonNo.getText().length();
				
				
				//(hasan) yeni kullanici veritabanda mevcut olup olmadigini kontrol eden kod
		        PreparedStatement ps0;
		        ResultSet rs;
		        boolean checkUser = false;
		        String query0 = "SELECT * FROM `users` WHERE phone_no = ? AND pass = ?";

		        try {
		        	ps0 = DBconnection.getConnection().prepareStatement(query0);
		        	ps0.setLong(1, phone_no);
		        	ps0.setString(2, parola);
	            
		        	rs = ps0.executeQuery();
		        	//(hasan) veritabanda kullanici mevcut olup olmadigini kontrol eden kodu
		        	if(rs.next()){
		        		dispose();
		        		new mainFrame().setVisible(true);
		        		}
		        	else
		        		JOptionPane.showMessageDialog(null, "telefon numara veya parola hatali");
	            
		        } catch (SQLException e2) {
		        	JOptionPane.showMessageDialog(null, "SQL'de hata olustu");
		        	e2.printStackTrace();
		        }
		        
				
			}
		});
		btnNewButton.setBackground(new Color(30, 144, 255));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(20, 246, 89, 23);
		contentPane.add(btnNewButton);
		
		tf_telefonNo = new JTextField();
		tf_telefonNo.setBounds(20, 40, 188, 20);
		contentPane.add(tf_telefonNo);
		tf_telefonNo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Telefon Numarasi:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(20, 15, 150, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Parola:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(20, 71, 150, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Yeni Kayit");
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			//(hasan) bu formu kapatip, farkli bir formu acan kod
			public void actionPerformed(ActionEvent e) {
				dispose();
				new signup().setVisible(true);
			}

		});
		btnNewButton_1.setBounds(119, 246, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblimage = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		lblimage.setIcon(new ImageIcon(img));
		lblimage.setBounds(268, 40, 256, 229);
		contentPane.add(lblimage);
		
		pf_parola = new JPasswordField();
		pf_parola.setBounds(20, 96, 188, 20);
		contentPane.add(pf_parola);
	}
}
