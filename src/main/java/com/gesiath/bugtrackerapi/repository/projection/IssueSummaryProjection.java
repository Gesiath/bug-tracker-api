package com.gesiath.bugtrackerapi.repository.projection;

import com.gesiath.bugtrackerapi.enumerator.IssueStatus;
import com.gesiath.bugtrackerapi.enumerator.Priority;

import java.util.UUID;

public interface IssueSummaryProjection {

    UUID getId();

    String getTitle();

    String getDescription();

    IssueStatus getIssueStatus();

    Priority getPriority();

}
