package com.galaxon.Quiz.Connect.repository;

import com.galaxon.Quiz.Connect.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByUsers_Id(Long userId);
}
