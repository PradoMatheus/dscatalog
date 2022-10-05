package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.services.validation.UserInsertValid;
import com.devsuperior.dscatalog.services.validation.UserUpdateValid;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@UserUpdateValid
public class UserUpdateDto extends UserDto implements Serializable {
    private static final long serialVersionUID = 1L;
}
