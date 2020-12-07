package auth.jwt.spring.repository;

import java.util.List;

import auth.jwt.spring.models.Restaurant;
import auth.jwt.spring.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	Boolean existsByName(String name);
	
	Boolean existsByEmail(String email);
	
	List<Restaurant> findByIdUser(Long id);

}
