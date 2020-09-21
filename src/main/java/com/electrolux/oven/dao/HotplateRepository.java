package com.electrolux.oven.dao;

import com.electrolux.oven.entity.Hotplate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotplateRepository extends JpaRepository<Hotplate, Long> {

  Optional<Hotplate> findByOvenIdAndHotplateNumber(Long ovenId, Integer hotplateNumber);

}
