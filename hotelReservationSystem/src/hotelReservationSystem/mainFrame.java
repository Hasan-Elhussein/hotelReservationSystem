package hotelReservationSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf_odaNumarasi;
	private JTextField tf_odaTipi;
	private JTextField tf_ucret;
	private JTextField tf_doluMu;
	private JTextField tf_kisiSayisi;

	/**
	 * Launch the application.
	 */
	
	static JList listView = new JList();
	
	JDateChooser dateChooser = new JDateChooser();
	public static JTextField tf_kullanici;
	JButton btnRezervasyonYap = new JButton("Rezervasyon yap");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				//(hasan) frame ilk acildiginda listView icine bilgileri yazdiran kod
		        PreparedStatement ps;
		        ResultSet rs;
	            String query1 = "SELECT * FROM `hotel1`";
	            DefaultListModel dlm = new DefaultListModel();
	            try {
					ps = DBconnection.getConnection().prepareStatement(query1);
					rs = ps.executeQuery();
					while(rs.next()) {
						String id = rs.getString("id");
						dlm.addElement(id);
					}
					listView.setModel(dlm);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	            
	            
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 550, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		listView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				//(hasan) listeden bir elemana tiklandiginda o elemanin bilgileri getiren ve ekrana yazdiran
				String tmp0 =(String)listView.getSelectedValue();
				int tmp = Integer.parseInt(tmp0);
				String query3="SELECT * FROM hotel1 WHERE id = ?";
				
				try {
					
			        PreparedStatement ps;
			        ResultSet rs;
		        	ps = DBconnection.getConnection().prepareStatement(query3);
		        	ps.setInt(1, tmp);
		        	rs = ps.executeQuery();
		        	tf_odaNumarasi.setText(tmp0);
		        	if(rs.next()) {
		        		String add1 = rs.getString("oda_tipi");
		        		switch (add1) {
						case "Tek Yatak": {
							tf_odaTipi.setText("Tek Yatak");
							break;
							}
						case "Cift Yatak":{
							tf_odaTipi.setText("Cift Yatak");
							break;
						}
						case "Suit Oda":{
							tf_odaTipi.setText("Suit Oda");
							break;
						}
						default:
							throw new IllegalArgumentException("Unexpected value: " + add1);
						}
		        		String add2 = rs.getString("kisi_sayisi");
			        	tf_kisiSayisi.setText(add2);
			        	String add3 = rs.getString("ucret");
			        	tf_ucret.setText(add3);
			        	Date add4 = rs.getDate("tarih");
			        	dateChooser.setDate(add4);
			        	int add5 = rs.getInt("is_full");
			        	if(add5 == 1) {
			        		tf_doluMu.setText("BOS");
			        		tf_kisiSayisi.setEditable(true);
			        		dateChooser.setEnabled(true);
			        		btnRezervasyonYap.setEnabled(true);
			        	}
			        	else {
			        		tf_doluMu.setText("DOLU");
			        		tf_kisiSayisi.setEditable(false);
			        		dateChooser.setEnabled(false);
			        		btnRezervasyonYap.setEnabled(false);
			        	}
		        	}
				} catch (Exception e2) {
					
				}
				
				
			}
		});
		
		
		listView.setBounds(375, 75, 149, 225);
		contentPane.add(listView);
		
		JLabel lblHotelReservationSystem = new JLabel("Hotel Reservation System");
		lblHotelReservationSystem.setForeground(Color.WHITE);
		lblHotelReservationSystem.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHotelReservationSystem.setBounds(147, 11, 231, 14);
		contentPane.add(lblHotelReservationSystem);
		setResizable(false);
		
		JLabel label = new JLabel("Oda Numarasi:");
		label.setForeground(Color.WHITE);
		label.setBounds(10, 76, 111, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Kisi Sayisi:");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(10, 175, 111, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Ucret: (TL)");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(10, 125, 111, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Oda Tipi:");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(10, 100, 111, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Dolu mu?");
		label_4.setForeground(Color.WHITE);
		label_4.setBounds(10, 150, 111, 14);
		contentPane.add(label_4);
		
		tf_odaNumarasi = new JTextField();
		tf_odaNumarasi.setEditable(false);
		tf_odaNumarasi.setColumns(10);
		tf_odaNumarasi.setBounds(121, 73, 150, 20);
		contentPane.add(tf_odaNumarasi);
		
		tf_odaTipi = new JTextField();
		tf_odaTipi.setEditable(false);
		tf_odaTipi.setColumns(10);
		tf_odaTipi.setBounds(121, 97, 150, 20);
		contentPane.add(tf_odaTipi);
		
		tf_ucret = new JTextField();
		tf_ucret.setEditable(false);
		tf_ucret.setColumns(10);
		tf_ucret.setBounds(121, 122, 150, 20);
		contentPane.add(tf_ucret);
		
		tf_doluMu = new JTextField();
		tf_doluMu.setEditable(false);
		tf_doluMu.setColumns(10);
		tf_doluMu.setBounds(121, 147, 150, 20);
		contentPane.add(tf_doluMu);
		
		tf_kisiSayisi = new JTextField();
		tf_kisiSayisi.setEditable(false);
		tf_kisiSayisi.setColumns(10);
		tf_kisiSayisi.setBounds(121, 172, 150, 20);
		contentPane.add(tf_kisiSayisi);
		
		JLabel label_5 = new JLabel("Odalar:");
		label_5.setForeground(Color.WHITE);
		label_5.setBounds(375, 50, 58, 14);
		contentPane.add(label_5);
		
		btnRezervasyonYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				int odaNumarasi = Integer.parseInt(tf_odaNumarasi.getText());
				int kisiSayisi = Integer.parseInt(tf_kisiSayisi.getText());
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				
				//(hasan) rezervasyon bilgileri veritabanina aktaran kodu
				PreparedStatement ps;
				String query = "UPDATE hotel1 SET kisi_sayisi=?, tarih=?, is_full=false, user=? WHERE id=?";
				try {
					
					ps = DBconnection.getConnection().prepareStatement(query);
					
					ps.setInt(1, kisiSayisi);
					ps.setDate(2, java.sql.Date.valueOf(df.format(dateChooser.getDate())));
					ps.setString(3, tf_kullanici.getText());
					ps.setInt(4, odaNumarasi);
					
					ps.executeUpdate();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnRezervasyonYap.setBounds(121, 243, 150, 23);
		contentPane.add(btnRezervasyonYap);
		
		JButton btnOdadanAyir = new JButton("Odadan ayril");
		btnOdadanAyir.setBounds(121, 277, 150, 23);
		contentPane.add(btnOdadanAyir);
		dateChooser.getCalendarButton().setForeground(new Color(255, 255, 255));
		dateChooser.getCalendarButton().setEnabled(false);
		
		dateChooser.setBounds(121, 200, 150, 20);
		contentPane.add(dateChooser);
		dateChooser.setEnabled(false);
		
		JLabel lblTarih = new JLabel("Tarih:");
		lblTarih.setForeground(Color.WHITE);
		lblTarih.setBounds(10, 200, 111, 14);
		contentPane.add(lblTarih);
		
		JButton btnNewButton = new JButton("Yenile");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//(hasan) listeyi yenileme kodu
		        PreparedStatement ps;
		        ResultSet rs;
	            String query = "SELECT * FROM `hotel1`";
	            DefaultListModel dlm = new DefaultListModel();
	            try {
					ps = DBconnection.getConnection().prepareStatement(query);
					rs = ps.executeQuery();
					while(rs.next()) {
						String id = rs.getString("id");
						dlm.addElement(id);
					}
					listView.setModel(dlm);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	            
			}
		});
		btnNewButton.setBounds(435, 46, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblMerhabalar = new JLabel("Merhabalar:");
		lblMerhabalar.setForeground(Color.WHITE);
		lblMerhabalar.setBounds(10, 46, 111, 14);
		contentPane.add(lblMerhabalar);
		
		tf_kullanici = new JTextField();
		tf_kullanici.setEditable(false);
		tf_kullanici.setColumns(10);
		tf_kullanici.setBounds(121, 42, 150, 20);
		contentPane.add(tf_kullanici);
	}
}
