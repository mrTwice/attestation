package ru.yampolskiy.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yampolskiy.usermicroservice.model.User;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью пользователя.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Находит пользователя по имени пользователя.
     * @param username Имя пользователя.
     * @return Найденный пользователь, если существует.
     */
    Optional<User> findUserByUsername(String username);

    /**
     * Удаляет пользователя по идентификатору.
     * @param id Идентификатор пользователя.
     * @return Удаленный пользователь, если существует.
     */
    Optional<User> deleteUserById(Long id);

    /**
     * Проверяет существование пользователя по имени пользователя.
     * @param username Имя пользователя.
     * @return true, если пользователь с таким именем существует, иначе false.
     */
    boolean existsUserByUsername(String username);
}
