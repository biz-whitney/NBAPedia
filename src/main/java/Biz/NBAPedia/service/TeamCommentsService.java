package Biz.NBAPedia.service;


import Biz.NBAPedia.entity.TeamComments;
import Biz.NBAPedia.entity.Teams;
import Biz.NBAPedia.entity.Users;
import Biz.NBAPedia.repository.TeamCommentsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamCommentsService {

  @Autowired
  private TeamCommentsRepository teamCommentsRepository;

  public TeamComments findTeamCommentById(int id) {
    return teamCommentsRepository.findById(id).orElse(null);
  }

  public List<TeamComments> findCommentsForTeam(Teams team) {
    return teamCommentsRepository.findAllByTeam(team);
  }

  public TeamComments save(TeamComments comment) {
    return teamCommentsRepository.save(comment);
  }

  public List<TeamComments> findTeamCommentsByUser(Users user) {
    return teamCommentsRepository.findTeamCommentsByUser(user);
  }

  public void deleteTeamComment(TeamComments teamComment) {
    teamCommentsRepository.delete(teamComment);
  }

}
