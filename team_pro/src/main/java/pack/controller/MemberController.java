package pack.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid; 
import lombok.RequiredArgsConstructor;
import pack.member.MemberDto;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 서비스 생성자 주입
    private final MemberService memberService;
    
    private Logger log = LoggerFactory.getLogger(MemberController.class);
    
    // 로그인 페이지 표시
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
    
    
    @PostMapping("/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession session, Model model) {
       MemberDto loginResult = memberService.login(memberDto);
       if(loginResult != null) {
          // login 성공, 세션에 닉네임을 저장
         session.setAttribute("loginNickname", loginResult.getCustomernickname());
          return "redirect:/index.html";
       }else {
          // 컨트롤러와 뷰의 데이터 전달 / 로그인 실패라는 값을 msg에 추가
          model.addAttribute("msg","아이디나 비밀번호가 일치하지 않습니다");
          return "login";
       }
    }
    
    // 로그아웃 핸들러 메서드, 세션을 무효화하여 로그아웃 처리
    @GetMapping("logout")
    public String loggout(HttpSession session) {
       session.invalidate();
       return "redirect:/index";
    }
    
    // 닉네임 중복 여부 확인
    @PostMapping("nickname-Check")
    public @ResponseBody String nicknameCheck(@RequestParam("customernickname") String customernickname) {
        System.out.println("customernickname = " + customernickname);
        String checkResult = memberService.nicknameCheck(customernickname);
        return checkResult;
    }
    // 닉네임 중복 여부 확인
    @PostMapping("nickname-Check1")
    public @ResponseBody String nicknameCheck1(@RequestParam("customernickname") String customernickname) {
       System.out.println("customernickname = " + customernickname);
       String checkResult2 = memberService.nicknameCheck1(customernickname);
       return checkResult2;
    }

    
    // 휴대전화 중복 여부 확인
    @PostMapping("phone-Check")
    public @ResponseBody String phonenumberCheck(@RequestParam("phonenumber") String phonenumber) {
       System.out.println("phonenumber = " + phonenumber);
       String phoneCheckResult = memberService.phonenumberCheck(phonenumber);
       return phoneCheckResult;
    }

    // 휴대전화 중복 여부 확인
    @PostMapping("phone-Check1")
    public @ResponseBody String phonenumberCheck1(@RequestParam("phonenumber") String phonenumber) {
       System.out.println("phonenumber = " + phonenumber);
       String phoneCheckResult3 = memberService.phonenumberCheck1(phonenumber);
       return phoneCheckResult3;
    }

    // 회원가입 페이지
    @GetMapping("/register")
    public String Register() {
        return "register";
    }
    
    // 회원가입
    @PostMapping("/register")
    public String register(@ModelAttribute @Valid MemberDto memberDto, BindingResult bindingResult) {
       if (bindingResult.hasErrors()) {
           // 유효성 검사 에러가 있는 경우
    	 
           return "register";
       }
        // 회원 정보 
        memberService.save(memberDto);
        return "login"; // 가입 후 로그인 페이지로 이동
    }
    
    // 아이디 찾기 페이지
    @GetMapping("/id_search")
    public String IdSearch() {
       return "id_search";
    }
    
    @GetMapping("/findIdByPhone")
    public String findNicknameByPhone(@RequestParam("customername") String customername, @RequestParam("phonenumber") String phonenumber, Model model) {
        // 입력된 이름과 휴대전화번호를 가지고 아이디를 찾는 서비스 메서드 호출
        String foundNickname = memberService.findNicknameByNameAndPhonenumber(customername, phonenumber);

        if (foundNickname != null) {
            model.addAttribute("foundNickname", foundNickname);
            return "id"; // foundId.html로 이동
        } else {
            return "notFound"; // notFound.html로 이동
        }
    }
    
    // 비밀번호 찾기 페이지
    @GetMapping("/passwd_search")
    public String PasswdSearch() {
       return "passwd_search";
    }
    
    // 이것도 똑같이 비밀번호 찾기 페이지에서 닉네임, 이름, 이메일을 받아 비밀번호를 찾는 엔드포인트
    @GetMapping("/findPasswd")
    public String findPassword(@RequestParam("customernickname") String customernickname, @RequestParam("customername") String customername, @RequestParam("phonenumber") String phonenumber, Model model) {
        // customernickname, customername 및 phonenumber를 사용하여 비밀번호를 찾음
        String foundPassword = memberService.findPassword(customernickname, customername, phonenumber);

        if (foundPassword != null) {
           model.addAttribute("foundPassword", foundPassword);
            return "id";
        } else {
            return "일치하는 정보가 없습니다.";
        }
    }
}