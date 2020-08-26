package Biz.NBAPedia.service;


import Biz.NBAPedia.entity.Players;
import Biz.NBAPedia.entity.UserViewHistory;
import Biz.NBAPedia.entity.Users;
import Biz.NBAPedia.repository.UserViewHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserViewHistoryService {

  @Autowired
  private UserViewHistoryRepository userViewHistoryRepository;

  public void save(UserViewHistory history) {
    userViewHistoryRepository.save(history);
  }

  public void updateHistory(Users user, Players player) {
    UserViewHistory history = userViewHistoryRepository.findByUserAndPlayer(user, player);
    if (history == null) {
      history = new UserViewHistory(user, player, 1);
    } else {
      history.setViewCount(history.getViewCount() + 1);
    }
    save(history);
  }

}
