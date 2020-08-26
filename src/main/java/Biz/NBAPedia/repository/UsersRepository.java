package Biz.NBAPedia.repository;

import Biz.NBAPedia.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
  public Users findByUsername(String username);
}
