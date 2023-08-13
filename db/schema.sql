# 관리자 회원 생성
INSERT INTO member
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
authLevel = '7',
name = 'admin',
nickname = 'admin',
cellphoneNo = '01012341234',
email = 'admin@test.com';

# 일반 회원 2명 생성
INSERT INTO member
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = 'user1',
authLevel = '3',
name = 'user1',
nickname = 'user1',
cellphoneNo = '01012341231',
email = 'user1@test.com';

INSERT INTO member
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPw = 'user2',
authLevel = '3',
name = 'user2',
nickname = 'user2',
cellphoneNo = '01012341232',
email = 'user2@test.com';

# 샘플 데이터 추가
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3';