package com.lwrs.app.domain.dto.req;

import com.lwrs.app.domain.BaseDto;
import com.lwrs.app.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookReq extends BaseDto{
    @NotNull(message = "商户不能为空")
    private Long shopId;

    @NotEmpty(message = "姓名不能为空")
    private String userName;
    /**
     * @see Gender#name
     */
    @NotEmpty(message = "性别不能为空")
    @Pattern(regexp = "^[FM]$", message = "性别错误")
    private String gender;

    @NotEmpty(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号应为11位")
    private String phone;
    /**
     * yyyy-mm-dd
     */
    @NotEmpty(message = "生日不能为空")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "生日格式不正确")
    private String birthDate;
    /**
     * yyyy-mm-dd
     */
    @NotEmpty(message = "预约日期不能为空")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "预约日期格式不正确")
    private String bookDate;

    @NotEmpty(message = "预约套餐不能为空")
    private String bookType;

}
