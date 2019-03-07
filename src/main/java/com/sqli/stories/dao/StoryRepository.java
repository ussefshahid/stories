package com.sqli.stories.dao;

import com.sqli.stories.entities.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
