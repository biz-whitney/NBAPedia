package Biz.NBAPedia.controller;


import Biz.NBAPedia.entity.Games;
import Biz.NBAPedia.entity.Teams;
import Biz.NBAPedia.repository.PlayerGameStatsRepository.RosterPlayer;
import Biz.NBAPedia.service.GamesService;
import Biz.NBAPedia.service.PlayerGameStatsService;
import Biz.NBAPedia.service.TeamsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GamesController {
  @Autowired
  private GamesService gamesService;
  @Autowired
  private TeamsService teamsService;
  @Autowired
  private PlayerGameStatsService playerGameStatsService;

  @GetMapping("/games")
  public String findRecentGames(Model model) {
    List<Teams> teamsList = teamsService.findAll();
    List<Games> games = gamesService.findRecentGames("2016-17");
    model.addAttribute("games", games);
    model.addAttribute("teamsList", teamsList);
    return "games";
  }

  @GetMapping("/gamedetails/{gameId}")
  public String getGameDetails(@PathVariable int gameId, Model model) {
    Games game = gamesService.findById(gameId);
    Teams homeTeam = game.getHomeTeam();
    Teams awayTeam = game.getAwayTeam();
    List<RosterPlayer> awayRoster = playerGameStatsService.findRosterPlayerStats(gameId,
        awayTeam.getId());
    List<RosterPlayer> homeRoster = playerGameStatsService.findRosterPlayerStats(gameId,
        homeTeam.getId());
    String homeTeamImagePath = homeTeam.getShortName() + ".png";
    String awayTeamImagePath = awayTeam.getShortName() + ".png";
    model.addAttribute("homeTeamImagePath", homeTeamImagePath);
    model.addAttribute("awayTeamImagePath", awayTeamImagePath);
    model.addAttribute("homeTeam", homeTeam);
    model.addAttribute("awayTeam", awayTeam);
    model.addAttribute("awayRoster", awayRoster);
    model.addAttribute("homeRoster", homeRoster);
    model.addAttribute("game", game);
    return "gamedetails";
  }

}
