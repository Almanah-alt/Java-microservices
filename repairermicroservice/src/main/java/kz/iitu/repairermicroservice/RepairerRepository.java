package kz.iitu.repairermicroservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairerRepository extends JpaRepository<Repairer,Long> {
    Repairer findByPhone(String phone);
    Repairer findByUsername(String username);
}
