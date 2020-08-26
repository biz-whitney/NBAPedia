package Biz.NBAPedia.repository;

import Biz.NBAPedia.entity.TeamSeasonStats;
import Biz.NBAPedia.entity.Teams;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamSeasonStatsRepository extends JpaRepository<TeamSeasonStats,
    Integer> {
  List<TeamSeasonStats> findByTeam(Teams team);
}
