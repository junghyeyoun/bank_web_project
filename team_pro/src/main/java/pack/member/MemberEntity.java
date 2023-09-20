package pack.member;



import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "members")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class MemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerid;
    
    @Column(unique = true)
    private String customernickname;
    
    private String passwordhash;
    
    private String customername;
    
    private String customergen;
    
    private String email;
    
    private String phonenumber;

    private String zipcode;
    
    private String address;

    private String detailaddress;
    
    @CreatedDate
    @Column(name = "regdate")
    private LocalDateTime regdate;
    
    public static MemberEntity toMemberEntity(MemberDto memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setCustomernickname(memberDTO.getCustomernickname());
        memberEntity.setPasswordhash(memberDTO.getPasswordhash());
        memberEntity.setCustomername(memberDTO.getCustomername());
        memberEntity.setCustomergen(memberDTO.getCustomergen());
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setPhonenumber(memberDTO.getPhonenumber());
        memberEntity.setZipcode(memberDTO.getZipcode());
        memberEntity.setAddress(memberDTO.getAddress());
        memberEntity.setDetailaddress(memberDTO.getDetailaddress());
        return memberEntity;
    }
    
    
    
}