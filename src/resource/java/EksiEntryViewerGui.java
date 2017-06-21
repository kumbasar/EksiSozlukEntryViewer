import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class EksiEntryViewerGui extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Entry[] entry;

	private JTable table;
	private JTextField searchField;

	private final String SOZLUK_URI = "http://www.eksisozluk.com/show.asp?id=";
	private final String TITLE = "EksiSozluk Entry Gosterge 1.02b - http://kumbi.wordpress.com - sUSER: ";

	public EksiEntryViewerGui(Entry[] entry, String suserName) {

		this.entry = entry;

		showEntry();

		setTitle(TITLE + suserName);
		setSize(1000, 500);
		setLocation(100, 100);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void showEntry() {

		table = new JTable(entry.length, 4) {
			private static final long serialVersionUID = -5139793767832988955L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};
		table.setAutoCreateRowSorter(true);

		table.getColumnModel().getColumn(0).setHeaderValue("Id");
		table.getColumnModel().getColumn(1).setHeaderValue("Baslik");
		table.getColumnModel().getColumn(2).setHeaderValue("Entry");
		table.getColumnModel().getColumn(3).setHeaderValue("Tarih");

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(240);
		table.getColumnModel().getColumn(2).setPreferredWidth(550);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);

		for (int s = 0; s < table.getRowCount(); ++s) {
			table.setValueAt(
					DateFormat.getInstance().format((entry[s].getEntryDate())),
					s, 3);
			table.setValueAt(entry[s].getEntryID(), s, 0);
			table.setValueAt(entry[s].getEntryTitle(), s, 1);
			table.setValueAt(entry[s].getEntryBody(), s, 2);
		}

		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				JTable table = (JTable) e.getSource();
				Point pt = e.getPoint();

				int ccol = table.columnAtPoint(pt);
				int crow = table.rowAtPoint(pt);

				// System.out.println("Mouse Point " + ccol + " " + crow);

				if (ccol == 0 /* || e.getClickCount() == 2 */) {
					// ccol = 0; // to get entry id
					// System.out.println(table.getValueAt(crow, ccol));
					try {
						Desktop.getDesktop()
								.browse(new URI(SOZLUK_URI
										+ table.getValueAt(crow, 0)));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				} else {

					String messageStr = (String) table.getValueAt(crow, 1);
					messageStr += "\n" + (String) table.getValueAt(crow, 2);

					JOptionPane.showMessageDialog(null, messageStr);
				}

			}
		});

		JScrollPane scrollPane = new JScrollPane(table);

		JPanel p = new JPanel();

		searchField = new JTextField(30);
		searchField.addActionListener(this);

		p.add(new JLabel("Ara"), BorderLayout.WEST);
		p.add(searchField, BorderLayout.EAST);

		add(p, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);

	}

	public void actionPerformed(ActionEvent event) {

		if (searchField.equals(event.getSource())) {
			search(searchField.getText());
		}
	}

	private void search(String str) {
		//System.out.println(" > " + str);
		// System.out.println(entry[0].getEntryBody());

		table.clearSelection();

		//boolean found = false;
		
		int counter = 0;

		for (int s = 0; s < entry.length; ++s) {

			if (entry[s].getEntryBody().contains(str)
					|| entry[s].getEntryTitle().contains(str)) {
				//found = true;
				// System.out.println(entry[s].getEntryBody());
				// System.out.println("-----");
				table.addRowSelectionInterval(s, s);
				++counter;
			}
		}
		
		if (counter > 0) {
			JOptionPane.showMessageDialog(null, "Bulunan toplam entry sayisi = " + counter);
		}
		else {
			JOptionPane.showMessageDialog(null, "Bulunamadi :(");
		}


		// System.out.println("============");

	}

}
