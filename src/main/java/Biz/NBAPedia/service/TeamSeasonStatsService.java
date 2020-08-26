package Biz.NBAPedia.service;


import Biz.NBAPedia.entity.TeamSeasonStats;
import Biz.NBAPedia.entity.Teams;
import Biz.NBAPedia.repository.TeamSeasonStatsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamSeasonStatsService {

  @Autowired
  private TeamSeasonStatsRepository teamSeasonStatsRepository;

  public TeamSeasonStats findById(int id) {
    return teamSeasonStatsRepository.findById(id).orElse(null);
  }

  public List<TeamSeasonStats> findByTeam(Teams team) {
    return teamSeasonStatsRepository.findByTeam(team);
  }

}
