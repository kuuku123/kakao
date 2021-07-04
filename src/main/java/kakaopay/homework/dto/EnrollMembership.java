package kakaopay.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollMembership
{
    private String membershipId;
    private String membershipName;
    private Integer point;
}
