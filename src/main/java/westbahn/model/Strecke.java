package westbahn.model;

import javax.persistence.*;

@Entity
public class Strecke {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;

	@ManyToOne
	@JoinColumn
	private Bahnhof start;

	@ManyToOne
	@JoinColumn
	private Bahnhof bahnhof;

	@ManyToOne
	@JoinColumn
	private Bahnhof ende;

	public Strecke() {}

	public Strecke(Bahnhof start, Bahnhof ende) {
		this.start = start;
		this.ende = ende;
	}

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public Bahnhof getStart() {
		return start;
	}

	public void setStart(Bahnhof start) {
		this.start = start;
	}

	public Bahnhof getBahnhof() {
		return bahnhof;
	}

	public void setBahnhof(Bahnhof bahnhof) {
		this.bahnhof = bahnhof;
	}

	public Bahnhof getEnde() {
		return ende;
	}

	public void setEnde(Bahnhof ende) {
		this.ende = ende;
	}
}
