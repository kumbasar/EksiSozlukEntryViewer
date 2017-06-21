import java.util.Date;


public class Entry {
	
	private String entryTitle;
	private String entryBody;
	private String entryID;
	private Date entryDate;
	
	public Entry() {
		
	}
	
	
	public String getEntryTitle() {
		return entryTitle;
	}
	public void setEntryTitle(String entryTitle) {
		this.entryTitle = entryTitle;
	}
	public String getEntryBody() {
		return entryBody;
	}
	public void setEntryBody(String entryBody) {
		this.entryBody = entryBody;
	}
	public String getEntryID() {
		return entryID;
	}
	public void setEntryID(String entryID) {
		this.entryID = entryID;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
}
