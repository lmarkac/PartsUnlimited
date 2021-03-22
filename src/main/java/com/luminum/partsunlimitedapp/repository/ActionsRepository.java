package com.luminum.partsunlimitedapp.repository;

import com.luminum.partsunlimitedapp.model.Action;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service("actionsRepositoryBean")
public interface ActionsRepository extends CrudRepository<Action, Integer> {

    @Modifying
    @Query(value = "DELETE FROM actions WHERE id = ?1", nativeQuery = true)
    void deleteById(Integer id);

}
