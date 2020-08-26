package Biz.NBAPedia.service;


import Biz.NBAPedia.entity.PlayerComments;
import Biz.NBAPedia.entity.Players;
import Biz.NBAPedia.entity.Users;
import Biz.NBAPedia.repository.PlayerCommentsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerCommentsService {

  @Autowired
  private PlayerCommentsRepository playerCommentsRepository;

  public List<PlayerComments> findCommentsByUser(Users user) {
    return playerCommentsRepository.findPlayerCommentsByUser(user);
  }

  public List<PlayerComments> findCommentsByPlayer(Players player) {
    return playerCommentsRepository.findPlayerCommentsByPlayer(player);
  }

  public PlayerComments saveComment(PlayerComments playerComment) {
    return playerCommentsRepository.save(playerComment);
  }


}
