package com.sqli.stories.repository;

import com.sqli.stories.entities.Story;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.query.Param;

        import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {
    @Query("SELECT s FROM Story s WHERE s.jiraKey=:id")
    Story getStoryByJiraKey(@Param("id") Long jiraKey);

    @Query("SELECT s FROM Story s WHERE s.title like :keyword")
    List<Story> searchByKeyword(@Param("keyword") String keyword);


}
