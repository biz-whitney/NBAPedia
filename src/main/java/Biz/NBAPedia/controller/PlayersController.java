package Biz.NBAPedia.controller;

import Biz.NBAPedia.dto.CommentDto;
import Biz.NBAPedia.entity.PlayerAwards;
import Biz.NBAPedia.entity.PlayerComments;
import Biz.NBAPedia.entity.Players;
import Biz.NBAPedia.entity.Users;
import Biz.NBAPedia.repository.PlayerGameStatsRepository.SeasonStats;
import Biz.NBAPedia.repository.PlayersRepository.PlayerChampionships;
import Biz.NBAPedia.service.PlayerAwardsService;
import Biz.NBAPedia.service.PlayerCommentsService;
import Biz.NBAPedia.service.PlayerGameStatsService;
import Biz.NBAPedia.service.PlayersService;
import Biz.NBAPedia.service.RecommendationService;
import Biz.NBAPedia.service.UserViewHistoryService;
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


@Controller
public class PlayersController {

  @Autowired
  private PlayersService playersService;
  @Autowired
  private PlayerCommentsService playerCommentsService;
  @Autowired
  private UsersService usersService;
  @Autowired
  private PlayerGameStatsService playerGameStatsService;
  @Autowired
  private PlayerAwardsService playerAwardsService;
  @Autowired
  private UserViewHistoryService userViewHistoryService;
  @Autowired
  private RecommendationService recommendationService;



  @GetMapping("/players")
  public String findAllPlayers(Model model) {
    List<Players> players = playersService.findAllPlayers();
    model.addAttribute("players", players);
    return "players";
  }

  @GetMapping("/playerdetails/{id}")
  public String getPlayerDetails(@PathVariable int id, Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    Players player = playersService.findPlayerById(id);
    List<PlayerComments> commentsList = playerCommentsService.findCommentsByPlayer(player);
    List<SeasonStats> seasonStats = playerGameStatsService.findPlayerSeasonStats(id);
    List<PlayerAwards> awardsList = playerAwardsService.findAwardsForPlayer(player);
    List<PlayerChampionships> championshipsList = playersService.findPlayerChampionships(id);
//    List<AwardDto> awardDtoList = getAwardDto(awardsList);
//    List<Players> similarPlayers = recommendationService.getSimilarPlayers(id);
    updatePlayerView(username, id);
    model.addAttribute("player", player);
    model.addAttribute("commentsList", commentsList);
    model.addAttribute("commentDto", new CommentDto());
    model.addAttribute("username", username);
    model.addAttribute("seasonStats", seasonStats);
    model.addAttribute("awardsList", awardsList);
    model.addAttribute("championshipsList", championshipsList);
//    model.addAttribute("similarPlayers", similarPlayers);
//    model.addAttribute("awardDtoList", awardDtoList);
    return "playerdetails";
  }

  public void updatePlayerView(String username, int playerId) {
    if (username !="anonymousUser") {
      Users user = usersService.findByUsername(username);
      Players player = playersService.findPlayerById(playerId);
      userViewHistoryService.updateHistory(user, player);
    }
  }


  @PostMapping("/addPlayerComment/{id}")
  public String addPlayerComment(@PathVariable int id, @ModelAttribute("commentDto")
      CommentDto commentDto, Model model) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    Players player = playersService.findPlayerById(id);
    Users user = usersService.findByUsername(currentPrincipalName);
    PlayerComments comment = new PlayerComments(player, date,user, commentDto.getContent());
    comment = playerCommentsService.saveComment(comment);
    return getPlayerDetails(id, model);
  }

}
