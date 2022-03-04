package web.practice.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    public static Map<Long, Member> memberList = new HashMap<>();

    @Override
    public void save(Member member) {
        memberList.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return memberList.get(memberId);
    }

    public List<Member> findAll(){
        return new ArrayList<>(memberList.values());
    }
}
