package org.theoliverlear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.theoliverlear.entity.Board;
import org.theoliverlear.entity.User;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board getBoardById(Long boardId);
}
