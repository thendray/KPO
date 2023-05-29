package ru.thendray.authorizationapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.thendray.authorizationapi.entities.SessionEntity;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
}
