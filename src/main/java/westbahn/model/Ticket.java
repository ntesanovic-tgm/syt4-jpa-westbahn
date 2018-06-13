package westbahn.model;

import westbahn.Zahlung;

import javax.persistence.*;

@NamedQuery(
		name = "Ticket.TicketsWithoutReservation",
		query = "SELECT t FROM Benutzer b JOIN b.tickets t WHERE t.strecke.start=:start AND t.strecke.ende=:ende AND t.strecke NOT in (SELECT r.strecke FROM b.reservierungen r)"
		)

@Entity
public abstract class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;

	@ManyToOne
	@JoinColumn
	protected Strecke strecke;

	@Transient
	protected Zahlung zahlung;

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public Strecke getStrecke() {
		return strecke;
	}

	public void setStrecke(Strecke strecke) {
		this.strecke = strecke;
	}

	public Zahlung getZahlung() {
		return zahlung;
	}

	public void setZahlung(Zahlung zahlung) {
		this.zahlung = zahlung;
	}
}
