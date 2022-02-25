package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); //저장된 회원들이 반환되어지는 부분
    // 요즘은, null 값을 반환할때 optional로 감싸서 반환하는걸 많이 선호함
    Optional<Member> findByid(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
