package com.example.repositiry;

import com.example.entity.profile.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer>,
        PagingAndSortingRepository<ProfileEntity,Integer> {
    Optional<ProfileEntity> findByUsername(String username);
    Optional<ProfileEntity> findByUsernameAndPassword(String username,String password);
}
