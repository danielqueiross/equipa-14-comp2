package com.upt.pt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.upt.pt.entity.Evento;
public interface EventoRepository extends JpaRepository<Evento, Long> {}