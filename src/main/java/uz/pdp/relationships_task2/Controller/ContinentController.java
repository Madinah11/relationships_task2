package uz.pdp.relationships_task2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.relationships_task2.Entitiy.Continent;
import uz.pdp.relationships_task2.repositary.ContinentRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class ContinentController {
    @Autowired
    ContinentRepositary continentRepositary;

    @PostMapping("/continent")
    public String addContinent(@RequestBody Continent continent){
        Continent continent1=new Continent(null,continent.getContinentName());
        continentRepositary.save(continent1);
        return "Continent added";



    }

    @GetMapping("/continent")
    public List<Continent> getContinent(){
        List<Continent> allContinent = continentRepositary.findAll();
        return allContinent;
    }

    @GetMapping("/continent/{id}")
    public Continent getContinentById(@PathVariable Integer id){
        Optional<Continent> byId = continentRepositary.findById(id);
        if (byId.isPresent()){
            Continent continent = byId.get();
            return continent;
        }
return new Continent();
    }

    @DeleteMapping("/continent/{id}")
    public String deleteContinent(@PathVariable Integer id){
        continentRepositary.deleteById(id);
        return "Continent deleted";
    }

    @PutMapping("/continent/{id}")
    public String editContinent(@PathVariable Integer id,@RequestBody Continent continent){
        Optional<Continent> byId = continentRepositary.findById(id);
        if (byId.isPresent()){
            Continent editingContinent = byId.get();
            editingContinent.setContinentName(continent.getContinentName());
            continentRepositary.save(editingContinent);
            return "Continent edited";
        }
return "Continent not found";
    }


}
