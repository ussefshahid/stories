package com.sqli.stories.repository;

import com.sqli.stories.entities.Sprint;
import com.sqli.stories.entities.Story;
import com.sqli.stories.entities.StorySprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StorySprintRepository extends JpaRepository<StorySprint,Long> {

    @Query("SELECT s FROM StorySprint s WHERE s.story.jiraKey=:jiraKey ORDER BY s.assignementDate")
    List<StorySprint> getSprintsByStoryKey(@Param("jiraKey") Long jiraKey);

    @Query("SELECT s.story FROM StorySprint s WHERE  s.sprint.numero=:sprintKey")
    List<Story> getStoriesBySprintKey(@Param("sprintKey") Long sprintKey);
}
