package ch.zh.fd.ksta.esrTool;

public class ESRTool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Arguments arguments = new Arguments(args);
			ESRProcessor processor = new ESRProcessor(arguments);
			processor.process();
		}
		catch(IllegalArgumentException ex) {
			printUsage();
		}
	}

	private static void printUsage() {
		System.out.println("Liest ein ESR File und erzeugt daraus ein CSV mit den einzelnen Buchungen");
		System.out.println("und ein CSV mit der Statistik.");
		System.out.println();
		System.out.println("java -jar ESRTool.jar [@][Laufwerk:][Pfad][Dateiname]");
		System.out.println();
		System.out.println("  [Laufwerk:][Pfad][Dateiname]");
		System.out.println("             Bezeichnet Laufwerk, Verzeichnis und Datei.");
		System.out.println("             Alternativ kann mit '@' eine Datei mit einer");
		System.out.println("             Liste von Files angegeben werden");
		System.out.println();
	}
}
