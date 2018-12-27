-- --------------------------------------------------------
-- 호스트:                          192.168.1.32
-- 서버 버전:                        10.1.33-MariaDB - MariaDB Server
-- 서버 OS:                        Linux
-- HeidiSQL 버전:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 테이블 데이터 sis.board:~30 rows (대략적) 내보내기
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` (`boardNo`, `userNo`, `boardTitle`, `boardContents`, `boardClass`, `comYn`, `regDate`, `delYn`) VALUES
	(1, 1, '신규 대여정책 안내', '1234', '1', 'N', '20180621', 'N'),
	(2, 2, '4414', '<p>1234</p>\n', '7', 'N', '20180621', 'N'),
	(3, 1, '123411', '<p>1234</p>\n', '1', 'N', '20180621', 'N'),
	(4, 2, '테스트', '<p>1234</p>\n', '7', 'N', '20180621', 'N'),
	(5, 2, '1234', '<p>12341</p>\n', '7', 'N', '20180621', 'N'),
	(6, 2, '414', '<p>141</p>\n', '7', 'N', '20180621', 'N'),
	(7, 2, '4141234', '<p>1123441234</p>\n', '7', 'N', '20180621', 'N'),
	(8, 2, '114123', '<p>12341</p>\n', '7', 'N', '20180621', 'N'),
	(9, 2, '412311', '<p>1234</p>\n', '7', 'N', '20180621', 'N'),
	(10, 2, 'kk', '<p>kk</p>\n', '7', 'N', '20180621', 'N'),
	(11, 1, '4123', '<p>asdfa</p>\n', '1', 'N', '20180621', 'N'),
	(12, 1, 'hghg', '<p>hghggg</p>\n', '1', 'N', '20180621', 'N'),
	(13, 1, 'adf', '<p>adfa</p>\n', '1', 'N', '20180621', 'N'),
	(14, 1, 'ghghgh', '<p>ghghghg</p>\n', '1', 'N', '20180621', 'N'),
	(15, 1, 'afaf', '<p>asdf</p>\n', '1', 'N', '20180621', 'N'),
	(16, 1, '1234', '<p>1234</p>\n', '1', 'N', '20180622', 'N'),
	(17, 1, '41', '<p>1234</p>\n', '1', 'N', '20180622', 'N'),
	(18, 1, '1234', '<p>12341</p>\n', '1', 'N', '20180622', 'N'),
	(19, 1, '11234', '<p>1234</p>\n', '1', 'N', '20180622', 'N'),
	(20, 2, '삭제', '<p>ㅅㅂ</p>\n', '7', 'N', '20180622', 'N'),
	(21, 2, '111', '<p>111</p>\n', '7', 'N', '20180622', 'N'),
	(22, 1, '411', '<p>adfadf</p>\n', '2', 'N', '20180622', 'N'),
	(23, 1, '41234', '<p>12341234</p>\n', '3', 'N', '20180622', 'N'),
	(24, 1, '12341', '<p>12341234</p>\n', '5', 'N', '20180622', 'N'),
	(25, 1, '1234123', '<p>123411234</p>\n', '6', 'N', '20180622', 'N'),
	(26, 1, '1234123', '<p>1234123</p>\n', '4', 'N', '20180622', 'N'),
	(27, 1, '12341234', '<p>1234123</p>\n', '5', 'N', '20180622', 'N'),
	(28, 1, '1234123', '<p>1231234</p>\n', '3', 'N', '20180622', 'N'),
	(29, 1, '1234123', '<p>1234123</p>\n', '5', 'N', '20180622', 'N'),
	(30, 1, '1234123', '<p>1234</p>\n', '6', 'N', '20180622', 'N');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;

-- 테이블 데이터 sis.boardFiles:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `boardFiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `boardFiles` ENABLE KEYS */;

-- 테이블 데이터 sis.files:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
/*!40000 ALTER TABLE `files` ENABLE KEYS */;

-- 테이블 데이터 sis.users:~5 rows (대략적) 내보내기
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`userNo`, `userEmail`, `userPassword`, `userName`, `userSex`, `userNumb`, `userAdr`, `delYn`) VALUES
	(1, 'admin@naver.com', '1234', '소인성', '남', '01011111111', '서울특별시 금천구', 'N'),
	(2, 'test@1', '1234', 'i뇸뇸i', '여', '1234', '1234', 'N'),
	(3, 'test@2', '1234', '오버워치', '남', '1234', '1234', 'N'),
	(4, 'test@3', '1234', '슈퍼백', '여', '1234', '1234', 'N'),
	(5, 'test@4', '1234', 'i냠냠i', '남', '1234', '1234', 'N');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
