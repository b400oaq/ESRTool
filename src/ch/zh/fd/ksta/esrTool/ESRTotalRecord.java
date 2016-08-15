package ch.zh.fd.ksta.esrTool;

public abstract class ESRTotalRecord extends ESRRecord {

private String transaktionsart;
	private String teilnehmernummer;
	private String sortierschluessel;
	private String betrag;
	private String anzahlTransaktionen;
	private String erstellungsdatum;
	private String einzahlungstaxe;
	private String reserve;

	private int[] fieldEndPos = {3, 12, 39, 51, 63, 69, 78, 87, 100}; 

	public ESRTotalRecord(String line) {
		transaktionsart = line.substring(0, fieldEndPos[0]);
		teilnehmernummer = line.substring(fieldEndPos[0], fieldEndPos[1]);
		sortierschluessel = line.substring(fieldEndPos[1], fieldEndPos[2]);
		betrag = line.substring(fieldEndPos[2], fieldEndPos[3]);
		anzahlTransaktionen = line.substring(fieldEndPos[3], fieldEndPos[4]);
		erstellungsdatum = line.substring(fieldEndPos[4], fieldEndPos[5]);
		einzahlungstaxe = line.substring(fieldEndPos[5], fieldEndPos[6]);
		reserve = line.substring(fieldEndPos[6], fieldEndPos[7]);
	}

	public static String csvHeader() {
		String csv = "\"Transaktionsart\";" +
		           "\"Teilnehmernummer\";" + 
		           "\"Sortierschluessel\";" + 
		           "\"Betrag\";" + 
		           "\"AnzahlTransaktionen\";" + 
		           "\"Erstellungsdatum\";" + 
		           "\"Einzahlungstaxe\";" + 
		           "\"Reserve\"";
		return csv;
	}

	public String toCSV() {
		String csv = "\"" + getTransaktionsart() + "\";" +
		           "\"" + getTeilnehmernummer() + "\";" + 
		           "\"" + getSortierschluessel() + "\";" + 
		           "\"" + getSignedDecimalBetrag() + "\";" + 
		           "\"" + getAnzahlTransaktionen() + "\";" + 
		           "\"" + getErstellungsdatum() + "\";" + 
		           "\"" + getEinzahlungstaxe().substring(0, 7) + "." + getEinzahlungstaxe().substring(7, 9) + "\";" +
		           "\"" + getReserve() + "\"";
		return csv;
	}

	public String getTransaktionsart() {
		return transaktionsart;
	}

	public String getTeilnehmernummer() {
		return teilnehmernummer;
	}

	public String getSortierschluessel() {
		return sortierschluessel;
	}

	public String getBetrag() {
		return betrag;
	}

	public abstract String getSignedDecimalBetrag();

	public String getAnzahlTransaktionen() {
		return anzahlTransaktionen;
	}

	public String getErstellungsdatum() {
		return erstellungsdatum;
	}

	public String getEinzahlungstaxe() {
		return einzahlungstaxe;
	}

	public String getReserve() {
		return reserve;
	}

	public int[] getFieldEndPos() {
		return fieldEndPos;
	}

}
