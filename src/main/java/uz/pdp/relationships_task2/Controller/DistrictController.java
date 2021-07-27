package uz.pdp.relationships_task2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.relationships_task2.Entitiy.District;
import uz.pdp.relationships_task2.Entitiy.Region;
import uz.pdp.relationships_task2.payload.DistrictDto;
import uz.pdp.relationships_task2.repositary.DistrictRepositary;
import uz.pdp.relationships_task2.repositary.RegionRepositary;

import java.util.List;
import java.util.Optional;
@RestController
public class DistrictController {
    @Autowired
    RegionRepositary regionRepositary;
    @Autowired
    DistrictRepositary districtRepositary;

    @PostMapping("/district")
    public String addDistrict(@RequestBody DistrictDto districtDto) {
        Optional<Region> byId = regionRepositary.findById(districtDto.getId());
        if (byId.isPresent()) {
            Region region = byId.get();
            District district = new District(null, districtDto.getDistrictName(), region);
            districtRepositary.save(district);
            return "District added";
        }
        return "District not found";
    }

    @GetMapping("/district")
    public List<District> getDistrict() {
        List<District> all = districtRepositary.findAll();
        return all;
    }

    @GetMapping("/district/{id}")
    public District getDistrictById(@PathVariable Integer id) {
        Optional<District> byId = districtRepositary.findById(id);
        if (byId.isPresent()) {
            District district = byId.get();
            return district;
        }
        return new District();
    }

    @DeleteMapping("/district/{id}")
    public String deleteDistrict(@PathVariable Integer id) {
        districtRepositary.deleteById(id);
        return "District deleted";
    }

    @PutMapping("/district/{id}")
    public String editDistrict(@PathVariable Integer id, @RequestBody DistrictDto districtDto) {
        Optional<District> byId = districtRepositary.findById(id);
        if (byId.isPresent()) {
            District district = byId.get();
            Optional<Region> byId1 = regionRepositary.findById(districtDto.getId());
            if (byId1.isPresent()) {
                Region region = byId1.get();
                district.setDistrictName(districtDto.getDistrictName());
                district.setRegion(region);
                districtRepositary.save(district);
                return "District edited";

            }

        }
        return "District not found";
    }
}