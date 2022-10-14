package com.sus.repo;

import com.sus.domain.ScoreBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreBoardDao extends JpaRepository<ScoreBoard, String> {

}
