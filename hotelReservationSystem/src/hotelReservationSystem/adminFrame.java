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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class adminFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf_odaNumarasi;
	private JTextField tf_kisiSayisi;
	private JTextField tf_ucret;

	/**
	 * Launch the application.
	 */
	static JList list_view = new JList();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminFrame frame = new adminFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//(hasan) frame ilk acildiginda listView icine bilgileri yazdiran kod
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
					list_view.setModel(dlm);
				} catch (SQLException e1) {
					e1.printStackTrace();
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
		setResizable(false);
		
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
		
		JToggleButton tglbtn_tikla = new JToggleButton("Dolu");
		tglbtn_tikla.setBackground(Color.RED);
		tglbtn_tikla.setForeground(Color.WHITE);
		tglbtn_tikla.setBounds(195, 186, 150, 23);
		contentPane.add(tglbtn_tikla);
		
		JLabel lblDoluMu = new JLabel("Dolu mu?");
		lblDoluMu.setForeground(Color.WHITE);
		lblDoluMu.setBounds(195, 162, 150, 14);
		contentPane.add(lblDoluMu);
		
		ButtonGroup G = new ButtonGroup();
		G.add(rdbtn_tekYatak);
		G.add(rdbtn_ciftYatak);
		G.add(rdbtn_suitOda);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(10, 245, 150, 20);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		
		list_view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//(hasan) listeden bir elemana tiklandiginda o elemanin bilgileri getiren ve ekrana yazdiran
				String tmp0 =(String)list_view.getSelectedValue();
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
							rdbtn_tekYatak.setSelected(true);
							break;
							}
						case "Cift Yatak":{
							rdbtn_ciftYatak.setSelected(true);
							break;
						}
						case "Suit Oda":{
							rdbtn_suitOda.setSelected(true);
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
			        	if(add5 == 1)
			        		tglbtn_tikla.setSelected(true);
			        	else
			        		tglbtn_tikla.setSelected(false);
		        	}
				} catch (Exception e2) {
					
				}
				
				
			}
		});
		list_view.setBounds(375, 75, 149, 225);
		contentPane.add(list_view);
		
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
				String query = "INSERT INTO hotel1(id,oda_tipi,kisi_sayisi,ucret,tarih,is_full) VALUES(?,?,?,?,?,?)";
		        
		            try {

				        //(hasan) Oda mevcut ise alttaki kod calismaz
				        if(checkUser == false){
							ps = DBconnection.getConnection().prepareStatement(query);
							ps.setInt(1, odaNumarasi);
							if(rdbtn_tekYatak.isSelected())
								ps.setString(2, "Tek Yatak");
							else if(rdbtn_ciftYatak.isSelected())
								ps.setString(2, "Cift Yatak");
							else if(rdbtn_suitOda.isSelected())
								ps.setString(2, "Suit Oda");
							ps.setInt(3, kisiSayisi);
							ps.setFloat(4, ucret);
							if(dateChooser.getDate() == null)
								ps.setDate(5, null);
							else
								ps.setDate(5, java.sql.Date.valueOf(df.format(dateChooser.getDate())));
							if(tglbtn_tikla.isSelected() == true)
								ps.setBoolean(6, true);
							else 
								ps.setBoolean(6, false);
							
			            if(ps.executeUpdate() > 0)
			            {
			                JOptionPane.showMessageDialog(null, "Oda olusturuldu");
			            }
			            
				        }
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		            
				//=============================================================================================//
		            
		            //(hasan)sql veritabinindan bilgileri getirip, list icine aktaran kod
		            String query2 = "SELECT * FROM `hotel1`";
		            DefaultListModel dlm = new DefaultListModel();
		            ResultSet rs2;
		            try {
						ps = DBconnection.getConnection().prepareStatement(query2);
						rs = ps.executeQuery();
						while(rs.next()) {
							String id = rs.getString("id");
							dlm.addElement(id);
						}
						list_view.setModel(dlm);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

		            
		            
		          //=============================================================================================//
		            
				
			}
		});
		btn_ekle.setBounds(195, 245, 150, 23);
		contentPane.add(btn_ekle);
		
		JButton btn_sil = new JButton("Sil");
		btn_sil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String tmp0 =(String)list_view.getSelectedValue();
				int tmp = Integer.parseInt(tmp0);
		        PreparedStatement ps;
		        ResultSet rs;
		        
		        String query = "DELETE FROM `hotel1` WHERE id = "+tmp;

		        	try {
						ps = DBconnection.getConnection().prepareStatement(query);
						ps.executeUpdate();
			        	//rs = ps.executeQuery();
			        	JOptionPane.showMessageDialog(null, "Oda silindi");
			        	
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
		        	
		        	
					//(hasan) element silindikten sonra, listeyi yenileme yapan kod
		            String query1 = "SELECT * FROM `hotel1`";
		            DefaultListModel dlm = new DefaultListModel();
		            try {
						ps = DBconnection.getConnection().prepareStatement(query1);
						rs = ps.executeQuery();
						while(rs.next()) {
							String id = rs.getString("id");
							dlm.addElement(id);
						}
						list_view.setModel(dlm);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				
			}
		});
		btn_sil.setBounds(195, 277, 150, 23);
		contentPane.add(btn_sil);
		

		
		JLabel lblOda = new JLabel("Odalar:");
		lblOda.setForeground(Color.WHITE);
		lblOda.setBounds(375, 50, 61, 14);
		contentPane.add(lblOda);
		
		JLabel lblTarih = new JLabel("Tarih:\r\n");
		lblTarih.setForeground(Color.WHITE);
		lblTarih.setBounds(10, 218, 150, 14);
		contentPane.add(lblTarih);
		
		JLabel lblKirmiziDolu = new JLabel("Kirmizi = Dolu");
		lblKirmiziDolu.setForeground(Color.WHITE);
		lblKirmiziDolu.setBounds(229, 220, 116, 14);
		contentPane.add(lblKirmiziDolu);
		
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
					list_view.setModel(dlm);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	            
			}
		});
		btnNewButton.setBounds(435, 46, 89, 23);
		contentPane.add(btnNewButton);
		
	}
}
