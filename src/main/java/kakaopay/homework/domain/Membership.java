package kakaopay.homework.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Membership
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @NotBlank
    private String membershipId;
    private String userId;
    @NotBlank
    private String membershipName;
    private String startDate;
    private String membershipStatus;
    private Integer point;

    public Membership(String membershipId, String userId, String membershipName, String startDate, String membershipStatus, Integer point)
    {
        this.membershipId = membershipId;
        this.userId = userId;
        this.membershipName = membershipName;
        this.startDate = startDate;
        this.membershipStatus = membershipStatus;
        this.point = point;
    }

    public Membership()
    {

    }
}
