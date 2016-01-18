package theater.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import theater.domein.Klant;

/**
 * Deze klasse is verantwoordelijk voor de klantadministratie.
 */
public class KlantDAO {
  private static final String SQLLEESKLANT = "select id, naam, telefoon from Klant where naam = ? AND telefoon = ?";
  private static final String SQLINSERTKLANT = "insert into Klant values (null,?, ?)";

  /**
   * Geeft een klantobject met de gegeven naam en het gegeven telefoonnummer;
   * als de klant nieuw is, wordt die meteen naar de database geschreven.
   * 
   * @param naam
   *          naam van de klant
   * @param telefoon
   *          telefoonnummer van de klant
   * @return een klant me de ingevoerde naam en telefoon.
   * @throws TheaterException
   */
  public synchronized static Klant getKlant(String naam, String telefoon)
      throws TheaterException {
    Klant klant = leesKlantUitDB(naam, telefoon);
    if (klant == null) {
      klant = nieuweKlant(naam, telefoon);
    }
    return klant;
  }

  // private hulpmethoden

  /**
   * Leest klant met gegeven naam uit de database.
   * 
   * @param naam
   *          naam van te zoeken klant
   * @param telefoon
   *          telefoonnummer van te zoeken klant
   * @return de klant of null wanneer klant niet is gevonden
   * @throws TheaterException
   */
  private static Klant leesKlantUitDB(String naam, String telefoon)
      throws TheaterException {
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection con = pool.getConnection();
    PreparedStatement prepLeesKlant = null;
    Klant klant = null;
    try {
      prepLeesKlant = con.prepareStatement(SQLLEESKLANT);
      prepLeesKlant.setString(1, naam);
      prepLeesKlant.setString(2, telefoon);
      ResultSet res = prepLeesKlant.executeQuery();
      if (res.next()) {
        klant = new Klant(res.getInt("id"), naam, telefoon);
      }
      res.close();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new TheaterException("Fout bij lezen klant");
    } finally {
      closePreparedStatement(prepLeesKlant);
      pool.freeConnection(con);
    }
    return klant;
  }

  /**
   * Voegt een nieuwe klant toe aan theater.
   * 
   * @param naam
   *          naam van de nieuwe klant
   * @param telefoon
   *          telefoonnummer van de nieuwe klant
   * @return de klant of null als het niet lukte 
   * @throws TheaterException         
   */
  private static Klant nieuweKlant(String naam, String telefoon)
      throws TheaterException {
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection con = pool.getConnection();
    PreparedStatement prepInsertKlant = null;
    Klant k = null;
    try {
      prepInsertKlant = con.prepareStatement(SQLINSERTKLANT,
          Statement.RETURN_GENERATED_KEYS);
      prepInsertKlant.setString(1, naam);
      prepInsertKlant.setString(2, telefoon);
      con.setAutoCommit(false);
      int gelukt = prepInsertKlant.executeUpdate();
      if (gelukt == 1) {
        con.commit();
      } else {
        con.rollback();
        throw new TheaterException("Fout bij schrijven klant");
      }
      ResultSet rs = prepInsertKlant.getGeneratedKeys();
      rs.next();
      k = new Klant(rs.getInt(1), naam, telefoon);
      con.setAutoCommit(true);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new TheaterException("Fout bij schrijven klant");
    } finally {
      closePreparedStatement(prepInsertKlant);
      pool.freeConnection(con);
    }
    return k;
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
