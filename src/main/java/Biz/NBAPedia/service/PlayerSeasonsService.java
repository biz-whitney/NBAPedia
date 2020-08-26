package Biz.NBAPedia.service;


import Biz.NBAPedia.entity.PlayerSeasons;
import Biz.NBAPedia.entity.Teams;
import Biz.NBAPedia.repository.PlayerSeasonsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerSeasonsService {

  @Autowired
  private PlayerSeasonsRepository playerSeasonsRepository;

  public List<PlayerSeasons> findPlayerSeasonsByTeamAndSeason(Teams team) {
    return playerSeasonsRepository.findPlayerSeasonsByTeamAndSeason(team, "2019-20");
  }

}
