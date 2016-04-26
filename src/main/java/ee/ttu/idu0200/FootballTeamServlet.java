package ee.ttu.idu0200;

import ee.ttu.idu0200.controller.TeamController;
import ee.ttu.idu0200.controller.TeamListController;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.math.NumberUtils.isNumber;
import static org.apache.logging.log4j.LogManager.getLogger;

@WebServlet({"/team/s"})
public class FootballTeamServlet extends HttpServlet {

  private static Logger LOG = getLogger(FootballTeamServlet.class);

  @Inject
  private TeamListController teamListController;

  @Inject
  private TeamController teamController;

  @Override
  public void init() throws ServletException {
    LOG.info("FootballTeamServlet.init() : I was created");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String idParameter = req.getParameter("id");
    String viewName = "";

    if (isNotEmpty(idParameter) && isNumber(idParameter)) {
      viewName = teamController.findById(req, resp);
    } else {
      viewName = teamListController.findAll(req, resp);
    }
    getServletContext().getRequestDispatcher(viewName).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String viewName = "";

    String action = req.getParameter("action");
    if ("save".equals(action)) {
      viewName = teamController.save(req, resp);
    }

    getServletContext().getRequestDispatcher(viewName).forward(req, resp);
  }
}
