package net.toyland.store.controllers.toys;

import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import net.toyland.store.persistence.toys.Toy;
import net.toyland.store.persistence.toys.ToyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/toys")
public class ToyController {

    private final ToyRepository toyRepository;

    public ToyController(ToyRepository toyRepository) {
        this.toyRepository = toyRepository;
    }

    @GetMapping("/{id}")
        public ResponseEntity<ToyResponse> getToyById(@PathVariable Long id) {
            Optional<Toy> toyOptional = toyRepository.findById(id);
            if (toyOptional.isPresent()) {
                Toy toy = toyOptional.get();
                ToyResponse toyResponse = mapToToyResponse(toy);
                return ResponseEntity.ok(toyResponse);
            }
            return ResponseEntity.notFound().build();
        }
    
    private ToyResponse mapToToyResponse(Toy toy) {
        return new ToyResponse(toy.getId(), toy.getName(), toy.getBrand(), toy.getPrice());
    }
}

