package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 테스트가 끝날때마다 해당 저장되어있는 데이터를 깔끔하게 지워주는 역할
    public void afterEach() {
        repository.clearStore();
    }
    // @AfterEach : 해당 Test가 끝날때마다 해당 public을 작동시켜줌
    // Test : 연결되어있으면안됨 , 항상 따로 분리되어 있어야 함

    @Test // 테스트를 할수 있게 도와줌
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findByid(member.getId()).get();
        // Optional에서 값을 가져올때는 다음과 같이 get으로 가져온다.
        //이게 좋은 방법은 아니지만 테스트를 진행할때 다음과 같은 방식으로 진행해도 무관

//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(member, result); //테스트를 할때 특정 메소드를 직접 강제로 작동시키는 방법

        Assertions.assertThat(member).isEqualTo(result);
        // member 가 Result와 똑같다 .라는 뜻
    }

    @Test
    public void findByName() {
        Member member1  = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(3);
    }
}
