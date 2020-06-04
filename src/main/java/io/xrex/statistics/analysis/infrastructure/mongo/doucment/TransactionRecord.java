package io.xrex.statistics.analysis.infrastructure.mongo.doucment;

import io.xrex.statistics.analysis.infrastructure.mongo.enums.Side;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "transaction_record")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRecord {

    @Id
    private String id;
    private Long orderId;
    private Side side;
    private Decimal128 dealVolume;
    private Decimal128 dealMoney;
    private String ownerId;
    private Long ownerExchangeUserId;
    private String tradnId;
    private LocalDateTime transactionTime;
    private String baseCoin;
    private String quoteCoin;
    private String feeCoin;
    private Decimal128 ownerFee;
    private Decimal128 xrexFee;
    private Double ownerRate;
    private Double xrexRate;
}

