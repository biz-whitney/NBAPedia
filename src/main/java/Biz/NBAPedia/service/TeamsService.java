package Biz.NBAPedia.service;

import Biz.NBAPedia.entity.Teams;
import Biz.NBAPedia.repository.TeamsRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamsService {

  @Autowired
  private TeamsRepository teamsRepository;

  public Teams findTeamById(int id) {
    return teamsRepository.findById(id).orElse(null);
  }

  public List<Teams> findAll() {
    return teamsRepository.findAll();
  }

  public  List<String> findTeamNames() {
    List<Teams> teamsList = teamsRepository.findAll();
    List<String> teamNameList = new ArrayList<>();
    for (Teams team: teamsList) {
      teamNameList.add(team.getShortName());
    }
    return teamNameList;
  }

//  public List<String> findTeamNames() {
//    List<Teams> teamsRepository.find
//  }


}
