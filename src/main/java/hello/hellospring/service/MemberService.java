package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원은 안된다.
        // Optional 를 바로 반환하는 것은 좋지 않다.

        memberRepository.save(member);
        return member.getId();
    }

    // 자동 메서드 생성 컨트롤 + T (맥기준)
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
             .ifPresent(m -> {
                 throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


    public  Optional<Member> findOne(Long memberId) {
        return memberRepository.findByid(memberId);
    }
}