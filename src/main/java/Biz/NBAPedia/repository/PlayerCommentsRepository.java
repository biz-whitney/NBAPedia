package Biz.NBAPedia.repository;

import Biz.NBAPedia.entity.PlayerComments;
import Biz.NBAPedia.entity.Players;
import Biz.NBAPedia.entity.Users;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


@Transactional
public interface PlayerCommentsRepository extends JpaRepository<PlayerComments, Integer> {
  List<PlayerComments> findPlayerCommentsByUser(Users user);
  List<PlayerComments> findPlayerCommentsByPlayer(Players player);
}
