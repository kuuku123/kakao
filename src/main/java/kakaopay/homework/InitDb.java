package kakaopay.homework;

import kakaopay.homework.domain.Membership;
import kakaopay.homework.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb
{
    private final InitService initService;

    @PostConstruct
    public void init()
    {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService
    {
        private final EntityManager em;
        private final MembershipService membershipService;

        public void dbInit()
        {
            String res = membershipService.getCurrentLocalDateTimeStamp();
            Membership membership1 = new Membership("spc", "test1", "happypoint", res, "Y", 120);
            Membership membership2 = new Membership("shinsegae", "test1", "shinsegaepoint", res, "Y", 3500);
            Membership membership3 = new Membership("cj", "test1", "cjone", res, "N", 1029);

            em.persist(membership1);
            em.persist(membership2);
            em.persist(membership3);
        }

    }
}
