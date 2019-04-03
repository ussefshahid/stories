package com.sqli.stories.services.impl;

import com.sqli.stories.entities.Sprint;
import com.sqli.stories.entities.StorySprint;
import com.sqli.stories.helpers.factory.SprintStoryFactory;
import com.sqli.stories.helpers.payload.StoryHistoric;
import com.sqli.stories.repository.StorySprintRepository;
import com.sqli.stories.services.StorySprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultStorySprintService implements StorySprintService {

    @Autowired
    private StorySprintRepository storySprintRepository;

    @Override
    public StorySprint addStorySprint(StorySprint storySprint) {
        return storySprintRepository.save(storySprint);
    }

    @Override
    public List<StoryHistoric> getSprintsByStoryKey(Long jiraKey) {

         List<StorySprint> storySprintList =storySprintRepository.getSprintsByStoryKey(jiraKey);
         List<StoryHistoric> storyHistoric=storySprintList.stream()
                                                          .map(s->{return SprintStoryFactory.createStoryHistoric(s.getSprint().getNumero(),s.getAssignementDate(),s.getStory().getTeam().getName());})
                                                          .collect(Collectors.toList());
         return storyHistoric;
    }
}
