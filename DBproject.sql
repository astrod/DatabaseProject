
-- 로그인 프로시저 --
DROP PROCEDURE IF EXISTS Login;
DELIMITER $$

CREATE PROCEDURE `Login` (IN inputId varchar(30), IN inputPwd varchar(30), OUT returnVal varchar(30) )
	
BEGIN
	DECLARE Result varchar(30);
	DECLARE Result2 varchar(30);
	DECLARE NUM INT;
	
	START TRANSACTION;
		select ID FROM client where ID=inputId into Result;
		if(Result IS NULL) THEN
			set returnVal = 'incorrect ID';
			rollback;
		ELSE
			Select ID INTO Result2 from client WHERE ID = inputId AND Password = md5(inputPwd);
			select Result2;
			if(Result2 IS NULL) THEN
				set returnVal = 'incorrect Password';
				rollback;
			else
				set returnVal = 'correct';
				Select UniNum INTO NUM from client where ID=inputId;
				Insert into Login (client_UniNum, LoginTime) values (NUM, NOW());
				COMMIT;
			END IF;
		END IF;
END $$
DELIMITER ; 

-- 구매 프로시저
DROP PROCEDURE IF EXISTS Buying;
DELIMITER $$

CREATE PROCEDURE `Buying` (IN Num int, IN UniNum_client int, Method varchar(20))
BEGIN
	Update SellingList SET BookState = 'dealing', client_UniNum_client = UniNum_client, BuyDate= CURDATE(), orderMethod =  Method where SellingNum = Num;
END $$
DELIMITER ;

Select UniNum from client where ID='next';

-- 구매 프로시저 테스트
Update SellingList SET BookState = 'dealing', client_UniNum_client = 72, BuyDate= CURDATE(), orderMethod = "신용카드" where SellingNum = 700195348;

select * from SellingList where SellingNum = 700195348;

select * from Book;

-- 물품 등록
insert into SellingList (client_UniNum_seller, Book_ISBN, SellingNum) values (25, 9023548125486, 700192348);

-- 물품 상세사항 확인
select * from SellingList s inner join Book b on s.BOOK_ISBN = b.ISBN where Name = '테스져이떻준감';

-- 분야별로 클릭할 시, 그 분야에 관련된 서적들을 모아서 리스트로 출력해 줌
select ISBN, Page, originPrice, Author, translater from Book b inner join SellingList s on b.ISBN = s.BOOK_ISBN where s.BookState = 'selling';

-- 구매확인
select Name from Book b inner join SellingList s on s.BOOK_ISBN = b.ISBN where SellingNum = '700195348';

-- 결제완료 후 결제확인, 결제방법, 비용, 환불계좌 등을 호출한다.
select * from Payment p inner join SellingList s on p.client_has_client_OrderNum = s.SellingNum inner join Book b on s.Book_ISBN = b.ISBN;

-- 지금까지 구매한 책의 리스트를 기간별로 출력한다.
select PaymentDate from Payment where PaymentDate > 1990-12-13 and PaymentDate < CURDATE();

-- 업로드 후 판매를 취소한 책 리스트를 출력한다.
select Name from Book b inner join SellingList s on b.ISBN = s.Book_ISBN where s.BookState = 'canceled';

-- 구매후 결제전에 취소한 책 리스트를 출력한다.
Select Name from Book b inner join SellingList s on b.ISBN = s.Book_ISBN inner join Payment p on s.SellingNum <> p.client_has_client_OrderNum where s.BookState = 'dealing';

-- 사용자가 관심을 갖는 분야를 출력한다.
Select * from Fields where client_UniNum = 5