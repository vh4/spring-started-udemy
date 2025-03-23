package com.mvc.crud.repository;

import com.mvc.crud.entity.Members;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface MembersRepository extends JpaRepository<Members, String> {

    Optional<Members> findByUserId(String userId);
    boolean existsByUserId(String userId);

}

