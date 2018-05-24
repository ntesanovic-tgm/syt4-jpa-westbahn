package westbahn.model;

import westbahn.Zahlung;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

public abstract class Ticket {

	@Id
	private long ID;

	protected Strecke strecke;

	protected Zahlung zahlung;

}
