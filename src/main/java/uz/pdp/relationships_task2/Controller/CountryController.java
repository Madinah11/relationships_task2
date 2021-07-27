package uz.pdp.relationships_task2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.relationships_task2.Entitiy.Continent;
import uz.pdp.relationships_task2.Entitiy.Country;
import uz.pdp.relationships_task2.Entitiy.District;
import uz.pdp.relationships_task2.payload.CountryDto;
import uz.pdp.relationships_task2.repositary.ContinentRepositary;
import uz.pdp.relationships_task2.repositary.CountryRepositary;
import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {
    @Autowired
    CountryRepositary countryRepositary;
    @Autowired
    ContinentRepositary continentRepositary;

    @PostMapping("/country")
    public String addCountry(@RequestBody CountryDto countryDto) {
        Optional<Continent> byId = continentRepositary.findById(countryDto.getId());
        if (byId.isPresent()) {
            Continent continent = byId.get();
            Country country = new Country(null, countryDto.getCountryName(), continent);
            countryRepositary.save(country);
            return "Country added";
        }
        return "Country not found";
    }

    @GetMapping("/country")
    public List<Country> getCountry() {
        List<Country> countries = countryRepositary.findAll();
        return countries;
    }

    @GetMapping("/country/{id}")
    public Country getCountryById(@PathVariable Integer id) {
        Optional<Country> byId = countryRepositary.findById(id);
        if (byId.isPresent()) {
            Country country = byId.get();
            return country;
        }
        return new Country();
    }

    @DeleteMapping("/country/{id}")
    public String deleteCountry(@PathVariable Integer id) {
        countryRepositary.deleteById(id);
        return "Country deleted";
    }

    @PutMapping("/country/{id}")
    public String editCountry(@PathVariable Integer id, @RequestBody CountryDto countryDto) {
        Optional<Country> byId = countryRepositary.findById(id);
        if (byId.isPresent()) {
            Country country1 = byId.get();

            Optional<Continent> byId1 = continentRepositary.findById(countryDto.getId());
            if (byId1.isPresent()) {
                Continent continent = byId1.get();
                country1.setCountryName(countryDto.getCountryName());
                country1.setContinent(continent);
                countryRepositary.save(country1);

                return "Country edited";

            }

        }
        return "Country not found";

    }
}