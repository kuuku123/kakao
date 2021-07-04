package kakaopay.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnrollMembership
{
    private String membershipId;
    private String membershipName;
    private Integer point;
}
