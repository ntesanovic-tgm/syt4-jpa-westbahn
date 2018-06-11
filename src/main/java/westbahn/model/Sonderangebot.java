package westbahn.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Sonderangebot {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;

	@Column
	private int kontingent = 999;

	@Column
	private Date startZeit;

	@Column
	private int dauer = 12;

	@Column
	private float preisNachlass = 0.5f;

	@OneToMany
	@JoinTable(
			name = "tickets",
			joinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_id")},
			inverseJoinColumns = { @JoinColumn(referencedColumnName = "id", name = "ticket_id") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Ticket> tickets;

	public Sonderangebot() {}

	public Sonderangebot(int kontingent, Date startZeit, int dauer, float preisNachlass) {
		this.kontingent = kontingent;
		this.startZeit = startZeit;
		this.dauer = dauer;
		this.preisNachlass = preisNachlass;
		this.tickets = new ArrayList<Ticket>();
	}

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public int getKontingent() {
		return kontingent;
	}

	public void setKontingent(int kontingent) {
		this.kontingent = kontingent;
	}

	public Date getStartZeit() {
		return startZeit;
	}

	public void setStartZeit(Date startZeit) {
		this.startZeit = startZeit;
	}

	public int getDauer() {
		return dauer;
	}

	public void setDauer(int dauer) {
		this.dauer = dauer;
	}

	public float getPreisNachlass() {
		return preisNachlass;
	}

	public void setPreisNachlass(float preisNachlass) {
		this.preisNachlass = preisNachlass;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
}
