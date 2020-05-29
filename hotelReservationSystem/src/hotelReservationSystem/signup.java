package hotelReservationSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.PreparedStatement;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField tf_kullaniciAdi;
	private JTextField tf_telefonNo;
	private JPasswordField pf_parola;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
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
	public signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 550, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tf_kullaniciAdi = new JTextField();
		tf_kullaniciAdi.setColumns(10);
		tf_kullaniciAdi.setBounds(20, 40, 188, 20);
		contentPane.add(tf_kullaniciAdi);
		
		JLabel lblKullaniciAdi = new JLabel("Kullanici Adi:");
		lblKullaniciAdi.setForeground(Color.WHITE);
		lblKullaniciAdi.setBounds(20, 15, 150, 14);
		contentPane.add(lblKullaniciAdi);
		
		JLabel lblTelefonNumarasi = new JLabel("Telefon Numarasi:");
		lblTelefonNumarasi.setForeground(Color.WHITE);
		lblTelefonNumarasi.setBounds(20, 71, 150, 14);
		contentPane.add(lblTelefonNumarasi);
		
		JLabel lblHata = new JLabel("");
		lblHata.setForeground(Color.RED);
		lblHata.setBounds(218, 99, 89, 14);
		contentPane.add(lblHata);
		
		tf_telefonNo = new JTextField();
		tf_telefonNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					long i = Long.parseLong(tf_telefonNo.getText());
					
					lblHata.setText("");
				} catch (NumberFormatException e2) {
					lblHata.setText("Hatali Giris!");
				}
			}
		});
		tf_telefonNo.setColumns(10);
		tf_telefonNo.setBounds(20, 96, 188, 20);
		contentPane.add(tf_telefonNo);
		
		JLabel lblParola = new JLabel("Parola:");
		lblParola.setForeground(Color.WHITE);
		lblParola.setBounds(20, 127, 150, 14);
		contentPane.add(lblParola);
		
		JButton button = new JButton("Giris");
		button.addActionListener(new ActionListener() {
			//(hasan) bu formu kapatip, farkli bir formu acan kod
			public void actionPerformed(ActionEvent e) {
				dispose();
				new login().setVisible(true);
			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(30, 144, 255));
		button.setBounds(20, 265, 89, 23);
		contentPane.add(button);
		
		JButton btnKayitOlustur = new JButton("Kayit Olustur");
		btnKayitOlustur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//(hasan) 3 degiskenin degerleri query kodun icine yazip sql veri tabanina aktarma kodu
				String kullanici_ad = tf_kullaniciAdi.getText(); 	
				String p_no = tf_telefonNo.getText();
				long phone_no = Long.parseLong(p_no);
				String parola = String.valueOf(pf_parola.getPassword());

				PreparedStatement ps;
				String query = "INSERT INTO users(phone_no,name,pass) VALUES(?,?,?)";
		        
		            try {

						//(hasan) degiskenlerin bos olup olmadigini kontrol eden kod
				        if(p_no.equals(""))
				        {
				            JOptionPane.showMessageDialog(null, "Telefon numaranizi giriniz!");
				        }
				        else if(kullanici_ad.equals(""))
				        {
				            JOptionPane.showMessageDialog(null, "Kullanici Adinizi giriniz!");
				        }
				        else if(parola.equals(""))
				        {
				            JOptionPane.showMessageDialog(null, "Parolayi giriniz!");
				        }
				        else {
				        	
							ps = DBconnection.getConnection().prepareStatement(query);
							ps.setLong(1, phone_no);
							ps.setString(2, kullanici_ad);
							ps.setString(3, parola);
							
			            if(ps.executeUpdate() > 0)
			            {
			                JOptionPane.showMessageDialog(null, "Basarli bir sekilde hesaninizi olusturuldu");
			            }
			            
				        }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            


			}
		});
		btnKayitOlustur.setForeground(Color.WHITE);
		btnKayitOlustur.setBackground(Color.RED);
		btnKayitOlustur.setBounds(20, 231, 188, 23);
		contentPane.add(btnKayitOlustur);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/newuser.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(272, 27, 234, 237);
		contentPane.add(lblNewLabel);
		
		pf_parola = new JPasswordField();
		pf_parola.setBounds(20, 152, 188, 20);
		contentPane.add(pf_parola);
		

	}
}
