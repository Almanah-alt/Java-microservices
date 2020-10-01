package kz.iitu.centerrepservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CenterRepRepository extends JpaRepository<CenterRep,Long> {
    CenterRep findByRepId(Long id);
}
