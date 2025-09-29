package com.galaxon.Quiz.Connect.repository;

import com.galaxon.Quiz.Connect.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
