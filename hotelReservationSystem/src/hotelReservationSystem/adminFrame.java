package hotelReservationSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class adminFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf_odaNumarasi;
	private JTextField tf_kisiSayisi;
	private JTextField tf_ucret;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminFrame frame = new adminFrame();
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
	public adminFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 550, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Administration Panel");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(172, 11, 188, 14);
		contentPane.add(lblNewLabel);
		
		tf_odaNumarasi = new JTextField();
		tf_odaNumarasi.setBounds(10, 75, 150, 20);
		contentPane.add(tf_odaNumarasi);
		tf_odaNumarasi.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Oda Numarasi:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 50, 150, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblYatakSayisi = new JLabel("Kisi Sayisi:");
		lblYatakSayisi.setForeground(Color.WHITE);
		lblYatakSayisi.setBounds(10, 106, 150, 14);
		contentPane.add(lblYatakSayisi);
		
		tf_kisiSayisi = new JTextField();
		tf_kisiSayisi.setColumns(10);
		tf_kisiSayisi.setBounds(10, 131, 150, 20);
		contentPane.add(tf_kisiSayisi);
		
		JLabel lblUcret = new JLabel("Ucret: (TL)");
		lblUcret.setForeground(Color.WHITE);
		lblUcret.setBounds(10, 162, 150, 14);
		contentPane.add(lblUcret);
		
		tf_ucret = new JTextField();
		tf_ucret.setColumns(10);
		tf_ucret.setBounds(10, 187, 150, 20);
		contentPane.add(tf_ucret);
		
		JRadioButton rdbtn_tekYatak = new JRadioButton("Tek Yatak");
		rdbtn_tekYatak.setForeground(Color.WHITE);
		rdbtn_tekYatak.setBackground(Color.GRAY);
		rdbtn_tekYatak.setBounds(195, 74, 150, 23);
		contentPane.add(rdbtn_tekYatak);
		
		JLabel lblOdaTipi = new JLabel("Oda Tipi:");
		lblOdaTipi.setForeground(Color.WHITE);
		lblOdaTipi.setBounds(195, 50, 150, 14);
		contentPane.add(lblOdaTipi);
		
		JRadioButton rdbtn_ciftYatak = new JRadioButton("Cift Yatak");
		rdbtn_ciftYatak.setForeground(Color.WHITE);
		rdbtn_ciftYatak.setBackground(Color.GRAY);
		rdbtn_ciftYatak.setBounds(195, 102, 150, 23);
		contentPane.add(rdbtn_ciftYatak);
		
		JRadioButton rdbtn_suitOda = new JRadioButton("Suit Oda");
		rdbtn_suitOda.setForeground(Color.WHITE);
		rdbtn_suitOda.setBackground(Color.GRAY);
		rdbtn_suitOda.setBounds(195, 130, 150, 23);
		contentPane.add(rdbtn_suitOda);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Tikla");
		tglbtnNewToggleButton.setBackground(Color.RED);
		tglbtnNewToggleButton.setForeground(Color.WHITE);
		tglbtnNewToggleButton.setBounds(195, 186, 150, 23);
		contentPane.add(tglbtnNewToggleButton);
		
		JLabel lblDoluMu = new JLabel("Dolu mu?");
		lblDoluMu.setForeground(Color.WHITE);
		lblDoluMu.setBounds(195, 162, 150, 14);
		contentPane.add(lblDoluMu);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 286, 150, 14);
		contentPane.add(progressBar);
		
		ButtonGroup G = new ButtonGroup();
		G.add(rdbtn_tekYatak);
		G.add(rdbtn_ciftYatak);
		G.add(rdbtn_suitOda);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(10, 245, 150, 20);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("yyyy-mm-dd");
		
		JButton btn_ekle = new JButton("Ekle");
		btn_ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//=============================================================================================//
				
				//(hasan) 3 degiskenin degerleri query kodun icine yazip sql veri tabanina aktarma kodu
				int odaNumarasi = Integer.parseInt(tf_odaNumarasi.getText());
				int kisiSayisi = Integer.parseInt(tf_kisiSayisi.getText());
				float ucret = Float.parseFloat(tf_ucret.getText());
				int odaTipi = 1;
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				
				//(hasan) yeni kullanici veritabanda mevcut olup olmadigini kontrol eden kod
		        PreparedStatement ps0;
		        ResultSet rs;
		        boolean checkUser = false;
		        String query0 = "SELECT * FROM `hotel1` WHERE id = ?";

		        try {
		        	ps0 = DBconnection.getConnection().prepareStatement(query0);
		        	ps0.setLong(1, odaNumarasi);
	            
		        	rs = ps0.executeQuery();
	            
		        	if(rs.next()) {
		        		checkUser = true;
		        		JOptionPane.showMessageDialog(null, "Oda mevcuttur!");
		        	}
	            
		        } catch (SQLException e2) {
		        	JOptionPane.showMessageDialog(null, "SQL'de hata olustu");
		        	e2.printStackTrace();
		        }
		
		         
				
				PreparedStatement ps;
				String query = "INSERT INTO hotel1(id,oda_tipi,kisi_sayisi,ucret,tarih) VALUES(?,?,?,?,?)";
		        
		            try {

				        //(hasan) Oda mevcut ise alttaki kod calismaz
				        if(checkUser == false){
							ps = DBconnection.getConnection().prepareStatement(query);
							ps.setInt(1, odaNumarasi);
							if(rdbtn_tekYatak.isSelected())
								ps.setInt(2, 1);
							else if(rdbtn_ciftYatak.isSelected())
								ps.setInt(2, 2);
							else if(rdbtn_suitOda.isSelected())
								ps.setInt(2, 3);
							ps.setInt(3, kisiSayisi);
							ps.setFloat(4, ucret);
							ps.setDate(5, java.sql.Date.valueOf(df.format(dateChooser.getDate())));

							
			            if(ps.executeUpdate() > 0)
			            {
			                JOptionPane.showMessageDialog(null, "Oda olusturuldu");
			            }
			            
				        }
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		            
				//=============================================================================================//
				
			}
		});
		btn_ekle.setBounds(195, 245, 150, 23);
		contentPane.add(btn_ekle);
		
		JButton btn_sil = new JButton("Sil");
		btn_sil.setBounds(195, 277, 150, 23);
		contentPane.add(btn_sil);
		
		JList list = new JList();
		list.setBounds(375, 75, 149, 225);
		contentPane.add(list);
		
		JLabel lblOda = new JLabel("Odalar:");
		lblOda.setForeground(Color.WHITE);
		lblOda.setBounds(375, 50, 150, 14);
		contentPane.add(lblOda);
		
		JLabel lblTarih = new JLabel("Tarih:\r\n");
		lblTarih.setForeground(Color.WHITE);
		lblTarih.setBounds(10, 218, 150, 14);
		contentPane.add(lblTarih);
		
	}
}
