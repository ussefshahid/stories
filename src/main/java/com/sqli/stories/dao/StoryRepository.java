package com.sqli.stories.dao;

import com.sqli.stories.entities.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoryRepository extends JpaRepository<Story, Long> {
    @Query("SELECT s FROM Story s WHERE s.jiraKey=:id")
    Story getStoryByJiraKey(@Param("id") Long jiraKey);
}
