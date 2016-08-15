package ch.zh.fd.ksta.esrTool;

public class ESRGutschriftsRecord extends ESRRecord {

	private String transaktionsart;
	private String teilnehmernummer;
	private String referenznummer;
	private String betrag;
	private String aufgabereferenz;
	private String aufgabedatum;
	private String verarbeitungsdatum;
	private String valutadatum;
	private String mikrofilmnummer;
	private String rejectcode;
	private String reserve;
	private String einzahlungstaxe;

	private int[] fieldEndPos = {3, 12, 39, 49, 59, 65, 71, 77, 86, 87, 96, 100}; 

	public ESRGutschriftsRecord(String line) {
		transaktionsart = line.substring(0, fieldEndPos[0]);
		teilnehmernummer = line.substring(fieldEndPos[0], fieldEndPos[1]);
		referenznummer = line.substring(fieldEndPos[1], fieldEndPos[2]);
		betrag = line.substring(fieldEndPos[2], fieldEndPos[3]);
		aufgabereferenz = line.substring(fieldEndPos[3], fieldEndPos[4]);
		aufgabedatum = line.substring(fieldEndPos[4], fieldEndPos[5]);
		verarbeitungsdatum = line.substring(fieldEndPos[5], fieldEndPos[6]);
		valutadatum = line.substring(fieldEndPos[6], fieldEndPos[7]);
		mikrofilmnummer = line.substring(fieldEndPos[7], fieldEndPos[8]);
		rejectcode = line.substring(fieldEndPos[8], fieldEndPos[9]);
		reserve = line.substring(fieldEndPos[9], fieldEndPos[10]);
		einzahlungstaxe = line.substring(fieldEndPos[10], fieldEndPos[11]);
	}

	public static String csvHeader() {
		String csv = "\"Transaktionsart\";" +
		           "\"Teilnehmernummer\";" + 
		           "\"Referenznummer\";" + 
		           "\"Betrag\";" + 
		           "\"Aufgabereferenz\";" + 
		           "\"Aufgabedatum\";" + 
		           "\"Verarbeitungsdatum\";" + 
		           "\"Valutadatum\";" + 
		           "\"Mikrofilmnummer\";" + 
		           "\"Rejectcode\";" + 
		           "\"Reserve\";" + 
		           "\"Einzahlungstaxe\"";
		return csv;
	}

	public String toCSV() {
		String csv = "\"" + transaktionsart + "\";" +
		           "\"" + teilnehmernummer + "\";" + 
		           "\"" + referenznummer + "\";" + 
		           "\"" + betrag.substring(0, 8) + "." + betrag.substring(8, 10) + "\";" + 
		           "\"" + aufgabereferenz + "\";" + 
		           "\"" + aufgabedatum + "\";" + 
		           "\"" + verarbeitungsdatum + "\";" + 
		           "\"" + valutadatum + "\";" + 
		           "\"" + mikrofilmnummer + "\";" + 
		           "\"" + rejectcode + "\";" + 
		           "\"" + reserve + "\";" + 
		           "\"" + einzahlungstaxe.substring(0, 2) + "." + einzahlungstaxe.substring(2, 4) + "\"";
		return csv;
	}

	public String getTransaktionsart() {
		return transaktionsart;
	}

	public String getTeilnehmernummer() {
		return teilnehmernummer;
	}

	public String getReferenznummer() {
		return referenznummer;
	}

	public String getBetrag() {
		return betrag;
	}

	public String getAufgabereferenz() {
		return aufgabereferenz;
	}

	public String getAufgabedatum() {
		return aufgabedatum;
	}

	public String getVerarbeitungsdatum() {
		return verarbeitungsdatum;
	}

	public String getValutadatum() {
		return valutadatum;
	}

	public String getMikrofilmnummer() {
		return mikrofilmnummer;
	}

	public String getRejectcode() {
		return rejectcode;
	}

	public String getReserve() {
		return reserve;
	}

	public String getEinzahlungstaxe() {
		return einzahlungstaxe;
	}

}
