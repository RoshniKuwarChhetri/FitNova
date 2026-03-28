package com.project.fitnova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.fitnova.model.Activity;
@Repository
public interface ActivityRepository extends JpaRepository<Activity,String>{
}
