package Biz.NBAPedia.repository;

import Biz.NBAPedia.entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
public interface TeamsRepository extends JpaRepository<Teams, Integer> {


}
