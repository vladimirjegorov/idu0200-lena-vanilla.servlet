package ee.ttu.idu0200.controller;

import ee.ttu.idu0200.converter.TeamConverter;
import ee.ttu.idu0200.db.TeamDao;
import ee.ttu.idu0200.form.TeamForm;
import ee.ttu.idu0200.model.Team;
import ee.ttu.idu0200.validator.TeamFormValidator;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class TeamController {

  public static final String TEAM_JSP = "/WEB-INF/jsp/team/team.jsp";
  public static final String TEAM_NOT_FOUND_JSP = "/WEB-INF/jsp/team/teamNotFound.jsp";

  @Inject
  private TeamDao teamDao;

  @Inject
  private TeamFormValidator teamFormValidator;

  @Inject
  private TeamConverter teamConverter;

  public String findById(HttpServletRequest req, HttpServletResponse resp) {
    String idAttribute = req.getParameter("id");
    Long id = Long.valueOf(idAttribute);

    Team team = teamDao.findById(id);
    if (team == null) {
      return TEAM_NOT_FOUND_JSP;
    }

    TeamForm teamForm = teamConverter.toTeamForm(team);

    req.setAttribute("team", teamForm);
    return TEAM_JSP;
  }

  public String save(HttpServletRequest req, HttpServletResponse resp) {
    TeamForm teamForm = teamConverter.toTeamForm(req);
    Map<String, String> bindingResult = teamFormValidator.validate(teamForm);
    req.setAttribute("bindingResult", bindingResult);
    if (bindingResult.isEmpty()) {
      Team team = teamConverter.toTeam(teamForm);
      teamDao.update(team);
      Team teamReloaded = teamDao.findById(team.getId());
      TeamForm teamFormReloaded = teamConverter.toTeamForm(teamReloaded);
      req.setAttribute("team", teamFormReloaded);
      req.setAttribute("successMessage", "saveSuccess");
    } else {
      req.setAttribute("team", teamForm);
    }

    return TEAM_JSP;
  }
}
