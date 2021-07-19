package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //회원 서비스 스프링 빈 등록
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemoryMemberRepository memberRepository){

        this.memberRepository = memberRepository;
    }

    /**
    *회원 가입
    */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //중복 회원 검증 메서드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())//Optional으로 감쌈
                .ifPresent(m -> {                    //Optional 안 멤버객체 - ifPresent = null이 아닐 경우 동작 , get()으로 꺼낼 수 있음
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
