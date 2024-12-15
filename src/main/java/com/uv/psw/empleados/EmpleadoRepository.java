
package com.uv.psw.empleados;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
    Optional<Empleado> findByCorreoE(String correoE);
    long countByRol(Rol rol);
}
