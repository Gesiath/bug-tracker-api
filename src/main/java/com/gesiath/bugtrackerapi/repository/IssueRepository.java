package com.gesiath.bugtrackerapi.repository;

import com.gesiath.bugtrackerapi.entity.Issue;
import com.gesiath.bugtrackerapi.enumerator.IssueStatus;
import com.gesiath.bugtrackerapi.repository.projection.IssueSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IssueRepository extends JpaRepository<Issue, UUID>, JpaSpecificationExecutor<Issue> {

    Page<Issue> findByProjectId(UUID projectId, Pageable pageable);
    long countByProjectId(UUID projectId);
    long countByProjectIdAndIssueStatus(UUID projectId, IssueStatus status);

    @Query(""" 
    SELECT i FROM Issue i
    WHERE LOWER(i.title) LIKE LOWER(CONCAT('%', :query, '%'))
    OR LOWER(i.description) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    Page<Issue> search(String query, Pageable pageable);

    @Query("""
    SELECT
        i.id as id,
            i.title as title,
                i.description as description,
                    i.issueStatus as issueStatus,
                        i.priority as priority
                            FROM Issue i
    """)
    Page<IssueSummaryProjection> findAllProjected(Specification<Issue> spec, Pageable pageable);

}
