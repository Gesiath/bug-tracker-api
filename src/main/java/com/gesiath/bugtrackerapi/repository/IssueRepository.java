package com.gesiath.bugtrackerapi.repository;

import com.gesiath.bugtrackerapi.entity.Issue;
import com.gesiath.bugtrackerapi.enumerator.IssueStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IssueRepository extends JpaRepository<Issue, UUID> {

    Page<Issue> findByProjectId(UUID projectId, Pageable pageable);
    long countByProjectId(UUID projectId);
    long countByProjectIdAndIssueStatus(UUID projectId, IssueStatus status);

}
