package com.example.woorinature.repository;

import com.example.woorinature.model.Event;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByDate(LocalDate date);

    @Override
    <S extends Event> Optional<S> findOne(Example<S> example);
}
