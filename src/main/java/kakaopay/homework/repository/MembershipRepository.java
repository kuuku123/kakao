package kakaopay.homework.repository;

import kakaopay.homework.domain.Membership;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership,Long>
{
    Optional<List<Membership>> findAllByUserId(String userId);
    Optional<Membership> findByMembershipIdAndUserId(String membershipId,String userId);
}
