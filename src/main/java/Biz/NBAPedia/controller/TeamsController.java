package Biz.NBAPedia.controller;

import Biz.NBAPedia.dto.CommentDto;
import Biz.NBAPedia.entity.PlayerSeasons;
import Biz.NBAPedia.entity.TeamComments;
import Biz.NBAPedia.entity.TeamSeasonStats;
import Biz.NBAPedia.entity.Teams;
import Biz.NBAPedia.entity.Users;
import Biz.NBAPedia.service.PlayerSeasonsService;
import Biz.NBAPedia.service.TeamCommentsService;
import Biz.NBAPedia.service.TeamSeasonStatsService;
import Biz.NBAPedia.service.TeamsService;
import Biz.NBAPedia.service.UsersService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//@RestController
@Controller
public class TeamsController {


  @Autowired
  private TeamSeasonStatsService teamSeasonStatsService;
  @Autowired
  private PlayerSeasonsService playerSeasonsService;
  @Autowired
  private TeamCommentsService teamCommentsService;
  @Autowired
  private UsersService usersService;
  @Autowired
  private TeamsService teamsService;
  @Autowired
  private MainController mainController;


  @GetMapping("/teamById/{id}")
  public Teams findTeamById(@PathVariable int id) {
    Teams team = teamsService.findTeamById(id);
    return teamsService.findTeamById(id);
  }

  @GetMapping("/teamdetails/{id}")
  public String getTeamDetails(@PathVariable int id, Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    Teams team = teamsService.findTeamById(id);
    List<TeamSeasonStats> teamSeasonStats = teamSeasonStatsService.findByTeam(team);
    List<TeamComments> commentsList = teamCommentsService.findCommentsForTeam(team);
    List<PlayerSeasons> playerSeasonsList =
        playerSeasonsService.findPlayerSeasonsByTeamAndSeason(team);
    String imagePath = team.getShortName() + ".png";
    model.addAttribute("team", team);
    model.addAttribute("imgPath", imagePath);
    model.addAttribute("teamSeasonStats", teamSeasonStats);
    model.addAttribute("playerSeasonsList", playerSeasonsList);
    model.addAttribute("commentsList", commentsList);
    model.addAttribute("commentDto", new CommentDto());
    model.addAttribute("username", username);
    return "teamdetails";
  }


  @PostMapping("/addTeamComment/{id}")
  public String addTeamComment(@PathVariable int id, @ModelAttribute("commentDto")
      CommentDto commentDto, Model model) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    Teams team = teamsService.findTeamById(id);
    Users user = usersService.findByUsername(currentPrincipalName);
    TeamComments comment = new TeamComments(team, date,user, commentDto.getContent());
    comment = teamCommentsService.save(comment);
    return getTeamDetails(id, model);
  }


  @GetMapping("/teams")
  public String findAll(Model model) {
    List<Teams> teams = teamsService.findAll();
    model.addAttribute("teams", teams);
    return "teams";
  }

  @PostMapping("/updateTeamComment/{id}")
  public String updateTeamComment(@PathVariable int id, Model model, @ModelAttribute("commentDto")
      CommentDto commentDto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    return mainController.accountSetting(model);
  }

  @GetMapping("/deleteTeamComment/{id}")
  public String deleteTeamComment(@PathVariable int id,  Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    TeamComments teamComment = teamCommentsService.findTeamCommentById(id);
    teamCommentsService.deleteTeamComment(teamComment);
    return mainController.accountSetting(model);
  }



}
