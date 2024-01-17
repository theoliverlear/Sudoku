package org.theoliverlear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.theoliverlear.entity.Board;
import org.theoliverlear.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User getUserByUsername(@Param("username") String username);
    User getUserById(Long id);
    @Query("SELECT u.currentBoard.id FROM User u WHERE u.username = ?1")
    Long getBoardIdByUsername(@Param("username") String username);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = ?1 AND u.currentBoard IS NOT NULL")
    boolean boardExistsByUsername(@Param("username") String username);
}
