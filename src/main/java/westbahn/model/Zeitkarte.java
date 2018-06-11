package westbahn.model;

import westbahn.Zahlung;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Entity
public class Zeitkarte extends Ticket {

	@Column
	private Date gueltigAb;

	@Enumerated(
			EnumType.STRING
	)
	@Column
	private ZeitkartenTyp typ;

	public Zeitkarte() {}

	public Zeitkarte(Strecke strecke, Zahlung zahlung, Date gueltigAb, ZeitkartenTyp typ) {
		this.gueltigAb = gueltigAb;
		this.typ = typ;
		this.strecke = strecke;
		this.zahlung = zahlung;
	}

	public Date getGueltigAb() {
		return gueltigAb;
	}

	public void setGueltigAb(Date gueltigAb) {
		this.gueltigAb = gueltigAb;
	}

	public ZeitkartenTyp getTyp() {
		return typ;
	}

	public void setTyp(ZeitkartenTyp typ) {
		this.typ = typ;
	}
}
