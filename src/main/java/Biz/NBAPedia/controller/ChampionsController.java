package Biz.NBAPedia.controller;


import Biz.NBAPedia.entity.Champions;
import Biz.NBAPedia.entity.Teams;
import Biz.NBAPedia.service.ChampionsService;
import Biz.NBAPedia.service.TeamsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChampionsController {

  @Autowired
  public ChampionsService championsService;
  @Autowired
  private TeamsService teamsService;

  @GetMapping("/championById/{id}")
  public Champions findById(@PathVariable int id) {
    return championsService.findById(id);
  }

  @GetMapping("/championByTeam/{id}")
  public List<Champions> findByTeam(@PathVariable int id) {
    Teams team = teamsService.findTeamById(id);
    return championsService.findByTeam(team);
  }

}
