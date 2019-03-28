package com.sqli.stories.controller;

import com.sqli.stories.entities.Member;
import com.sqli.stories.helpers.payload.MemberIdentityAvailability;
import com.sqli.stories.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<Member> add(@RequestBody Member member) {
        return Optional
                .ofNullable(memberService.add(member))
                .map(addedMember -> ResponseEntity.ok().body(addedMember))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/member")
    public ResponseEntity<Member> update(@RequestBody Member member) {
        return Optional
                .ofNullable(memberService.update(member))
                .map(updatedMember -> ResponseEntity.ok().body(updatedMember))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/member/search/{keyword}")
    public ResponseEntity<List<Member>> getByKeyword(@PathVariable("keyword") String keyword) {
        return ResponseEntity.ok(memberService.searchByKeyword(keyword));
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAll() {
        return ResponseEntity.ok(memberService.getAll());
    }

    @DeleteMapping("/member/{login}")
    public ResponseEntity<Void> delete(@PathVariable("login") String login) {
        try {
            memberService.delete(login);
            return ResponseEntity.noContent().build();

        } catch (ResourceNotFoundException ex) {
         return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/member/{login}")
    public ResponseEntity<Member> getById(@PathVariable("login") String login) {
        return Optional
                .ofNullable(memberService.getByLogin(login))
                .map(member -> ResponseEntity.ok().body(member))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/member/checkLoginAvailability")
    public ResponseEntity<MemberIdentityAvailability> checkLoginAvailability( @RequestParam("login") String login){
        boolean isAvailable =!memberService.existsByLogin(login);
        return  ResponseEntity.ok(new MemberIdentityAvailability(isAvailable));
    }

}
