package br.com.santander.infra.db.repository;

import br.com.santander.infra.db.model.Devices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevicesRepo extends JpaRepository<Devices, String> {
}