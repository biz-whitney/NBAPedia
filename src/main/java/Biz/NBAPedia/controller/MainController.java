package Biz.NBAPedia.controller;


import Biz.NBAPedia.dto.CommentDto;
import Biz.NBAPedia.dto.UsersDto;
import Biz.NBAPedia.entity.PlayerComments;
import Biz.NBAPedia.entity.TeamComments;
import Biz.NBAPedia.entity.Teams;
import Biz.NBAPedia.entity.Users;
import Biz.NBAPedia.service.PlayerCommentsService;
import Biz.NBAPedia.service.PlayerSeasonsService;
import Biz.NBAPedia.service.TeamCommentsService;
import Biz.NBAPedia.service.TeamSeasonStatsService;
import Biz.NBAPedia.service.TeamsService;
import Biz.NBAPedia.service.UsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

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
  private PlayerCommentsService playerCommentsService;


  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/")
  public String index(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    Users user = usersService.findByUsername(currentPrincipalName);
    model.addAttribute("user", user);
    return "index";
  }


  @GetMapping("/accountSettings")
  public String accountSetting(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    CommentDto commentDto = new CommentDto();
    UsersDto usersDto = new UsersDto();
    Users user = usersService.findByUsername(username);
    List<Teams> teams = teamsService.findAll();
    List<PlayerComments> playerCommentsList = playerCommentsService.findCommentsByUser(user);
    List<TeamComments>  teamCommentsList = teamCommentsService.findTeamCommentsByUser(user);
    if (!model.containsAttribute("msg")) {
      model.addAttribute("msd", "");
    }
    model.addAttribute("teams", teams);
    model.addAttribute("user", user);
    model.addAttribute("playerCommentsList", playerCommentsList);
    model.addAttribute("teamCommentsList", teamCommentsList);
    model.addAttribute("commentDto", commentDto);
    model.addAttribute("usersDto", usersDto);
    return "accountSettings";
  }


  @PostMapping("/user/update")
  public String updateUser(@ModelAttribute("usersDto") UsersDto usersDto, Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    usersDto.setUsername(username);
    Users user = usersService.updateUser(usersDto);
    model.addAttribute("msg", "Account updated");
    return accountSetting(model);
  }

}
