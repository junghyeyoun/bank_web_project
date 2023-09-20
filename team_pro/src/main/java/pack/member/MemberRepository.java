package pack.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
	// 닉네임으로 회원 정보 조회(아이디 중복)
	Optional<MemberEntity> findByCustomernickname(String customernickname);
	
	Optional<MemberEntity> findByPhonenumber(String Phonenumber);
    
    // 이름과 이메일로 회원 정보 조회 (아이디 찾기)
	MemberEntity findByCustomernameAndPhonenumber(String customername, String phonenumber);
	
    // 닉네임, 이름, 이메일로 정보 조회 (비밀번호 찾기)
	MemberEntity findByCustomernicknameAndCustomernameAndPhonenumber(String customernickname, String customername, String phonenumber);
   
    
}