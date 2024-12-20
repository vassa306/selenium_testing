package pages.autostore.page_objects.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pages.autostore.page_objects.model.enums.Region;


@Getter
@AllArgsConstructor


public class RegistrationModel {

    String firstName;
    String lastName;
    String email;
    String address;
    String city;
    Region region;
    String zip;
    String country;
    String loginName;
    String password;
    String passwordConfirm;
}



