package com.example.AI_mailSortApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AI_mailSortApp.model.DbUpdateLog;

@Repository
public interface DbUpdateLogRepository extends JpaRepository<DbUpdateLog, Boolean> {
}
