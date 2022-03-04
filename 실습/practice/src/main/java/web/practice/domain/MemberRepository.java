package web.practice.domain;

import java.util.List;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
    List<Member> findAll();
}
