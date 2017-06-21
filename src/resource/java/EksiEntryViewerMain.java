import javax.swing.JFileChooser;

public class EksiEntryViewerMain {

	public static void main(String[] args) {
		new EksiEntryViewerMain();
	}

	public EksiEntryViewerMain() {

		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new XMLFilter());
		int returnVal = fc.showOpenDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: "
					+ fc.getSelectedFile().getAbsolutePath());

			EksiEntryViewer app = new EksiEntryViewer();
			app.parseEksiXML(fc.getSelectedFile());

			new EksiEntryViewerGui(app.getEntry(), fc.getSelectedFile()
					.getName());

		} else {
			System.exit(0);
		}

	}
}
