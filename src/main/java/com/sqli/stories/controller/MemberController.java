package com.sqli.stories.controller;

import com.sqli.stories.entities.Member;
import com.sqli.stories.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<Member> add(@RequestBody Member member) {
        return Optional
                .ofNullable(memberService.add(member))
                .map(addedMember -> ResponseEntity.ok().body(addedMember))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @PutMapping("/member")
    public ResponseEntity<Member> update(@RequestBody Member member) {
        return Optional
                .ofNullable( memberService.update(member) )
                .map(updatedMember -> ResponseEntity.ok().body(updatedMember))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/member/{keyword}")
    public ResponseEntity<List<Member>> getByKeyword(@PathVariable("keyword") String keyword) {
        return Optional
                .ofNullable( memberService.searchByKeyword(keyword) )
                .map(member -> ResponseEntity.ok().body(member))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAll() {
        return Optional
                .ofNullable( memberService.getAll() )
                .map(member -> ResponseEntity.ok().body(member))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/member/{login}")
    public void delete(@PathVariable("login") String login) {
        memberService.delete(login);
    }

    @GetMapping("/member/{login}")
    public ResponseEntity<Member> getById(@PathVariable("login") String login){
        return Optional
                .ofNullable( memberService.getByLogin(login) )
                .map(member -> ResponseEntity.ok().body(member))
                .orElseGet(() -> ResponseEntity.notFound().build() );
    }
}
