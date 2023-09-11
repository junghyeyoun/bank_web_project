![header](https://capsule-render.vercel.app/api?type=Cylinder&color=1E9133&height=200&section=header&text=clover_pay&fontSize=60&fontColor=705A64&stroke=3A2F32&strokeWidth=2&animation=twinkling)
![image](https://github.com/jun981125/bank_web_project/assets/139423925/64dc2c69-20f4-44b3-bfa4-1c581a559617)


# 💡 git 사용방법

  import : <a href="https://jin2rang.tistory.com/m/entry/GitHub%EC%97%90%EC%84%9C-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-import%ED%95%98%EA%B8%B0">import링크</a>

  import하는 방법은 이걸로 해주세요 ! 나머지는 팀원보고 따라하면 될것 같습니다. 

  팀원 : <a href="https://uni.rejoice-it.com/m/entry/Git-%EC%9D%B4%ED%81%B4%EB%A6%BD%EC%8A%A4-%EC%97%B0%EB%8F%99-%ED%98%91%EC%97%85%ED%95%98%EA%B8%B0-2-%ED%8C%80%EC%9B%90">팀원링크</a>
  
  pull&push&merge : <a href="https://uni.rejoice-it.com/m/entry/Git-%EC%9D%B4%ED%81%B4%EB%A6%BD%EC%8A%A4-%EC%97%B0%EB%8F%99-%ED%98%91%EC%97%85%ED%95%98%EA%B8%B0-3-pull-push">pull&push&merge링크</a>
  
  깃 안써보신거 같아서 보기 쉽게 올려드려요 ! 

### 🚫 주의 사항 🚫
1. 각자 이클립스(스프링)에서 clone한 git repository 웬만하면 지우지 마세요 ! 만약 꼭 지워야겠다면 체크박스 중에 위에 있는 거 꼭!!! 하나만 선택해주셔야 해요. 안그러면 컴퓨터에 있는 프로젝트 파일까지 날라갑니당 😥 그냥 안지우는게 제일 베스트에용
2. pull하고 수정하고 push 해주세요! 안그러면 충돌?? 문제 일어날 수도 있습니다 😥
3. 저는 자주 non-fast-forward 오류가 나는데 옆에 있는 포스팅 보면서 따라하면 바로 해결됩니다 😀  ☞ https://hanyda.tistory.com/36
4. 토큰이 만료 되었을 수도 있으니까 확인해보세요. 만료되었다면 갱신하거나 삭제하고 다시 만들면 됩니당 ! 토큰은 한번만 보여주니까 꼭 복사해서 기억하기 쉬운데에 복붙해두세용 😁 ☞ https://kim-dragon.tistory.com/122
5. 처음 import 하거나 push 하면 username과 토큰(password)를 입력해야하는데 username 기억안나시면 옆 포스팅 보고 확인해보세요 ! ☞ https://yusang.tistory.com/26
6. main branch에서 바로 수정하지말고 다른 branch를 만들어서 수정or 추가 할텐데 각자 개인 branch를 쓰고 이름을 정합시당 >< 전 그냥 제이름으로 할까 생각중이에욥
7. git ignore에 아무것도 쓰지 말기 ❗❗❗❗❗❗❗❗❗❗❗❗❗❗❗❗❗❗❗❗❗❗❗
   
  별거 아니지만 2개월동안 깃하면서 시행착오 겪었던 것들 공유합니당.. 🐷 우리팀 파이팅..  화이팅!!

# 💡 기술 스택
<svg fill="#E34F26" role="img" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><title>HTML5</title><path d="M1.5 0h21l-1.91 21.563L11.977 24l-8.564-2.438L1.5 0zm7.031 9.75l-.232-2.718 10.059.003.23-2.622L5.412 4.41l.698 8.01h9.126l-.326 3.426-2.91.804-2.955-.81-.188-2.11H6.248l.33 4.171L12 19.351l5.379-1.443.744-8.157H8.531z"/></svg>

# 💡 table
USE bank_db;

CREATE TABLE IF NOT EXISTS Customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    address VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(10),
    occupation VARCHAR(50),
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    customer_id INT NOT NULL,
    account_type VARCHAR(50) NOT NULL,
    balance DECIMAL(10, 2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE IF NOT EXISTS FinancialProducts (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    product_type VARCHAR(50) NOT NULL,
    interest_rate DECIMAL(5, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Admins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);







-- 데이터베이스 선택
USE bank_db;

-- Customers 테이블 생성 (이미 존재하면 생략)
CREATE TABLE IF NOT EXISTS Customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY, -- 고객 ID (자동 증가)
    name VARCHAR(100) NOT NULL, -- 이름
    email VARCHAR(100) UNIQUE NOT NULL, -- 이메일 (고유한 값, 필수)
    phone_number VARCHAR(15) NOT NULL, -- 전화번호 (필수)
    address VARCHAR(255) NOT NULL, -- 주소 (필수)
    date_of_birth DATE NOT NULL, -- 생년월일 (필수)
    gender VARCHAR(10), -- 성별
    occupation VARCHAR(50), -- 직업 (NULL 허용)
    password VARCHAR(100) NOT NULL, -- 비밀번호 (필수)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 생성 일시 (자동 생성)
);

-- Accounts 테이블 생성 (이미 존재하면 생략)
CREATE TABLE IF NOT EXISTS Accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY, -- 계좌 ID (자동 증가)
    account_number VARCHAR(20) UNIQUE NOT NULL, -- 계좌 번호 (고유한 값, 필수)
    customer_id INT NOT NULL, -- 고객 ID (필수)
    account_type VARCHAR(50) NOT NULL, -- 계좌 유형 (필수)
    balance DECIMAL(10, 2) DEFAULT 0.00, -- 잔액 (기본값 0.00)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (자동 생성)
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id) -- 외래 키 (Customers 테이블과 연결)
);

-- FinancialProducts 테이블 생성 (이미 존재하면 생략)
CREATE TABLE IF NOT EXISTS FinancialProducts (
    product_id INT AUTO_INCREMENT PRIMARY KEY, -- 금융 상품 ID (자동 증가)
    product_name VARCHAR(100) NOT NULL, -- 상품명 (필수)
    product_type VARCHAR(50) NOT NULL, -- 상품 유형 (필수)
    interest_rate DECIMAL(5, 2) NOT NULL, -- 이자율 (필수)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 생성 일시 (자동 생성)
);

-- Admins 테이블 생성 (비밀번호 열 추가)
CREATE TABLE IF NOT EXISTS Admins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY, -- 관리자 ID (자동 증가)
    username VARCHAR(50) UNIQUE NOT NULL, — 사용자 이름 (고유한 값, 필수)
    password VARCHAR(100) NOT NULL, — 비밀번호 (필수)
    name VARCHAR(100) NOT NULL, — 이름 (필수)
    email VARCHAR(100) UNIQUE NOT NULL, — 이메일 (고유한 값, 필수)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP — 생성 일시 (자동 생성)
);
