package ucsal.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ucsal.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

  boolean existsByUsername(String username);

  AppUser findByUsername(String username);

  @Transactional
  void deleteByUsername(String username);

  @Query("SELECT u FROM AppUser u WHERE u.id IN (:ids)")     // 2. Spring JPA In cause using @Query
  List<AppUser> finByUsersIds(@Param("ids") List<Integer> ids);
  
}
