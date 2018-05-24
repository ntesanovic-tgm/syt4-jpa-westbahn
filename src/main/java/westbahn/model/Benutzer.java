package westbahn.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Benutzer {

	@Id
	private long ID;

	private String vorName;

	private String nachName;

	private String eMail;

	private String passwort;

	private String smsNummer;

	private Long verbuchtePraemienMeilen;

	private Ticket tickets;

//	@OneToMany
////(cascade = CascadeType.ALL, mappedBy = "comment_system", orphanRemoval = true)//(cascade=CascadeType.ALL)
//	@JoinTable(
//			name = "benutzer_reservierungen",
//			joinColumns = {@JoinColumn(referencedColumnName = "id")},
//			inverseJoinColumns = { @JoinColumn(referencedColumnName = "id") })
//	private Reservierung[] reservierungen;

	private Collection<Reservierung> reservierungen;
}
