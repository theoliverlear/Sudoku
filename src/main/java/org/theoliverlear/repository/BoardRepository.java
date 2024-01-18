package org.theoliverlear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.theoliverlear.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board getBoardById(Long boardId);
    @Query("SELECT b.timer FROM Board b WHERE b.id = ?1")
    String getTimeById(@Param("id") Long boardId);
}
