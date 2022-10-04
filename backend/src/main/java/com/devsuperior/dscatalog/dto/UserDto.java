package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank(message = "Required field")
    private String firstName;
    private String lastName;
    @NotBlank(message = "Please, set a valid email")
    private String email;
    @Setter(AccessLevel.NONE)
    private Set<RoleDto> roles = new HashSet<>();

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserDto(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        user.getRoles().forEach(role -> this.roles.add(new RoleDto(role)));
    }
}
