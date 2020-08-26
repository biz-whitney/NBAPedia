package Biz.NBAPedia.repository;

import Biz.NBAPedia.entity.TeamComments;
import Biz.NBAPedia.entity.Teams;
import Biz.NBAPedia.entity.Users;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface TeamCommentsRepository extends JpaRepository<TeamComments, Integer> {
  List<TeamComments> findAllByTeam(Teams team);
  List<TeamComments> findTeamCommentsByUser(Users user);
}
