package ch.zh.fd.ksta.esrTool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ESRProcessor {

	private Arguments arguments;

	public ESRProcessor(Arguments arguments) {
		this.arguments = arguments;
	}

	public void process() {
		try {
			File destinationAllFile = new File((new File(arguments.getSourceFileNames().get(0))).getParentFile() + "\\Rechnungen.csv");
			BufferedWriter allWriter = new BufferedWriter(new FileWriter(destinationAllFile));
			for(String sourceFileName : arguments.getSourceFileNames()) {
				processFile(sourceFileName, allWriter);
			}
			allWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processFile(String sourceFileName, BufferedWriter allWriter) {

		System.out.println("Processing " + sourceFileName);

		BufferedReader sourceReader = null;
		BufferedWriter destinationWriter = null;
		try {
			sourceReader = new BufferedReader(new FileReader(sourceFileName));
			destinationWriter = new BufferedWriter(new FileWriter(sourceFileName + ".csv"));

			destinationWriter.write(ESRGutschriftsRecord.csvHeader());
			destinationWriter.newLine();
			allWriter.write(ESRGutschriftsRecord.csvHeader());
			allWriter.newLine();

			String line;
			ESRTotalRecord totalRecord = null;
			ESRStatistik statistics = new ESRStatistik();
			while((line = sourceReader.readLine()) != null) {
				ESRRecord record = ESRRecordFactory.createESRRecord(line);
				if(record instanceof ESRGutschrift) {
					destinationWriter.write(((ESRGutschriftsRecord)record).toCSV());
					allWriter.write(((ESRGutschriftsRecord)record).toCSV());
					statistics.addGutschrift(((ESRGutschriftsRecord)record).getBetrag());
				}
				else if(record instanceof ESRStorno) {
					destinationWriter.write(((ESRGutschriftsRecord)record).toCSV());
					allWriter.write(((ESRGutschriftsRecord)record).toCSV());
					statistics.addStorno(((ESRGutschriftsRecord)record).getBetrag());
				}
				else if(record instanceof ESRKorrektur) {
					destinationWriter.write(((ESRGutschriftsRecord)record).toCSV());
					allWriter.write(((ESRGutschriftsRecord)record).toCSV());
					statistics.addKorrektur(((ESRGutschriftsRecord)record).getBetrag());
				}
				else if(record instanceof ESRTotalRecord) {
					if(totalRecord == null) {
						totalRecord = (ESRTotalRecord)record;
					}
					else {
						throw new IllegalArgumentException("Zwei Total Records vorhanden");
					}
				}
				destinationWriter.newLine();
				allWriter.newLine();
			}
			destinationWriter.close();

			if(totalRecord == null) {
				throw new IllegalArgumentException("Kein Total Record vorhanden");
			}
			else {
				destinationWriter = new BufferedWriter(new FileWriter(sourceFileName + ".total.csv"));
				destinationWriter.write(ESRTotalRecord.csvHeader());
				destinationWriter.newLine();
				destinationWriter.write(totalRecord.toCSV());
				destinationWriter.newLine();
				destinationWriter.close();
			}

			destinationWriter = new BufferedWriter(new FileWriter(sourceFileName + ".statistik.csv"));
			destinationWriter.write("Typ;Anzahl;Summe");
			destinationWriter.newLine();
			destinationWriter.write("Gutschriften;" + statistics.getNOfGutschrift() + ";" + statistics.getSumOfGutschrift());
			destinationWriter.newLine();
			destinationWriter.write("Stornos;" + statistics.getNOfStorno() + ";" + statistics.getSumOfStorno());
			destinationWriter.newLine();
			destinationWriter.write("Korrekturen;" + statistics.getNOfKorrektur() + ";" + statistics.getSumOfKorrektur());
			destinationWriter.newLine();
			destinationWriter.write("Summe;" + statistics.getNOfTotal() + ";" + statistics.getSumTotal());
			destinationWriter.newLine();
			destinationWriter.close();

		} catch (FileNotFoundException e) {
			System.out.println("File konnte nicht geöffnet werden (" + e.getMessage() + ")");
		} catch (IOException e) {
			System.out.println("Fehler beim Filezugriff (" + e.getMessage() + ")");
		} catch (Exception e) {
			System.out.println("Fehler beim formatieren");
			e.printStackTrace();
		}
		finally {
			try {
				destinationWriter.close();
			} catch (IOException e) {}
		}
	}

}
