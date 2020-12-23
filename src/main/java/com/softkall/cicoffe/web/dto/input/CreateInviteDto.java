package com.softkall.cicoffe.web.dto.input;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.util.Collection;



@Data
public class CreateInviteDto {
    @NotBlank
    private String link;
    @NotBlank
    Collection<String> emails;
}
