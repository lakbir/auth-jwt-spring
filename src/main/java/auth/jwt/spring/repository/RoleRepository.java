package auth.jwt.spring.repository;

import java.util.Optional;

import auth.jwt.spring.models.ERole;
import auth.jwt.spring.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
