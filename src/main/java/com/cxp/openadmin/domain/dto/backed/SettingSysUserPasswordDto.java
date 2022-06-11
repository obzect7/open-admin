package com.cxp.openadmin.domain.dto.backed;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class SettingSysUserPasswordDto {

    @NotBlank(message = "비밀번호는 필수입력입니다.")
    private String sourcePassword;

    @NotBlank(message = "새 비밀번호는 필수입력입니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수입력입니다.")
    private String confirmPassword;

}
