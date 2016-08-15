package ch.zh.fd.ksta.esrTool;

public class ESRRecordFactory {

	public static ESRRecord createESRRecord(String line) {
		String belegart = line.substring(0, 1);
		String zahlungsart = line.substring(1, 2);
		String typ = line.substring(2, 3);

		if(("9".equals(belegart)) &&  ("9".equals(zahlungsart))  &&  ("9".equals(typ))) {
			return new ESRPositiveTotalRecord(line); 
		}
		else if(("9".equals(belegart)) &&  ("9".equals(zahlungsart))  &&  ("5".equals(typ))) {
			return new ESRNegativeTotalRecord(line); 
		}
		else if(("0".equals(belegart)) || ("1".equals(belegart))) {
			if(("0".equals(zahlungsart)) || ("1".equals(zahlungsart))) {
				if("2".equals(typ)) {
					return new ESRGutschrift(line); 
				}
				else if("5".equals(typ)) {
					return new ESRStorno(line); 
				}
				else if("8".equals(typ)) {
					return new ESRKorrektur(line); 
				}
				else {
					throw new IllegalArgumentException("Ungültiger Typ (" + typ + ")");
				}
			}
			else {
				throw new IllegalArgumentException("Ungültige Zahlungsart (" + zahlungsart + ")");
			}
		}
		else {
			throw new IllegalArgumentException("Ungültige Belegart (" + belegart + ")");
		}
	}

}
