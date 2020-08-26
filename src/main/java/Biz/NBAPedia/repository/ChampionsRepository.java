package Biz.NBAPedia.repository;

import Biz.NBAPedia.entity.Champions;
import Biz.NBAPedia.entity.Teams;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionsRepository extends JpaRepository<Champions, Integer> {
  public List<Champions> findByTeam(Teams team);

}
