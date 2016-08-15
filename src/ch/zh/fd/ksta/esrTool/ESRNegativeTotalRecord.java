package ch.zh.fd.ksta.esrTool;

public class ESRNegativeTotalRecord extends ESRTotalRecord {

	public ESRNegativeTotalRecord(String line) {
		super(line);
	}

	public String getSignedDecimalBetrag() {
        return "-" + getBetrag().substring(0, 10) + "." + getBetrag().substring(10, 12); 
	}

}
