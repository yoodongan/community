# database 초기화 및 생성
DROP DATABASE IF EXISTS `community`;
CREATE DATABASE `community`;
USE `community`;

# 게시물 테이블 생성
CREATE TABLE article(
                        id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        regDate DATETIME NOT NULL,
                        updateDate DATETIME NOT NULL,
                        title CHAR(100) NOT NULL,
                        body TEXT NOT NULL
);

# 게시물 테스트 데이터 3개 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
body = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
body = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
body = '내용3';

# 회원 테이블 생성
CREATE TABLE member(
                       id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       regDate DATETIME NOT NULL,
                       updateDate DATETIME NOT NULL,
                       loginId CHAR(20) NOT NULL,
                       loginPw CHAR(50) NOT NULL,
                       authLevel SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '(3=일반, 7=관리자)',
                       `name` CHAR(20) NOT NULL,
                       nickname CHAR(20) NOT NULL,
                       cellphoneNo CHAR(20) NOT NULL,
                       email CHAR(20) NOT NULL,
                       delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '(0=탈퇴X, 1=탈퇴O)',
                       delDate DATETIME COMMENT '탈퇴날짜'
);

# 회원 테스트 데이터 생성
INSERT INTO member
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = 'user1',
`name` = '사용자1',
nickname = '사용자1',
cellphoneNo = '01012341234',
email = 'user1@test.com';

INSERT INTO member
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPw = 'user2',
`name` = '사용자2',
nickname = '사용자2',
cellphoneNo = '01012341233',
email = 'user2@test.com';

INSERT INTO member
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
authLevel = 7,
`name` = '관리자',
nickname = '관리자',
cellphoneNo = '01043214321',
email = 'admin@test.com';

# 게시물 테이블 과 회원 테이블 연결 - 게시물 테이블에 회원정보를 추가
ALTER TABLE article
    ADD COLUMN memberId BIGINT UNSIGNED NOT NULL AFTER `updateDate`;

# 게시물 테스트 데이터에 작성자 추가
UPDATE article
SET memberId = 2
WHERE memberId = 0;

UPDATE article
SET memberId = 1
WHERE `id` = 3;

# 게시판(board) 테이블 생성
CREATE TABLE board (
                       id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       regDate DATETIME NOT NULL,
                       updateDate DATETIME NOT NULL,
                       `code` CHAR(10) NOT NULL UNIQUE COMMENT 'notice(공지사항), free(자유게시판)',
                       `name` CHAR(20) NOT NULL UNIQUE COMMENT '게시판 이름',
                       delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '삭제여부' ,
                       delDate DATETIME COMMENT '삭제날짜'
);

# 게시판 테스트 데이터 추가
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'notice',
`name` = '공지사항';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'free',
`name` = '자유1';

# 게시물 테이블에 게시판 테이블 연결
ALTER TABLE article
    ADD COLUMN boardId BIGINT UNSIGNED NOT NULL AFTER memberId;

# 게시물 테이블에 게시판 정보 삽입
UPDATE article
SET boardId = 1
WHERE id IN (1, 2);

UPDATE article
SET boardId = 2
WHERE id = 3;

select * from article;

# 대량의 게시물 샘플 데이터 추가
INSERT INTO article (
	regDate, updateDate, memberId, boardId, title, body
)
SELECT NOW(), NOW(), FLOOR(RAND() * 2) + 1, FLOOR(RAND() * 2) + 1, CONCAT('제목', rand()), CONCAT('내용', RAND()) FROM article;

# 게시물 테이블에 hitCount(조회수) 추가
ALTER TABLE article
    ADD COLUMN hitCount INT(10) UNSIGNED NOT NULL;

# 리액션 테이블 추가
CREATE TABLE reaction(
                         id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         regDate DATETIME NOT NULL,
                         updateDate DATETIME NOT NULL,
                         memberId BIGINT UNSIGNED NOT NULL,
                         relTypeCode CHAR(30) NOT NULL COMMENT '관련 데이터타입코드(우선, article)',
                         relId BIGINT UNSIGNED NOT NULL COMMENT '관련 게시물 번호',
                         `point` SMALLINT NOT NULL
);

# 리액션 테이블에 테스트 데이터 추가
# 1번회원 1번게시물에 대해 '좋아요'
INSERT INTO reaction
(regDate, updateDate, memberId, relTypeCode, relId, `point`)
VALUES(NOW(), NOW(), 1, 'article', 1, 1);

# 1번회원 2번게시물에 대해 '싫어요'
INSERT INTO reaction
(regDate, updateDate, memberId, relTypeCode, relId, `point`)
VALUES(NOW(), NOW(), 1, 'article', 2, -1);

# 2번회원 1번게시물에 대해 '싫어요'
INSERT INTO reaction
(regDate, updateDate, memberId, relTypeCode, relId, `point`)
VALUES(NOW(), NOW(), 2, 'article', 1, -1);

# 2번회원 2번게시물에 대해 '좋아요'
INSERT INTO reaction
(regDate, updateDate, memberId, relTypeCode, relId, `point`)
VALUES(NOW(), NOW(), 2, 'article', 2, 1);

# 3번회원 1번게시물에 대해 '좋아요'
INSERT INTO reaction
(regDate, updateDate, memberId, relTypeCode, relId, `point`)
VALUES(NOW(), NOW(), 3, 'article', 1, 1);

# 3번회원 2번게시물에 대해 '좋아요'
INSERT INTO reaction
(regDate, updateDate, memberId, relTypeCode, relId, `point`)
VALUES(NOW(), NOW(), 3, 'article', 2, -1);