package kakaopay.homework.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(MemberShipController.class)
class MemberShipControllerTest
{
    @Autowired
    MockMvc mockMvc;
    @MockBean
    MembershipRepository membershipRepository;
    @MockBean
    MembershipService membershipService;

    @Test
    void getAllMembership() throws Exception
    {
        mockMvc.perform(get("/api/v1/membership"))
                .andExpect(status().is(400));
    }

    @Test
    void enrollMembership() throws Exception
    {
        EnrollMembership member = new EnrollMembership("spc", "happypoint", 200);
        Gson gson = new Gson();
        String s = gson.toJson(member);

        //when
        mockMvc.perform(post("/api/v1/membership")
                .header("X-USER-ID","test1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isOk());
        //then
    }

    @Test
    void deleteMembership() throws Exception
    {
        //given
        //when
        mockMvc.perform(delete("/api/v1/membership/{membershipId}","spc")
                .header("X-USER-ID","test1")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(400));
        //then
    }

    @Test
    void getSpecificMembership() throws Exception
    {
       mockMvc.perform(get("/api/v1/membership/{membershipId}","spc"))
               .andExpect(status().is(400));
    }

    @Test
    void addMembershipPoint() throws Exception
    {
        mockMvc.perform(put("/api/v1/membership/point"))
                .andExpect(status().is(400));
    }
}