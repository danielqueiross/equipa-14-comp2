package com.upt.pt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.upt.pt.entity.Estudante;
public interface EstudanteRepository extends JpaRepository<Estudante, Long> {}