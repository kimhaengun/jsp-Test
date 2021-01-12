# JSP USER 관리 프로젝트

## 환경

- windows10
- jdk1.8
- tomcat9.0
- sts tool
- mysql8.0
- lombok
- jstl
- 인코딩 utf-8
- git

## MySQL 데이터베이스 생성 및 사용자 생성
```sql
create user 'projectuser'@'%' identified by 'bitc5600';
GRANT ALL PRIVILEGES ON *.* TO 'projectuser'@'%';
create database project;
```

## MySQL 테이블 생성
- projectuser 사용자로 접속
- use project; 데이터 베이스 선택

```sql
CREATE TABLE user(
    id int primary key auto_increment,
    username varchar(100) not null unique,
    password varchar(100) not null,
    email varchar(100) not null,
    role varchar(20)
);
```

## 추가. 
- user는 id를 비교해서 같을 시에 삭제 가능
```
<c:if test="${sessionScope.principal.id == board.id}">
    <button onclick="deleteById(${board.id})">삭제</button>
</c:if>
```
- admin은 role 값이 admin이면 모두 삭제 가능
- jstl문법에서  == -> eq 로 사용이 가능하다.
```
<c:if test="${sessionScope.principal.role eq 'admin'}">
    <button onclick="deleteById(${board.id})">삭제</button>
</c:if>
```                
## 초기 화면 
![초기화면](https://user-images.githubusercontent.com/74044234/104345287-835bd180-5541-11eb-94f6-dcf405d59105.jpg)

## 회원가입 
![로그인](https://user-images.githubusercontent.com/74044234/104345301-8656c200-5541-11eb-9c6c-8e4fb04f27f2.jpg)

- 회원가입을 하게 되면 첫화면 User Table에 유저목록 추가 
![회원가입 완료](https://user-images.githubusercontent.com/74044234/104345305-8787ef00-5541-11eb-8f77-713c2c5c99a8.jpg)

## 유저로 로그인하면 로그인한 유저계정만 삭제가능 
![유저 로그인시 삭제버튼생성](https://user-images.githubusercontent.com/74044234/104345311-8951b280-5541-11eb-9427-a0a8ec848442.jpg)

![유저 삭제](https://user-images.githubusercontent.com/74044234/104345813-17c63400-5542-11eb-89ca-fb91ff655c8c.jpg)

## admin계정으로 로그인시 모든 유저계정 삭제 가능
![admin로그인 시 모든 사용자 삭제](https://user-images.githubusercontent.com/74044234/104345822-198ff780-5542-11eb-987a-aed63c060506.jpg)

## 로그아웃 시 세션이 날라가 삭제 불가능
![로그아웃 세션out](https://user-images.githubusercontent.com/74044234/104346815-3416a080-5543-11eb-983d-7e0deae6b0e5.jpg)
