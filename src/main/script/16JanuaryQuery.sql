use classicmodels;

CREATE TABLE iman_16_jan_bank_feeder_user (
	id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
	balance INT
);

CREATE TABLE iman_16_jan_bank_feeder_transaction (
	transaction_id INT AUTO_INCREMENT PRIMARY KEY,
	sender INT,
	receiver INT,
	amount INT,
	transaction_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE iman_16_jan_bank_feeder_record (
	record_id INT AUTO_INCREMENT PRIMARY KEY,
	amount INT,
	user_id INT,
	record_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	record_type VARCHAR(50),
	FOREIGN KEY (user_id) REFERENCES iman_16_jan_bank_feeder_user(id)
)

CREATE PROCEDURE deposit_action(IN user_id_param INT, IN amount_param INT)
BEGIN
    START TRANSACTION;

    UPDATE iman_16_jan_bank_feeder_user SET balance = balance + amount_param
    WHERE id = user_id_param;

    INSERT INTO iman_16_jan_bank_feeder_record (user_id, record_type, amount)
    VALUES (user_id_param, 'deposit', amount_param);

    COMMIT;
END;

CREATE PROCEDURE withdraw_action(IN user_id_param INT, IN amount_param INT)
BEGIN
    START TRANSACTION;

    UPDATE iman_16_jan_bank_feeder_user SET balance = balance - amount_param
    WHERE id = user_id_param;

    INSERT INTO iman_16_jan_bank_feeder_record (user_id, record_type, amount)
    VALUES (user_id_param, 'withdraw', amount_param);

    COMMIT;
END;

CREATE PROCEDURE transfer_action(IN sender_id_param INT, IN receiver_id_param INT, IN amount_param INT)
BEGIN
	START TRANSACTION;

	UPDATE iman_16_jan_bank_feeder_user SET balance = balance + amount_param WHERE id = receiver_id_param;
	UPDATE iman_16_jan_bank_feeder_user SET balance = balance - amount_param - (amount_param * 0.02) WHERE id = sender_id_param;
	
	INSERT INTO iman_16_jan_bank_feeder_transaction (sender, receiver, amount)
	VALUES (sender_id_param, receiver_id_param, amount_param);

	IF (SELECT balance FROM iman_16_jan_bank_feeder_user WHERE id = sender_id_param) < 0 THEN
		ROLLBACK;
		SELECT 'Insufficient Fund' AS error_message;
	ELSE
		COMMIT;
	END IF;
END

CREATE PROCEDURE transfer_with_count_action(IN sender_id_param INT, IN receiver_id_param INT, IN amount_param INT, IN count_param INT)
BEGIN

	SET @counter = 0;
    SET @rollback_msg = 'Insufficient Fund';

	transfer_count_loop: LOOP
		
		START TRANSACTION;
	
		IF @counter = count_param THEN
			LEAVE transfer_count_loop;
		END IF;
		
		UPDATE iman_16_jan_bank_feeder_user SET balance = balance + amount_param WHERE id = receiver_id_param;
		UPDATE iman_16_jan_bank_feeder_user SET balance = balance - amount_param - (amount_param * 0.02) WHERE id = sender_id_param;
		
		INSERT INTO iman_16_jan_bank_feeder_transaction (sender, receiver, amount)
		VALUES (sender_id_param, receiver_id_param, amount_param);
	
		SET @counter = @counter + 1;
	
		IF (SELECT balance FROM iman_16_jan_bank_feeder_user WHERE id = sender_id_param) < 0 THEN
			ROLLBACK;
			SELECT CONCAT('Insufficient Fund on Transaction month ', @counter) AS error_message;
		ELSE
			COMMIT;
		END IF;

	END LOOP;
END

-- Each user creating their bank feeder acc

INSERT INTO iman_16_jan_bank_feeder_user (username, balance) VALUES ('Joko', 0);
INSERT INTO iman_16_jan_bank_feeder_user (username, balance) VALUES ('Bowo', 0);
INSERT INTO iman_16_jan_bank_feeder_user (username, balance) VALUES ('Amin', 0);
INSERT INTO iman_16_jan_bank_feeder_user (username, balance) VALUES ('Raka', 0);

-- User deposit into their bank acc

CALL deposit_action(1, 30000000);
CALL deposit_action(2, 43000000);
CALL deposit_action(3, 100000000);
CALL deposit_action(4, 18000000);


-- Bowo bayar utang ke Joko 12 jt 3 kali

CALL transfer_action(2, 1, 4000000);
CALL transfer_action(2, 1, 4000000);
CALL transfer_action(2, 1, 4000000);

-- Raka jual motor ke joko 14,5jt

CALL transfer_action(1, 4, 14500000);

-- Amin bagi2 duid 10jt

CALL transfer_action(3, 1, 10000000);
CALL transfer_action(3, 2, 10000000);
CALL transfer_action(3, 4, 10000000);

DROP PROCEDURE IF EXISTS transfer_with_count_action;
CALL transfer_with_count_action(2, 3, 30000000, 2);
CALL transfer_action(1, 5, 999999999);
SELECT * FROM iman_16_jan_bank_feeder_user ijbfu;

-- Nomor 5

SELECT ijbfu.id , ijbfu.username , SUM(ijbft.amount) AS keluaran FROM iman_16_jan_bank_feeder_transaction ijbft
JOIN iman_16_jan_bank_feeder_user ijbfu
ON ijbft.sender = ijbfu.id
GROUP BY ijbfu.id
ORDER BY keluaran DESC 
LIMIT 1;

-- Nomor 6
-- SELECT ijbfu.id , ijbfu.username , SUM(CASE WHEN ijbft.sender = ijbfu.id THEN ijbft.amount ELSE 0 END) AS total_sent , SUM(CASE WHEN ijbft.receiver = ijbfu.id THEN ijbft.amount ELSE 0 END) AS total_received
-- FROM iman_16_jan_bank_feeder_user ijbfu
-- LEFT JOIN iman_16_jan_bank_feeder_transaction ijbft
-- ON ijbfu.id = ijbft.sender OR ijbfu.id = ijbft.receiver
-- GROUP BY ijbfu.id , ijbfu.username;

-- Nomor 6

SELECT ijbfu.id , ijbfu.username , COUNT(DISTINCT sent.transaction_id) AS total_sent , COUNT(received.transaction_id) AS total_received
FROM iman_16_jan_bank_feeder_user ijbfu
LEFT JOIN iman_16_jan_bank_feeder_transaction sent
ON ijbfu.id = sent.sender
LEFT JOIN iman_16_jan_bank_feeder_transaction received
ON ijbfu.id = received.receiver 
GROUP BY ijbfu.id
ORDER BY total_sent DESC;

-- Nomor 6 Alter

SELECT ijbfu.username AS sender_name , ijbfu2.username AS receiver_name , COUNT(ijbft.transaction_id) AS transaction_count
FROM iman_16_jan_bank_feeder_user ijbfu
JOIN iman_16_jan_bank_feeder_transaction ijbft ON ijbfu.id = ijbft.sender
JOIN iman_16_jan_bank_feeder_user ijbfu2 ON ijbfu2.id = ijbft.receiver
GROUP BY ijbfu.username , ijbfu2.username
ORDER BY transaction_count DESC;

-- Nomor 7

SELECT ijbfu.username  , SUM(ijbft.amount * 0.02) AS cuan_bank FROM iman_16_jan_bank_feeder_transaction ijbft
JOIN iman_16_jan_bank_feeder_user ijbfu
ON ijbft.sender = ijbfu.id
GROUP BY ijbft.sender 
ORDER BY cuan_bank DESC;


SELECT * FROM iman_16_jan_bank_feeder_user ijbfu;
SELECT * FROM iman_16_jan_bank_feeder_transaction ijbft;
SELECT * FROM iman_16_jan_bank_feeder_record ijbfr;

DELETE FROM iman_16_jan_bank_feeder_user WHERE id = 6;
CALL deposit_action(1, 100); 

-- Reset Procedure
DROP PROCEDURE IF EXISTS transfer_action;

-- Reset Table
DELETE FROM iman_16_jan_bank_feeder_user;
ALTER TABLE iman_16_jan_bank_feeder_user AUTO_INCREMENT = 5;

DELETE FROM iman_16_jan_bank_feeder_transaction;
ALTER TABLE iman_16_jan_bank_feeder_transaction AUTO_INCREMENT = 0;

DELETE FROM iman_16_jan_bank_feeder_record;
ALTER TABLE iman_16_jan_bank_feeder_record AUTO_INCREMENT = 0;



