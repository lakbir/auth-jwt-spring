package auth.jwt.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import auth.jwt.spring.models.Restaurant;
import auth.jwt.spring.models.User;
import auth.jwt.spring.payload.response.MessageResponse;
import auth.jwt.spring.repository.RestaurantRepository;
import auth.jwt.spring.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/addRestaurant")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> addRestaurant(@Valid @RequestBody Restaurant restaurant) {
		if (restaurantRepository.existsByName(restaurant.getName())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Le nom du restaurant est déjà pris !"));
		}
		
		if (restaurantRepository.existsByEmail(restaurant.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Restaurant Email is already taken!"));
		}
		
		// create a new restaurant
		restaurantRepository.save(restaurant);
		return ResponseEntity.ok(new MessageResponse("Restaurant registered successfully!"));
	}
	
	
	@GetMapping("/myRestaurants")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Restaurant>> myRestaurant(@RequestParam(required = false, name="idUser") long id) {
		System.out.println("Id User : " + id);
        try {
            List<Restaurant> restaurants = new ArrayList<Restaurant>();
            
            restaurantRepository.findByIdUser(id).forEach(restaurants::add);

            if (restaurants.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(restaurants, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
}
