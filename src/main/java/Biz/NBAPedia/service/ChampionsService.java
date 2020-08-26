package Biz.NBAPedia.service;

import Biz.NBAPedia.entity.Champions;
import Biz.NBAPedia.entity.Teams;
import Biz.NBAPedia.repository.ChampionsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampionsService {

  @Autowired
  private ChampionsRepository championsRepository;


  public Champions findById(int id) {
    return championsRepository.findById(id).orElse(null);
  }

  public List<Champions> findByTeam(Teams team) {
    return championsRepository.findByTeam(team);
  }


}
