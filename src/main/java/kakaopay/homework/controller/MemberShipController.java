package kakaopay.homework.controller;


import kakaopay.homework.domain.Membership;
import kakaopay.homework.dto.AccumulateMembership;
import kakaopay.homework.dto.EnrollMembership;
import kakaopay.homework.repository.MembershipRepository;
import kakaopay.homework.service.MembershipService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberShipController
{
    private final MembershipRepository membershipRepository;
    private final MembershipService membershipService;

    @GetMapping("/api/v1/membership")
    public ResponseEntity getAllMembership(@RequestHeader("X-USER-ID") String userId)
    {
        try
        {
            Optional<List<Membership>> all = membershipRepository.findAllByUserId(userId);
            if (all.isEmpty())
            {
                return new ResponseEntity(new Result("false",null,null), HttpStatus.valueOf(400));
            }
            else
            {
                return new ResponseEntity(new Result("true", all.get(), null), HttpStatus.valueOf(200));
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Result("false",null,null), HttpStatus.valueOf(400));
        }
    }

    @PostMapping("/api/v1/membership")
    public ResponseEntity enrollMembership(@RequestBody EnrollMembership enrollMembership,
                                   @RequestHeader("X-USER-ID") String userId)
    {
        try
        {
            String cur_time = membershipService.getCurrentLocalDateTimeStamp();
            Membership newMember = new Membership(enrollMembership.getMembershipId(), userId,enrollMembership.getMembershipName() , cur_time, "Y", enrollMembership.getPoint());
            membershipRepository.save(newMember);
            return new ResponseEntity(new Result("true", newMember,null), HttpStatus.valueOf(200));
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Result("false",null,new ApiError(400,"membershipid must be provided")), HttpStatus.valueOf(400));
        }

    }

    @DeleteMapping("/api/v1/membership/{membershipId}")
    public ResponseEntity deleteMembership(@PathVariable("membershipId") String membershipId, @RequestHeader("X-USER-ID") String userId)
    {
        try
        {
            String done = membershipService.DeactivateMembership(membershipId, userId);
            if (done.equals("failed"))
            {
                return new ResponseEntity(new Result("false",null,new ApiError(400,"no such membershipId exist in your userId")), HttpStatus.valueOf(400));
            }
            else
            {
                return new ResponseEntity(new Result("true",true,null),HttpStatus.valueOf(200));
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity(new Result("false",null,new ApiError(400,"no such membershipId exist in your userId")), HttpStatus.valueOf(400));
        }
    }

    @GetMapping("/api/v1/membership/{membershipId}")
    public ResponseEntity getSpecificMembership(@PathVariable("membershipId") String membershipId,@RequestHeader("X-USER-ID") String userId )
    {
        Optional<Membership> member = membershipRepository.findByMembershipIdAndUserId(membershipId, userId);
        if (member.isEmpty())
        {
            return new ResponseEntity(new Result("false",null,new ApiError(400,"no such membershipId exist in your userId")),HttpStatus.valueOf(400));
        }
        else
        {
            return new ResponseEntity(new Result("true", member.get(),null),HttpStatus.valueOf(200));
        }
    }

    @PutMapping("/api/v1/membership/point")
    public ResponseEntity addMembershipPoint(@RequestBody AccumulateMembership accumulateMembership, @RequestHeader("X-USER-ID") String userId)
    {
        String done = membershipService.addMembershipPoint(accumulateMembership.getMembershipId(), accumulateMembership.getAmount(), userId);
        if (done.equals("failed"))
        {
            return new ResponseEntity(new Result("false",null,new ApiError(400,"no such membershipId exist in your userId")),HttpStatus.valueOf(400));
        }
        else
        {
            return new ResponseEntity(new Result("true", true,null),HttpStatus.valueOf(200));
        }
    }

    @Data
    @AllArgsConstructor
    static class Result<T>
    {
        private String success;
        private T response;
        private ApiError error;
    }

    @Data
    @AllArgsConstructor
    static class ApiError
    {
        private Integer status;
        private String message;
    }

}
