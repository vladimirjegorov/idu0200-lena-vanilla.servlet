package ee.ttu.idu0200.db;

import ee.ttu.idu0200.model.Team;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TeamDaoTest {

  @InjectMocks
  private TeamDao teamDao;
  @Mock
  private DataSource dataSourceMock;

  private Connection connectionMock;
  private PreparedStatement preparedStatementMock;

  @Before
  public void setUp() throws SQLException {
    connectionMock = mock(Connection.class);
    when(dataSourceMock.getConnection()).thenReturn(connectionMock);
    preparedStatementMock = mock(PreparedStatement.class);
    when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
  }

  @Test
  public void findByIdReturnsTeamWhenTeamFound() throws Exception {
    ResultSet resultSetMock = mock(ResultSet.class);
    when(resultSetMock.next()).thenReturn(true);
    when(resultSetMock.getLong("id")).thenReturn(123L);
    when(resultSetMock.getString("title")).thenReturn("Title");
    when(resultSetMock.getInt("members_amount")).thenReturn(11);
    when(resultSetMock.getString("description")).thenReturn("Description");

    when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

    Team team = teamDao.findById(123L);

    verify(preparedStatementMock).setLong(1, 123L);
    assertEquals(123L, team.getId());
    assertEquals("Title", team.getTitle());
    assertEquals(11, team.getMembersAmount());
    assertEquals("Description", team.getDescription());
  }

  @Test
  public void findByIdReturnsNullWhenTeamNotFound() throws Exception {
    ResultSet resultSetMock = mock(ResultSet.class);
    when(resultSetMock.next()).thenReturn(false);

    when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

    Team team = teamDao.findById(123L);

    verify(preparedStatementMock).setLong(1, 123L);
    assertNull(team);
  }

  @Test
  public void findAllReturnsAListOfTeams() throws Exception {
    ResultSet resultSetMock = mock(ResultSet.class);
    when(resultSetMock.next()).thenReturn(true, true, false);
    when(resultSetMock.getLong("id")).thenReturn(123L, 124L);
    when(resultSetMock.getString("title")).thenReturn("Title1", "Title2");
    when(resultSetMock.getInt("members_amount")).thenReturn(11, 12);
    when(resultSetMock.getString("description")).thenReturn("Description1", "Description2");

    when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

    List<Team> teamList = teamDao.findAll();

    Team team0 = teamList.get(0);
    assertEquals(123L, team0.getId());
    assertEquals("Title1", team0.getTitle());
    assertEquals(11, team0.getMembersAmount());
    assertEquals("Description1", team0.getDescription());

    Team team1 = teamList.get(1);
    assertEquals(124L, team1.getId());
    assertEquals("Title2", team1.getTitle());
    assertEquals(12, team1.getMembersAmount());
    assertEquals("Description2", team1.getDescription());
  }

  @Test
  public void updatesTeam() throws Exception {
    Team team = new Team();
    team.setId(123L);
    team.setTitle("Title");
    team.setMembersAmount(11);
    team.setDescription("Description");

    teamDao.update(team);
    verify(preparedStatementMock).setString(1, "Title");
    verify(preparedStatementMock).setInt(2, 11);
    verify(preparedStatementMock).setString(3, "Description");
    verify(preparedStatementMock).setLong(4, 123L);
    verify(preparedStatementMock).executeUpdate();
    verifyNoMoreInteractions(preparedStatementMock);
  }
}