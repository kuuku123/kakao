package kakaopay.homework;

import com.google.gson.Gson;
import kakaopay.homework.controller.MemberShipController;
import kakaopay.homework.domain.Membership;
import kakaopay.homework.dto.EnrollMembership;
import kakaopay.homework.repository.MembershipRepository;
import kakaopay.homework.service.MembershipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(MemberShipController.class)
class HomeworkApplicationTests {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    MembershipRepository membershipRepository;
    @MockBean
    MembershipService membershipService;

    @BeforeEach
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
    public void getAll() throws Exception
    {
        //given
        //when
        Optional<List<Membership>> result = membershipRepository.findAllByUserId("test1");
        //then
        assertEquals("spc",result.get().get(0).getMembershipId());
        assertEquals("shinsegae",result.get().get(1).getMembershipId());
        assertEquals("cj",result.get().get(2).getMembershipId());
    }


}
