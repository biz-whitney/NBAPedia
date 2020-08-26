package Biz.NBAPedia.repository;

import Biz.NBAPedia.entity.Players;
import Biz.NBAPedia.entity.UserViewHistory;
import Biz.NBAPedia.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserViewHistoryRepository extends JpaRepository<UserViewHistory, Integer> {
  UserViewHistory findByUserAndPlayer(Users user, Players player);

}
