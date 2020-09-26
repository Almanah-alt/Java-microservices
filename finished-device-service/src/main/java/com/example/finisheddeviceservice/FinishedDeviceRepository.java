package com.example.finisheddeviceservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FinishedDeviceRepository extends JpaRepository<FinishedDevices,Long> {

    FinishedDevices findByDeviceId(Long id);
}
