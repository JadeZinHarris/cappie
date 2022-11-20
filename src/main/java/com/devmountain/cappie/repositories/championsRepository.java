package com.devmountain.cappie.repositories;
import com.devmountain.cappie.entities.Champions;
import com.devmountain.cappie.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface championsRepository extends JpaRepository<Champions,Long>{
    List<Champions> findAllByUserEquals(User user);
}
