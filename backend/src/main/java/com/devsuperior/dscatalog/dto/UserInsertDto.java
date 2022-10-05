package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.services.validation.UserInsertValid;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@UserInsertValid
public class UserInsertDto extends UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String password;

    public UserInsertDto(){
        super();
    }
}
