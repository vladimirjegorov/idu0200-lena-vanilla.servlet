package ee.ttu.idu0200.converter;

import ee.ttu.idu0200.form.TeamForm;
import ee.ttu.idu0200.model.Team;

import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.lang3.time.DateUtils.parseDate;

public class TeamConverter {

  public TeamForm toTeamForm(HttpServletRequest request) {
    TeamForm teamForm = new TeamForm();
    teamForm.setId(request.getParameter("id"));
    teamForm.setTitle(request.getParameter("title"));
    teamForm.setMembersAmount(request.getParameter("membersAmount"));
    teamForm.setDescription(request.getParameter("description"));

    return teamForm;
  }

  public TeamForm toTeamForm(Team team) {
    TeamForm teamForm = new TeamForm();
    teamForm.setId(String.valueOf(team.getId()));
    teamForm.setTitle(team.getTitle());
    teamForm.setMembersAmount(String.valueOf(team.getMembersAmount()));
    teamForm.setDescription(team.getDescription());
    return teamForm;
  }

  public Team toTeam(TeamForm teamForm) {
    Team team = new Team();
    team.setId(Long.valueOf(teamForm.getId()));
    team.setTitle(teamForm.getTitle());

    String membersAmount = teamForm.getMembersAmount();
    team.setMembersAmount(Integer.valueOf(membersAmount));
    team.setDescription(teamForm.getDescription());

    return team;
  }
}
