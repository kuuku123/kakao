package kakaopay.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccumulateMembership
{
    private String membershipId;
    private Integer amount;
}
