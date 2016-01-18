package theater.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import theater.domein.Plaats;
import theater.domein.Theater;
import theater.domein.Voorstelling;

/**
 * Deze klasse is verantwoordelijk voor het inlezen van voorstellingen en het
 * schrijven van bezettingen.
 */
public class VoorstellingDAO {
  private static final String LEESVOORSTELLINGEN = "select id, datum, naam  from Voorstelling where datum > curdate()";
  private static final String SQLUITVERKOCHT = "select count(*) from Bestelregel where voorstelling = ?";
  private static final String SQLINSERTBESTELREGEL = "insert into Bestelregel  "
      + "values(null,?, ?, ?, ?)";
  private static final String SQLGETBEZET = "select rijnummer,stoelnummer from Bestelregel where voorstelling = ? order by rijnummer,stoelnummer";
  public static final char BEZET = 'B';
  public static final char VRIJ = 'V';

  /**
   * Leest voorstellingen in (voor zover in de toekomst), zonder bezettingen.
   * 
   * @return een lijst met voorstellingen in de toekomst
   * @throws TheaterException
   *           als het inlezen mislukt
   */
  public static ArrayList<Voorstelling> getVoorstellingen()
      throws TheaterException {
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection con = pool.getConnection();
    PreparedStatement prepLeesVoorstellingen = null;
    ArrayList<Voorstelling> voorstellingen = new ArrayList<Voorstelling>();
    try {
      prepLeesVoorstellingen = con.prepareStatement(LEESVOORSTELLINGEN);
      ResultSet res = prepLeesVoorstellingen.executeQuery();
      while (res.next()) {
	    GregorianCalendar datum = new GregorianCalendar();
        datum.setTime(res.getDate(2));
        voorstellingen.add(new Voorstelling(res.getInt(1), res.getString(3),
            datum));
      }
      res.close();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new TheaterException(
          "Opzoeken voorstellingsdata in database is mislukt");
    } finally {
      closePreparedStatement(prepLeesVoorstellingen);
      pool.freeConnection(con);
    }
    return voorstellingen;
  }

  /**
   * Test of een voorstelling is uitverkocht
   * 
   * @param datum
   *          de datum van de voorstelling
   * @return true als alle plaatsen bezet zijn en anders false
   * @throws TheaterException
   */
  public static boolean isUitverkocht(int id) throws TheaterException {
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection con = pool.getConnection();
    PreparedStatement prepIsUitverkocht = null;
    boolean isUitverkocht = false;
    try {
      prepIsUitverkocht = con.prepareStatement(SQLUITVERKOCHT);
      prepIsUitverkocht.setInt(1, id);
      ResultSet res = prepIsUitverkocht.executeQuery();
      res.next();
      isUitverkocht = (res.getInt(1) == Theater.AANTALPERRIJ
          * Theater.AANTALRIJEN);
      res.close();
      return isUitverkocht;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new TheaterException(
          "Bepalen of voorstelling is uitverkocht, is mislukt");
    } finally {
      closePreparedStatement(prepIsUitverkocht);
      pool.freeConnection(con);
    }
  }

  /**
   * 
   * @param voorstelling
   * @return een arraylist met
   * @throws TheaterException
   */

