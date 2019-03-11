package com.sqli.stories.dao;

import com.sqli.stories.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
    @Query("SELECT s FROM Sprint s WHERE s.numero=:numero")
    Sprint getSprintByNumero(@Param("numero") Long numero);
}
