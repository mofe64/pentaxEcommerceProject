package com.pentax.ecommerce.dtos;

import com.pentax.ecommerce.models.Address;
import com.pentax.ecommerce.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotNull
    @NotBlank(message = "firstname field cannot be empty")
    @Min(value = 2, message = "Firstname cannot be less that 2 characters")
    private String firstName;
    @NotNull
    @NotBlank(message = "lastname field cannot be empty")
    @Min(value = 2, message = "lastname cannot be less that 2 characters")
    private String lastName;
    @Email(message = "Please provide a valid email")
    private String email;
    private List<Address> addresses = new ArrayList<>();
    @NotNull
    @NotBlank
    @Min(value = 6, message = "password must be at least 6 chars")
    private String password;

    public static User unpackDTO(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstname(userDTO.getFirstName());
        user.setLastname(userDTO.getLastName());
        user.setAddresses(userDTO.getAddresses());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public static UserDTO packDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setAddresses(user.getAddresses());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstname());
        userDTO.setLastName(user.getLastname());
        userDTO.setPassword("");
        return userDTO;
    }
}
