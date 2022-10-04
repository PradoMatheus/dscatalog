package com.devsuperior.dscatalog.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserInsertDto extends UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String password;

    public UserInsertDto(){
        super();
    }
}
