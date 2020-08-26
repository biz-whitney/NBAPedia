package Biz.NBAPedia.controller;

import Biz.NBAPedia.dto.UsersDto;
import Biz.NBAPedia.entity.Players;
import Biz.NBAPedia.entity.Users;
import Biz.NBAPedia.repository.PlayerGameStatsRepository.SeasonStats;
import Biz.NBAPedia.service.PlayerGameStatsService;
import Biz.NBAPedia.service.PlayersService;
import Biz.NBAPedia.service.RecommendationService;
import Biz.NBAPedia.service.UsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UsersService usersService;

  @Autowired
  private PlayerGameStatsService playerGameStatsService;

  @Autowired
  private PlayersService playersService;

  @Autowired
  private RecommendationService recommendationService;

  @PostMapping("/addUser")
  public Users addUser(@RequestBody UsersDto user) {
    int x = 1;
    return usersService.save(user);
  }



  @GetMapping("/tt")
  public List<SeasonStats> playerSeasonStats() {
    List<SeasonStats> seasonStats = playerGameStatsService.findPlayerSeasonStats(3);
    return seasonStats ;
  }

  @GetMapping("/model/{id}")
  public List<Players> playerChampionships(@PathVariable int id) {
    List<Players> similarPlayers = recommendationService.getSimilarPlayers(id);
    return similarPlayers;
  }
}
