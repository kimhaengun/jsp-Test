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
