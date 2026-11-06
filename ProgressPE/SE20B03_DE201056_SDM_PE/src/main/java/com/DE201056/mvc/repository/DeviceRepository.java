package com.DE201056.mvc.repository;

import com.DE201056.mvc.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
    List<Device> findAllByOrderByInstallationDateDesc();

    List<Device> findByDeviceNameContainingIgnoreCaseOrderByInstallationDesc(String deviceName);

    Optional<Device> findByDeviceCode(String deviceCode);
}
