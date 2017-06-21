import java.io.File;
/**
Class to filter files for .xml only
 */

public class XMLFilter extends javax.swing.filechooser.FileFilter {

	public boolean accept(File f) {
		//if it is a directory -- we want to show it so return true.
		if (f.isDirectory()) 
			return true;

		String extension = getExtension(f);

		if ( extension.equals("xml") ) 
			return true; 

		return false;
	}


	public String getDescription() {
		return "XML files";
	}
	
	private String getExtension(File f) {

		String s = f.getName();

		int i = s.lastIndexOf('.');
		if (i > 0 &&  i < s.length() - 1) 
			return s.substring(i+1).toLowerCase();
		return "";
	}
}