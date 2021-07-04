package kakaopay.homework.service;

import kakaopay.homework.domain.Membership;
import kakaopay.homework.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MembershipService
{
    private final MembershipRepository membershipRepository;

    public String getCurrentLocalDateTimeStamp()
    {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM--dd'T'HH:mm:ss.SSS"));
    }

    @Transactional
    public String DeactivateMembership(String membershipId,String userId)
    {
        String result = membershipRepository.findByMembershipIdAndUserId(membershipId, userId)
                .map(m -> m.getMembershipId())
                .orElse("failed");
        if (result.equals("failed"))
        {
            return "failed";
        }
        else
        {
            membershipRepository.deleteMembershipByMembershipIdAndUserId(membershipId,userId);
            return "success";
        }
    }

    @Transactional
    public String addMembershipPoint(String membershipId, Integer point, String userId )
    {
        Optional<Membership> member = membershipRepository.findByMembershipIdAndUserId(membershipId, userId);
        if (member.isEmpty())
        {
            return "failed";
        }
        else
        {
            Membership membership = member.get();
            membership.setPoint(membership.getPoint()+point);
            return "success";
        }
    }


}
