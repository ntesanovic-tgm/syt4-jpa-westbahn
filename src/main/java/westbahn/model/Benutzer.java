package westbahn.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NamedQuery(
		name = "Benutzer.monatskarten",
		query = "SELECT b FROM Benutzer b JOIN b.tickets t WHERE t.typ = 'MONATSKARTE'"
)

@Entity
public class Benutzer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;

	@Column
	private String vorName;

	@Column
	private String nachName;

	@Column
	private String eMail;

	@Column
	private String passwort;

	@Column
	private String smsNummer;

	@Column
	private Long verbuchtePraemienMeilen;

	@OneToMany
	@JoinTable(
			name = "benutzer_tickets",
			joinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_id")},
			inverseJoinColumns = { @JoinColumn(referencedColumnName = "id", name = "ticket_id") })
	private List<Ticket> tickets;

	@OneToMany
	@JoinTable(
			name = "benutzer_reservierungen",
			joinColumns = {@JoinColumn(referencedColumnName = "id", name = "user_id")},
			inverseJoinColumns = { @JoinColumn(referencedColumnName = "id", name = "reservierungs_id") })
	private List<Reservierung> reservierungen;

	public Benutzer() {}

	public Benutzer(String vorName, String nachName, String eMail, String passwort, String smsNummer) {
		this.vorName = vorName;
		this.nachName = nachName;
		this.eMail = eMail;
		this.passwort = passwort;
		this.smsNummer = smsNummer;
		this.verbuchtePraemienMeilen = verbuchtePraemienMeilen;
		this.tickets = new ArrayList<Ticket>();
		this.reservierungen = new ArrayList<Reservierung>();
	}

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public String getVorName() {
		return vorName;
	}

	public void setVorName(String vorName) {
		this.vorName = vorName;
	}

	public String getNachName() {
		return nachName;
	}

	public void setNachName(String nachName) {
		this.nachName = nachName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getSmsNummer() {
		return smsNummer;
	}

	public void setSmsNummer(String smsNummer) {
		this.smsNummer = smsNummer;
	}

	public Long getVerbuchtePraemienMeilen() {
		return verbuchtePraemienMeilen;
	}

	public void setVerbuchtePraemienMeilen(Long verbuchtePraemienMeilen) {
		this.verbuchtePraemienMeilen = verbuchtePraemienMeilen;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Reservierung> getReservierungen() {
		return reservierungen;
	}

	public void setReservierungen(List<Reservierung> reservierungen) {
		this.reservierungen = reservierungen;
	}

	public void addTicket(Ticket ticket){
		this.tickets.add(ticket);
	}

	public void removeTicket(Ticket ticket){
		this.tickets.remove(ticket);
	}

	public void addReservierung(Reservierung reservierung){
		this.reservierungen.add(reservierung);
	}

	public void removeReservierung(Reservierung reservierung){
		this.reservierungen.remove(reservierung);
	}

	@Override
	public String toString() {
		String tostring =  "Benutzer{ \n" +
				"	ID=" + ID + ",\n" +
				"	vorName = '" + vorName + "\', \n" +
				"	nachName = '" + nachName + "\', \n" +
				"	eMail = '" + eMail + "\', \n" +
				"	passwort = '" + passwort + "\', \n" +
				"	smsNummer = '" + smsNummer + "\', \n" +
				"	verbuchtePraemienMeilen = " + 0 + ",\n" +
				"	Tickets = {\n";
		for (Ticket ticket: tickets) {
			tostring += "		" + ticket.toString() + "\n";
		}
		tostring += "	}, \n";
		tostring += "	Reservierungen = { \n";
		for (Reservierung reservierung: reservierungen) {
			tostring += "		" + reservierung.toString() + "\n";
		}
		tostring += "} \n";
		return tostring;
	}
}
