package westbahn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

public class Zeitkarte extends Ticket {

	private Date gueltigAb;

	private ZeitkartenTyp typ;

}