  private static String[] getBezet(int voorstelling) throws TheaterException {
    StringBuffer[] zaalsb = new StringBuffer[Theater.AANTALRIJEN + 1];
    int rijnr, stoelnr, index = 0;
    // zaalsb vullen met allemaal vrije plaatsen
    for (index = 1; index <= Theater.AANTALRIJEN; index++) {
      zaalsb[index] = new StringBuffer();
      for (int i = 0; i < Theater.AANTALPERRIJ; i++) {
        zaalsb[index].append(VRIJ);
      }
    }
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection con = pool.getConnection();
    PreparedStatement prepGetBezet = null;
    try {
      prepGetBezet = con.prepareStatement(SQLGETBEZET);
      prepGetBezet.setInt(1, voorstelling);
      ResultSet res = prepGetBezet.executeQuery();
      index = 1;
      while (res.next()) {
        rijnr = res.getInt(1);
        stoelnr = res.getInt(2);
        zaalsb[rijnr].setCharAt(stoelnr - 1, BEZET);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new TheaterException(
          "Opzoeken bezette plaatsen in database mislukt");
    } finally {
      closePreparedStatement(prepGetBezet);
      pool.freeConnection(con);
    }
    String[] zaal = new String[Theater.AANTALRIJEN + 1];
    zaal[0] = "";
    for (int i = 1; i < Theater.AANTALRIJEN + 1; i++) {
      zaal[i] = new String(zaalsb[i]);
    }
    return zaal;
  }

  /**
   * 
   * @param voorstelling
   *          : id van voorstelling
   * @param klant
   *          : id van klant
   * @param aantal
   *          : aantal kaarten
   * @param db
   *          boolean die aangeeft of er naar de datbase geschreven moet worden
   * @return ArrayList<Plaats> als er plaatsen gevonden zijn, anders null
   * @throws TheaterException
   */
  public static synchronized ArrayList<Plaats> reserveer(int voorstelling,
      int klant, int aantal, boolean db) throws TheaterException {
    if (aantal > Theater.AANTALPERRIJ) {
      return null; // niet meer plaatsen beschikbaar dan Theater.AANTALPERRIJ
    }
    ArrayList<Plaats> plaatsen = null;
    String zoekstr = maakZoekString(aantal);
    String[] zaal = getBezet(voorstelling);
    boolean klaar = false;
    int rij = 0;
    int index = 0;
    for (rij = 1; rij < zaal.length && !klaar; rij++) {
      index = zaal[rij].indexOf(zoekstr);
      klaar = (index != -1);
    }
    if (klaar) { // komt voor : op rij zijn er aantal stoelen gevonden te
                 // beginnenbij index+1, omdat stoelnummering bij 1 begint
      plaatsen = new ArrayList<Plaats>();
      for (int i = 0; i < aantal; i++) {
        Plaats p = new Plaats(rij - 1, index + 1 + i);
        plaatsen.add(p);
      }
      if (db) {
        schrijfWeg(voorstelling, klant, aantal, plaatsen);
      }
    }
    return plaatsen;
  }

  private static void schrijfWeg(int voorstelling, int klant, int aantal,
      ArrayList<Plaats> plaatsen) throws TheaterException {
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection con = pool.getConnection();
    PreparedStatement prepInsertBezetting = null;
    try {
      con.setAutoCommit(false);
      prepInsertBezetting = con.prepareStatement(SQLINSERTBESTELREGEL);
      prepInsertBezetting.setInt(1, voorstelling);
      prepInsertBezetting.setInt(4, klant);
      int n = 0;
      for (int i = 0; i < aantal; i++) {
        prepInsertBezetting.setInt(2, plaatsen.get(i).getRijnummer());
        prepInsertBezetting.setInt(3, plaatsen.get(i).getStoelnummer());
        n = n + prepInsertBezetting.executeUpdate();
      }
      if (n == aantal) {
        con.commit();
      } else {
        con.rollback();
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new TheaterException(
          "Bestelde plaatsen wegschrijven naar datbase mislukt");
    } finally {
      closePreparedStatement(prepInsertBezetting);
      pool.freeConnection(con);
    }
  }

  private static String maakZoekString(int aantal) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < aantal; i++) {
      sb.append(VRIJ);
    }
    return new String(sb);
  }

  /**
   * Sluit een prepared statement af.
   * 
   * @param ps
   * @throws TheaterException
   */
  private static void closePreparedStatement(PreparedStatement ps)
      throws TheaterException {
    try {
      if (ps != null) {
        ps.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new TheaterException(
          "Door een systeemfout bij het sluiten van een databaseopdracht "
              + "kan niet voldaan worden aan dit verzoek");
    }
  }

}
