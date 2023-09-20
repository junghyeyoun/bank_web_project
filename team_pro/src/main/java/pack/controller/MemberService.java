package pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pack.member.MemberDto;
import pack.member.MemberEntity;
import pack.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

   private final MemberRepository memberRepository;

   // 회원 정보를 데이터베이스에 저장
   public void save(MemberDto memberDto) {
      MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
      memberRepository.save(memberEntity);

   }
   
   // 로그인 성공, 실패 기능 처리
   public MemberDto login(MemberDto memberDTO) {
      /*
       1. 회원이 입력한 이메일로 DB에서 조회를 함
       2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
       */
      Optional<MemberEntity> byCustomernickname = memberRepository.findByCustomernickname(memberDTO.getCustomernickname());
      if(byCustomernickname.isPresent()) {
         // 조회 결과가 있다
         MemberEntity memberEntity = byCustomernickname.get();
         if(memberEntity.getPasswordhash().equals(memberDTO.getPasswordhash())) {
            // 비밀번호 일치
            // entity -> dto로 변환 후 리턴
            MemberDto dto = MemberDto.toMemberDTO(memberEntity);
            return dto;
         }else {
            // 비밀번호 불일치(로그인 실패)
            return null;
         }
         
      }else {
         // 조회 결과가 없다
         return null;
      }
      
   }
   
    // 아이디 중복 검사 
    public String nicknameCheck(String customernickname) {
           Optional<MemberEntity> byCustomernickname = memberRepository.findByCustomernickname(customernickname);
           if (byCustomernickname.isPresent()) {
               // 조회결과가 있으니 사용 불가.
               return null;
           } else {
               // 조회결과가 없으니 사용 가능.
               return "ok";
           }
       }
    // 아이디 중복 검사 
    public String nicknameCheck1(String customernickname) {
       Optional<MemberEntity> byCustomernickname = memberRepository.findByCustomernickname(customernickname);
       if (byCustomernickname.isPresent()) {
          // 조회결과가 있으니 사용 불가.
          return null;
       } else {
          // 조회결과가 없으니 사용 가능.
          return "ok";
       }
    }

    // 휴대전화 중복 검사 
    public String phonenumberCheck(String phonenumber) {
       Optional<MemberEntity> byPhonenumber = memberRepository.findByPhonenumber(phonenumber);
       if (byPhonenumber.isPresent()) {
          // 조회결과가 있으니 사용 불가.
          return null;
       } else {
          // 조회결과가 없으니 사용 가능.
          return "ok";
       }
    }
    // 휴대전화 중복 검사 
    public String phonenumberCheck1(String phonenumber) {
       Optional<MemberEntity> byPhonenumber = memberRepository.findByPhonenumber(phonenumber);
       if (byPhonenumber.isPresent()) {
          // 조회결과가 있으니 사용 불가.
          return null;
       } else {
          // 조회결과가 없으니 사용 가능.
          return "ok";
       }
    }
    
   
   // 이름과 휴대전화번호로 아이디를 찾는 메서드
    public String findNicknameByNameAndPhonenumber(String customername, String phonenumber) {
        MemberEntity memberEntity = memberRepository.findByCustomernameAndPhonenumber(customername, phonenumber);
        if (memberEntity != null) {
            return memberEntity.getCustomernickname();
        }
        return null;
    }
    

   // 닉네임, 이름, 이메일로 비밀번호를 찾는 메서드
    public String findPassword(String customernickname, String customername, String phonenumber) {
          MemberEntity memberEntity = memberRepository.findByCustomernicknameAndCustomernameAndPhonenumber(customernickname, customername, phonenumber);

          if (memberEntity != null) {
              return memberEntity.getPasswordhash();
          } else {
              return null;
          }
      }

   
}      
