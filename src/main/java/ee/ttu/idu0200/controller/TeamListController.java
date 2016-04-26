package ee.ttu.idu0200.controller;

import ee.ttu.idu0200.db.TeamDao;
import ee.ttu.idu0200.model.Team;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TeamListController {

  @Inject
  private TeamDao teamDao;

  public String findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    List<Team> teamList = teamDao.findAll();
    req.setAttribute("teamList", teamList);

    return "/WEB-INF/jsp/team/teamList.jsp";
  }
}
