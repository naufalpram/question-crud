package main.java.com.sgedts.tdp.constant;

public class QueryConst {
    public String createBankAccountQuery = "INSERT INTO iman_16_jan_bank_feeder_user (username, balance) VALUES (?, ?)";
    public String depositQuery = "CALL deposit_action(?, ?)";
    public String withdrawQuery = "CALL withdraw_action(?, ?)";
    public String transferQuery = "CALL transfer_action(?, ?, ?)";
    public String multipleTransferQuery = "CALL transfer_with_count_action(?, ?, ?, ?);";
    public String getUserWithBiggestTransactionValueQuery = """
                            SELECT ijbfu.username  , ijbft.amount FROM iman_16_jan_bank_feeder_transaction ijbft
                            JOIN iman_16_jan_bank_feeder_user ijbfu
                            ON ijbft.sender = ijbfu.id
                            ORDER BY amount DESC
                            LIMIT 1""";
    public String getTotalTransferReceiveCountEachUserQuery = """
                            SELECT ijbfu.id , ijbfu.username , COUNT(DISTINCT sent.transaction_id) AS total_sent , COUNT(received.transaction_id) AS total_received
                            FROM iman_16_jan_bank_feeder_user ijbfu
                            LEFT JOIN iman_16_jan_bank_feeder_transaction sent
                            ON ijbfu.id = sent.sender
                            LEFT JOIN iman_16_jan_bank_feeder_transaction received
                            ON ijbfu.id = received.receiver\s
                            GROUP BY ijbfu.id
                            ORDER BY total_sent DESC;""";
    public String getBankProfit = """
                            SELECT ijbfu.username  , SUM(ijbft.amount * 0.02) AS cuan_bank FROM iman_16_jan_bank_feeder_transaction ijbft
                            JOIN iman_16_jan_bank_feeder_user ijbfu
                            ON ijbft.sender = ijbfu.id
                            GROUP BY ijbft.sender\s
                            ORDER BY cuan_bank DESC;""";
}
