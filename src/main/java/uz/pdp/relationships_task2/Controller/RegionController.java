package uz.pdp.relationships_task2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.relationships_task2.Entitiy.Continent;
import uz.pdp.relationships_task2.Entitiy.Country;
import uz.pdp.relationships_task2.Entitiy.District;
import uz.pdp.relationships_task2.Entitiy.Region;
import uz.pdp.relationships_task2.payload.CountryDto;
import uz.pdp.relationships_task2.payload.RegionDto;
import uz.pdp.relationships_task2.repositary.CountryRepositary;
import uz.pdp.relationships_task2.repositary.RegionRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class RegionController {
    @Autowired
    CountryRepositary countryRepositary;

    @Autowired
    RegionRepositary regionRepositary;

    @PostMapping("/region")
    public String addRegion(@RequestBody RegionDto regionDto){
        Optional<Country> byId = countryRepositary.findById(regionDto.getId());
        if (byId.isPresent()){
            Country country = byId.get();
            Region region1=new Region(null,regionDto.getRegionName(),country);
            regionRepositary.save(region1);
            return "Region added";
        }
        return "Region not found";
    }

    @GetMapping("/region")
    public List<Region> getRegion(){
        List<Region> regions = regionRepositary.findAll();
        return regions;
    }

    @GetMapping("/region/{id}")
    public Region getRegionById(@PathVariable Integer id){
        Optional<Region> byId = regionRepositary.findById(id);
        if (byId.isPresent()){
            Region region = byId.get();
            return region;
        }
        return new Region();
    }

    @DeleteMapping("/region/{id}")
    public String deleteCountry(@PathVariable Integer id){
        regionRepositary.deleteById(id);
        return "Region deleted";
    }

    @PutMapping("/region/{id}")
    public String editRegion(@PathVariable Integer id,@RequestBody RegionDto regionDto){
        Optional<Region> byId = regionRepositary.findById(id);
        if (byId.isPresent()){
            Region region = byId.get();

            Optional<Country> byId1 = countryRepositary.findById(regionDto.getId());
            if (byId1.isPresent()) {
                Country country = byId1.get();
                region.setRegionName(regionDto.getRegionName());
                region.setCountry(country);
                regionRepositary.save(region);


                return "Region edited";
            }

        }
        return "Region not found";
    }

}
