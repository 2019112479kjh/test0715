package com.example.test0715.day0717;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private List<Member> members = new ArrayList<>();
    private long nextId = 1;

    @GetMapping
    public List<Member> getAllMembers() {
        return members;
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        member.setId(nextId++);
        members.add(member);
        return member;
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<Member> getMemberById(@PathVariable("id") long id) {
//        Member member1 = members.stream()
//                .filter(member -> member.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("뭐시여 이건. 에러여?"));
////        return ResponseEntity.notFound().build();
//        return ResponseEntity.status(404).body(member1);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Member> getMemberById(@PathVariable("id") Long id) {
//        Member member1 = members.stream()
//                .filter(member -> member.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("뭐시여 이건. 에러여?"));
//        return ResponseEntity.status(404).body(member1);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable("id") Long id) {
        Member member1 = members.stream()
                .filter(member -> member.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("뭐시여 이건. 에러여?"));
        MemberDTO memberDTO = new MemberDTO(member1.getEmail());
        return ResponseEntity.status(404).body(memberDTO);
    }
//    @GetMapping("/{id}")
//    public Member getMemberById(@PathVariable("id") long id) {
//        return members.stream()
//                .filter(member -> member.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
//    }

    @PutMapping("/{id}") // put은 전체수정 patch는 특정 수정
    public Member updateMember(@PathVariable("id") long id, @RequestBody Member updateMember) {
        Member member = members.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("멤버를 찾지 못하였습니다."));

        member.setName(updateMember.getName());
        member.setEmail(updateMember.getEmail());
        return member;
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable("id") long id) {
        members.removeIf(m -> m.getId() == id);
    }
}
