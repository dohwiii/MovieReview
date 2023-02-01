package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.entity.Member;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMembers()
    {
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder()
                    .email("r" + i + "@zerock.org")
                    .pw("1111")
                    .nickname("reviewer" + i)
                    .build();

            memberRepository.save(member);
        });

    }
    @Commit
    @Transactional
    @Test
    public void testDeleteMember()
    {
        Long mid = 4L;

        Member member = Member.builder().mid(mid).build();

        reviewRepository.deleteByMember(member); //참조하고 있는 쪽에서 먼저 삭제(FK)
        memberRepository.deleteById(mid); //PK 삭제
        //PK쪽을 먼저 삭제하면 이것을 참조하고 있는 Review 테이블이 있으니까 안된다고 에러뜸
        //근데 두개 모르고 순서 거꾸로 돌렸는데 되넹? 뭐지.

    }
}
