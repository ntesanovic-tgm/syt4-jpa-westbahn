package westbahn.model;

import javax.persistence.*;

public class Preisstaffelung {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private static long serialVersionUID;

	@Column
	private float grossGepaeck = 1.02f;

	@Column
	private float fahrrad = 1.05f;

	@Column
	private int zeitkarteWoche = 8;

	@Column
	private int zeitkarteMonat = 25;

	@Column
	private int zeitkarteJahr = 250;

	private static Preisstaffelung instance;

	public static Preisstaffelung getInstance() {
		return null;
	}

	private Preisstaffelung() {

	}

	public Preisstaffelung(float grossGepaeck, float fahrrad, int zeitkarteWoche, int zeitkarteMonat, int zeitkarteJahr) {
		this.grossGepaeck = grossGepaeck;
		this.fahrrad = fahrrad;
		this.zeitkarteWoche = zeitkarteWoche;
		this.zeitkarteMonat = zeitkarteMonat;
		this.zeitkarteJahr = zeitkarteJahr;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		Preisstaffelung.serialVersionUID = serialVersionUID;
	}

	public float getGrossGepaeck() {
		return grossGepaeck;
	}

	public void setGrossGepaeck(float grossGepaeck) {
		this.grossGepaeck = grossGepaeck;
	}

	public float getFahrrad() {
		return fahrrad;
	}

	public void setFahrrad(float fahrrad) {
		this.fahrrad = fahrrad;
	}

	public int getZeitkarteWoche() {
		return zeitkarteWoche;
	}

	public void setZeitkarteWoche(int zeitkarteWoche) {
		this.zeitkarteWoche = zeitkarteWoche;
	}

	public int getZeitkarteMonat() {
		return zeitkarteMonat;
	}

	public void setZeitkarteMonat(int zeitkarteMonat) {
		this.zeitkarteMonat = zeitkarteMonat;
	}

	public int getZeitkarteJahr() {
		return zeitkarteJahr;
	}

	public void setZeitkarteJahr(int zeitkarteJahr) {
		this.zeitkarteJahr = zeitkarteJahr;
	}

	public static void setInstance(Preisstaffelung instance) {
		Preisstaffelung.instance = instance;
	}
}
