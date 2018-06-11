package westbahn.model;

import westbahn.Zahlung;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Einzelticket extends Ticket {

	@Enumerated(
		EnumType.STRING
	)
	@Column
	private TicketOption ticketOption;

	public Einzelticket() {}

	public Einzelticket(Strecke strecke, Zahlung zahlung, TicketOption ticketOption) {
		this.ticketOption = ticketOption;
		this.strecke = strecke;
		this.zahlung = zahlung;
	}

	public TicketOption getTicketOption() {
		return ticketOption;
	}

	public void setTicketOption(TicketOption ticketOption) {
		this.ticketOption = ticketOption;
	}
}
