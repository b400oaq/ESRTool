package ch.zh.fd.ksta.esrTool;

import java.math.BigDecimal;

public class ESRStatistik {
	
	private static BigDecimal HUNDRED = new BigDecimal(100);

	int nOfGutschrift = 0;
	int nOfStorno = 0;
	int nOfKorrektur = 0;
	BigDecimal sOfGutschrift = new BigDecimal(0);
	BigDecimal sOfStorno = new BigDecimal(0);
	BigDecimal sOfKorrektur = new BigDecimal(0);

	public int getNOfGutschrift() {
		return nOfGutschrift;
	}

	public int getNOfStorno() {
		return nOfStorno;
	}

	public int getNOfKorrektur() {
		return nOfKorrektur;
	}

	public int getNOfTotal() {
		return nOfGutschrift + nOfStorno + nOfKorrektur;
	}

	public BigDecimal getSumOfGutschrift() {
		return sOfGutschrift;
	}

	public void addGutschrift(String amount) {
		sOfGutschrift = sOfGutschrift.add((new BigDecimal(amount)).divide(HUNDRED));
		nOfGutschrift++;
	}

	public BigDecimal getSumOfStorno() {
		return sOfStorno;
	}

	public void addStorno(String amount) {
		sOfStorno = sOfStorno.add(new BigDecimal(amount).divide(HUNDRED));
		nOfStorno++;
	}

	public BigDecimal getSumOfKorrektur() {
		return sOfKorrektur;
	}

	public void addKorrektur(String amount) {
		sOfKorrektur = sOfKorrektur.add(new BigDecimal(amount).divide(HUNDRED));
		nOfKorrektur++;
	}

	public BigDecimal getSumTotal() {
		return sOfGutschrift.subtract(sOfStorno).add(sOfKorrektur);
	}

}
