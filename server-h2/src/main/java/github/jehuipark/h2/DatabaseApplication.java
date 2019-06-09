package github.jehuipark.h2;

import java.sql.SQLException;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by JeHuiPark on 2019-06-09
 * Blog   : https://jehuipark.github.io
 * Github : https://github.com/JeHuiPark
 */
@Slf4j
@ConfigurationProperties("h2")
@SpringBootApplication
public class DatabaseApplication {

  @Getter @Setter
  private int dbPort = 9092;
  @Getter @Setter
  private String internalDbName = "oauth";
  @Getter @Setter
  private String externalDbName = "oauth";

  public static void main(String[] args){
    SpringApplication.run(DatabaseApplication.class, args);
  }

  /**
   * @see org.h2.server.TcpServer
   * @return
   * @throws SQLException
   */
  @PostConstruct
  public void dataSource() throws SQLException {
    Server server = adviceRun(dbPort, externalDbName, internalDbName, FilePath.absolute);
    if(server.isRunning(true)){
      log.info("server run success");
    }
    log.info("h2 server url = {}", server.getURL());
  }

  /**
   *
   * @param port
   * @param externalDbName
   * @param dbname
   * @param db_store
   * @return
   * @throws SQLException
   */
  private Server adviceRun(int port, String externalDbName, String dbname, FilePath db_store) throws SQLException {
    return Server.createTcpServer(
        "-tcp",
        "-tcpAllowOthers",
        "-ifNotExists",
        "-tcpPort", port+"", "-key", externalDbName, db_store.value2(dbname)).start();
  }

  private Server defaultRun(int port) throws SQLException {
    return Server.createTcpServer(
        "-tcp",
        "-tcpAllowOthers",
        "-ifNotExists",
        "-tcpPort", port+"").start();
  }

  enum FilePath {
    absolute("~/"),
    relative("./");
    String prefix;
    FilePath(String prefix){
      this.prefix = prefix;
    }
    public String value2(String dbname){
      return prefix + dbname;
    }
  }
}
