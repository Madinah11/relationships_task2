package uz.pdp.relationships_task2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.relationships_task2.Entitiy.Address;
import uz.pdp.relationships_task2.Entitiy.District;
import uz.pdp.relationships_task2.payload.AddressDto;
import uz.pdp.relationships_task2.repositary.AddressRepositary;
import uz.pdp.relationships_task2.repositary.DistrictRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class AdressController {
    @Autowired
    DistrictRepositary districtRepositary;
    @Autowired
    AddressRepositary addressRepositary ;

    @RequestMapping(value = "/address",method = RequestMethod.POST)
    public String addAddress(@RequestBody AddressDto addressDto){
        Optional<District> byId = districtRepositary.findById(addressDto.getId());
        if (byId.isPresent()){
            District district1 = byId.get();
            Address address=new Address(null,addressDto.getStreet(),district1);
            addressRepositary.save(address);
            return "Address added";
        }
        return "Address not found";
    }

    @GetMapping("/address/{id}")
    public Address getAddressById(@PathVariable Integer id){
        Optional<Address> byId = addressRepositary.findById(id);
        if (byId.isPresent()){
            Address address = byId.get();
            return address;
        }
        return new Address();
    }

    @GetMapping("/address")
    public List<Address> getAddress(){
        List<Address> all = addressRepositary.findAll();
        return all;
    }

    @DeleteMapping("/address/{id}")
    public String deleteAddress(@PathVariable Integer id){
        addressRepositary.deleteById(id);
        return "Address deleted";
    }

    @PutMapping("/address/{id}")
    public String editAddress(@PathVariable Integer id,@RequestBody AddressDto addressDto){
        Optional<Address> byId = addressRepositary.findById(id);
        if (byId.isPresent()){
            Address address = byId.get();

            Optional<District> byIdDistrict = districtRepositary.findById(addressDto.getId());
            if (byIdDistrict.isPresent()){
                District district = byIdDistrict.get();

                address.setStreet(addressDto.getStreet());
                address.setDistrict(district);
                addressRepositary.save(address);
                return "Address edited";
            }


        }
        return "Address not found";
    }
}
