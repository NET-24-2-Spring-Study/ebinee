package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // ID는 시스템이 정함
        store.put(member.getId(), member); // ID를 store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        return Optional.ofNullable(store.get(id)); // null일 가능성 고려
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // name이 일치하는 결과를 하나라도 찾으면 반환.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // member들 반환
    }

    public void clearStore() {
        store.clear();
    }
}
