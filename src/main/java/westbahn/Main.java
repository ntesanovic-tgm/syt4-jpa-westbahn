package westbahn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

import javax.persistence.*;

//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;

import westbahn.model.*;

public class Main {

//	private static final Logger log = Logger.getLogger(Main.class);
	private static EntityManagerFactory sessionFactory;
	@PersistenceContext
	private static EntityManager entitymanager;
	static SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");
	static SimpleDateFormat timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");

	private Main() {
		super();
	}

	public static void main(String[] args) {
//		log.setLevel(Level.ALL);
		try {
//			log.info("Starting \"Mapping Perstistent Classes and Associations\" (task1)");
			sessionFactory = Persistence.createEntityManagerFactory("westbahn");
			
			fillDB(entitymanager);
			task01();
//			log.info("Starting \"Working with JPA-QL and the Hibernate Criteria API\" (task2)");
			task02a();
			task02b();
			task02c();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void fillDB(EntityManager em) throws ParseException {
		em = sessionFactory.createEntityManager();

		Maestro maestro = new Maestro();
		Kreditkarte kreditkarte = new Kreditkarte();
		Praemienmeilen praemienmeilen = new Praemienmeilen();

		Benutzer benutzerChristoph = new Benutzer(
				"Christoph",
				"Pader-Barosch",
				"christoph.pader-barosch@hotmail.com",
				"hallo123",
				"+436801182120"
		);
		Benutzer benutzerMarc = new Benutzer(
				"Marc",
				"Rousavy",
				"mrousavy@hotmail.com",
				"hallo123",
				"+43680123456789"
		);
		Benutzer benutzerBen = new Benutzer(
				"David",
				"Hofer",
				"david.hofer@gmail.com",
				"hallo123",
				"+43680987654321"
		);

		Bahnhof bahnhofWienWestbahnhof = new Bahnhof(
				"Wien Westbahnhof",
				11,
				100,
				true
		);
		Bahnhof bahnhofWienHuetteldorf = new Bahnhof(
				"Wien Hütteldorf",
				25,
				50,
				false
		);
		Bahnhof bahnhofSanktPoelten = new Bahnhof(
				"Sankt Pölten",
				40,
				500,
				false
		);
		Bahnhof bahnhofAmstetten = new Bahnhof(
				"Amstetten",
				60,
				200,
				false
		);
		Bahnhof bahnhofLinz = new Bahnhof(
				"Linz",
				50,
				150,
				false
		);
		Bahnhof bahnhofWels = new Bahnhof(
				"Wels",
				20,
				400,
				false
		);
		Bahnhof bahnhofAttnangPuchheim = new Bahnhof(
				"Attnang-Puchheim",
				20,
				500,
				false
		);
		Bahnhof bahnhofSalzburg = new Bahnhof(
				"Salzburg",
				50,
				200,
				true
		);

		Strecke streckeWienWestbahnhofLinz = new Strecke(
				bahnhofWienWestbahnhof,
				bahnhofLinz
		);
		Strecke streckeWienHuetteldorfWels = new Strecke(
				bahnhofWienHuetteldorf,
				bahnhofWels
		);
		Strecke streckeSanktPoeltenSalzburg = new Strecke(
				bahnhofSanktPoelten,
				bahnhofSalzburg
		);
		Strecke streckeAmstettenWels = new Strecke(
				bahnhofAmstetten,
				bahnhofWels
		);
		Strecke streckeLinzAttnangPuchheim = new Strecke(
				bahnhofLinz,
				bahnhofAttnangPuchheim
		);

		Zug zug1 = new Zug(
				new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
				500, 50, 10,
				bahnhofWienHuetteldorf,
				bahnhofSalzburg
		);
		Zug zug2 = new Zug(
				new java.sql.Date(dateForm.parse("10.03.2018").getTime()),
				500, 50, 10,
				bahnhofWienHuetteldorf,
				bahnhofSalzburg
		);
		Zug zug3 = new Zug(
				new java.sql.Date(dateForm.parse("11.03.2018").getTime()),
				500, 50, 10,
				bahnhofWienHuetteldorf,
				bahnhofSalzburg
		);
		Zug zug4 = new Zug(
				new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
				500, 50, 10,
				bahnhofSalzburg,
				bahnhofWienWestbahnhof
		);
		Zug zug5 = new Zug(
				new java.sql.Date(dateForm.parse("10.03.2018").getTime()),
				500, 50, 10,
				bahnhofSalzburg,
				bahnhofWienWestbahnhof
		);


		Reservierung reservierung1 = new Reservierung(
				new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
				15,
				150,
				StatusInfo.ONTIME,
				streckeWienWestbahnhofLinz,
				zug1,
				benutzerChristoph,
				maestro
		);
		Reservierung reservierung2 = new Reservierung(
				new java.sql.Date(dateForm.parse("10.03.2018").getTime()),
				15,
				150,
				StatusInfo.DELAYED,
				streckeAmstettenWels,
				zug2,
				benutzerChristoph,
				maestro
		);
		Reservierung reservierung3 = new Reservierung(
				new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
				15,
				150,
				StatusInfo.ONTIME,
				streckeWienWestbahnhofLinz,
				zug2,
				benutzerBen,
				kreditkarte
		);
		Reservierung reservierung4 = new Reservierung(
				new java.sql.Date(dateForm.parse("11.03.2018").getTime()),
				15,
				150,
				StatusInfo.DELAYED,
				streckeSanktPoeltenSalzburg,
				zug3,
				benutzerMarc,
				maestro
		);
		Reservierung reservierung5 = new Reservierung(
				new java.sql.Date(dateForm.parse("12.03.2018").getTime()),
				15,
				150,
				StatusInfo.ONTIME,
				streckeWienWestbahnhofLinz,
				zug3,
				benutzerChristoph,
				maestro
		);

		Ticket einzelticketFahrrad = new Einzelticket(
				streckeAmstettenWels,
				maestro,
				TicketOption.FAHRRAD
		);
		Ticket einzelticketGrossgepaeck = new Einzelticket(
				streckeWienWestbahnhofLinz,
				kreditkarte,
				TicketOption.GROSSGEPAECK
		);
		Ticket zeitkarteWoche = new Zeitkarte(
				streckeLinzAttnangPuchheim,
				kreditkarte,
				new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
				ZeitkartenTyp.WOCHENKARTE
		);
		Ticket zeitkarteMonat = new Zeitkarte(
				streckeAmstettenWels,
				maestro,
				new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
				ZeitkartenTyp.MONATSKARTE
		);
		Ticket zeitkarteJahr = new Zeitkarte(
				streckeWienHuetteldorfWels,
				maestro,
				new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
				ZeitkartenTyp.JAHRESKARTE
		);

		Sonderangebot sonderangebot1 = new Sonderangebot(
				100,
				new java.sql.Date(dateForm.parse("10.03.2018").getTime()),
				200,
				0.5f
		);
		Sonderangebot sonderangebot2 = new Sonderangebot(
				1500,
				new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
				100,
				0.95f
		);
		Sonderangebot sonderangebot3 = new Sonderangebot(
				500,
				new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
				1000,
				0.75f
		);
		Sonderangebot sonderangebot4 = new Sonderangebot(
				1500,
				new java.sql.Date(dateForm.parse("09.03.2018").getTime()),
				200,
				0.95f
		);
		Sonderangebot sonderangebot5 = new Sonderangebot(
				999,
				new java.sql.Date(dateForm.parse("12.03.2018").getTime()),
				100,
				0.66f
		);

		// Transactions

		// Benutzer
		transact(em, benutzerChristoph);
		transact(em, benutzerMarc);
		transact(em, benutzerBen);

		// Bahnhöfe
		transact(em, bahnhofWienWestbahnhof);
		transact(em, bahnhofWienHuetteldorf);
		transact(em, bahnhofSanktPoelten);
		transact(em, bahnhofAmstetten);
		transact(em, bahnhofLinz);
		transact(em, bahnhofWels);
		transact(em, bahnhofAttnangPuchheim);
		transact(em, bahnhofSalzburg);


		// Strecken
		transact(em, streckeWienWestbahnhofLinz);
		transact(em, streckeWienHuetteldorfWels);
		transact(em, streckeSanktPoeltenSalzburg);
		transact(em, streckeAmstettenWels);
		transact(em, streckeLinzAttnangPuchheim);

		// Züge
		transact(em, zug1);
		transact(em, zug2);
		transact(em, zug3);
		transact(em, zug4);
		transact(em, zug5);

		// Reservierungen
		transact(em, reservierung1);
		transact(em, reservierung2);
		transact(em, reservierung3);
		transact(em, reservierung4);
		transact(em, reservierung5);

		// Tickets
		transact(em, einzelticketFahrrad);
		transact(em, einzelticketGrossgepaeck);
		transact(em, zeitkarteWoche);
		transact(em, zeitkarteMonat);
		transact(em, zeitkarteJahr);

		// Sonderangebote
		transact(em, sonderangebot1);
		transact(em, sonderangebot2);
		transact(em, sonderangebot3);
		transact(em, sonderangebot4);
		transact(em, sonderangebot5);

		em.getTransaction().begin();
		benutzerChristoph.setTickets(Collections.singletonList(zeitkarteMonat));
		benutzerChristoph.setReservierungen(Arrays.asList(reservierung1, reservierung2, reservierung5));
		em.getTransaction().commit();

		em.getTransaction().begin();
		benutzerMarc.setTickets(Collections.singletonList(einzelticketFahrrad));
		benutzerMarc.setReservierungen(Collections.singletonList(reservierung4));
		em.getTransaction().commit();

		em.getTransaction().begin();
		benutzerBen.setTickets(Collections.singletonList(zeitkarteJahr));
		benutzerBen.setReservierungen(Collections.singletonList(reservierung3));
		em.getTransaction().commit();
	}

	public static void transact(EntityManager em, Object o){
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
	}
	
	public static void task01() throws ParseException, InterruptedException {
		
		
	}
	public static void task02a() throws ParseException {

	}

	public static void task02b() throws ParseException {

	}

	public static void task02c() throws ParseException {

	}

}
