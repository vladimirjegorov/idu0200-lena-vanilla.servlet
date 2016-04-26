package ee.ttu.idu0200.db;

import ee.ttu.idu0200.model.Team;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class TeamDao {

  private static Logger LOG = getLogger(TeamDao.class);

  public static final String SELECT_ALL_TEAMS = "SELECT id, title, members_amount, description FROM team";
  public static final String SELECT_TEAM_BY_ID = "SELECT id, title, members_amount, description FROM team WHERE id = ?";
  public static final String UPDATE_TEAM = "UPDATE team SET title=?, members_amount=?, description=? WHERE id=?";

  @Resource(name = "jdbc/dataSource")
  private DataSource dataSource;


  public Team findById(long id) {
    try {
      try (Connection conn = dataSource.getConnection()) {
        PreparedStatement sql = conn.prepareStatement(SELECT_TEAM_BY_ID);
        sql.setLong(1, id);

        LOG.info("findById: id = " + id);

        ResultSet set = sql.executeQuery();
        if (set.next()) {
          return toTeam(set);
        }
        return null;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Team> findAll() {
    ArrayList<Team> teams = new ArrayList<>();
    try {
      try (Connection conn = dataSource.getConnection()) {
        PreparedStatement sql = conn.prepareStatement(SELECT_ALL_TEAMS);
        ResultSet set = sql.executeQuery();
        while (set.next()) {
          teams.add(toTeam(set));
        }
        LOG.info("findAll: size = " + teams.size());
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return teams;
  }

  public void update(Team team) {
    try {
      try (Connection conn = dataSource.getConnection()) {
        PreparedStatement sql = conn.prepareStatement(UPDATE_TEAM);
        sql.setString(1, team.getTitle());
        sql.setInt(2, team.getMembersAmount());
        sql.setString(3, team.getDescription());
        sql.setLong(4, team.getId());

        sql.executeUpdate();
        LOG.info("update: id = " + team.getId());
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private Team toTeam(ResultSet set) throws SQLException {
    Team team = new Team();
    team.setId(set.getLong("id"));
    team.setTitle(set.getString("title"));
    team.setMembersAmount(set.getInt("members_amount"));
    team.setDescription(set.getString("description"));
    return team;
  }


}
