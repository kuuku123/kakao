package kakaopay.homework.service;

import kakaopay.homework.domain.Membership;
import kakaopay.homework.repository.MembershipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MembershipServiceTest
{
    @Autowired
    MembershipRepository membershipRepository;
    @Autowired
    MembershipService membershipService;

//    @BeforeEach
    public void init()
    {
        String res = membershipService.getCurrentLocalDateTimeStamp();
        Membership membership1 = new Membership("spc", "test1", "happypoint", res, "Y", 120);
        Membership membership2 = new Membership("shinsegae", "test1", "shinsegaepoint", res, "Y", 3500);
        Membership membership3 = new Membership("cj", "test1", "cjone", res, "N", 1029);

        membershipRepository.save(membership1);
        membershipRepository.save(membership2);
        membershipRepository.save(membership3);
    }
    @Test
    void getCurrentLocalDateTimeStamp() throws Exception
    {
        String result = membershipService.getCurrentLocalDateTimeStamp();
        System.out.println("result = " + result);
    }

    @Test
    void deactivateMembership() throws Exception
    {
        String s = membershipService.DeactivateMembership("spc", "test1");
        Optional<Membership> member = membershipRepository.findByMembershipIdAndUserId("spc", "test1");
        assertEquals("N",member.get().getMembershipStatus());
    }

    @Test
    void addMembershipPoint() throws Exception
    {
        Optional<Membership> member1 = membershipRepository.findByMembershipIdAndUserId("spc", "test1");
        Integer before = member1.get().getPoint();
        membershipService.addMembershipPoint("spc",300,"test1");
        Optional<Membership> member2 = membershipRepository.findByMembershipIdAndUserId("spc", "test1");
        Integer after = member2.get().getPoint();
        assertEquals(before+300,after);
    }
}