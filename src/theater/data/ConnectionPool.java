package theater.data;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Singletonklasse die verbinding maakt met de database en verbindingen opvraagt
 * en vrijgeeft.
 * 
 * @author Medewerker OUNL
 * 
 */
public final class ConnectionPool {
  // constante voor de JNDI databasenaam
  private static final String DBNAAM = "java:/comp/env/jdbc/TheaterWas";

  // de enige instantie van deze klasse
  private static ConnectionPool pool = null;

  // de datasource
  private DataSource dataSource = null;

  /**
   * De (private) constructor maakt verbinding met de database.
   * 
   * @throws TheaterException
   */
  private ConnectionPool() throws TheaterException {
    try {
      Context initCtx = new InitialContext();
      dataSource = (DataSource) initCtx.lookup(DBNAAM);
    } catch (NamingException e) {
      e.printStackTrace();
      throw new TheaterException(
          "Door een ernstige systeemfout kan niet voldaan worden aan uw verzoek; "
              + "(de database met voorstellingen kan niet worden geopend");
    }
  }

  /**
   * Geeft de enige instantie terug
   * 
   * @return de enige instantie van ConnectionPool
   * @throws TheaterException
   */
  public static synchronized ConnectionPool getInstance()
      throws TheaterException {
    if (pool == null) {
      pool = new ConnectionPool();
    }
    return pool;
  }

  /**
   * Vraagt om een beschikbare connectie uit de pool die wordt bijgehouden door
   * de datasource
   * 
   * @return een connectie met de datasource
   * @throws TheaterException
   */
  public Connection getConnection() throws TheaterException {
    try {
      Connection con = dataSource.getConnection();
      con.setAutoCommit(true);
      return con;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new TheaterException(
          "Door een ernstige systeemfout kan niet voldaan worden aan uw verzoek; "
              + "(verbinding met de database faalt");
    }
  }

  /**
   * Geeft een connectie terug aan de pool
   * 
   * @param c
   *          de connectie die vrijgegeven kan worden
   * @throws TheaterException
   */
  public void freeConnection(Connection c) throws TheaterException {
    try {
      c.close();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new TheaterException("Er is een ernstige systeemfout opgetreden;"
          + "(verbinding met de database kan niet worden gesloten)");
    }
  }

}
