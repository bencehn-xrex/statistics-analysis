package io.xrex.statistics.analysis.infrastructure.mongo.repository.custom.impl;

import io.xrex.statistics.analysis.dto.OwnerRevenueDto;
import io.xrex.statistics.analysis.dto.TradnVolumeDto;
import io.xrex.statistics.analysis.infrastructure.mongo.repository.custom.TransactionRecordRepositoryCustom;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public class TransactionRecordRepositoryImpl implements TransactionRecordRepositoryCustom {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    private static final String DEAL_MONEY = "dealMoney";
    private static final String COLLECTION_NAME = "transaction_record";

    public TransactionRecordRepositoryImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Flux<TradnVolumeDto> findTradnVolume(Long exchangeUserId, LocalDateTime starDate, LocalDateTime endDate, int page, int size) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("ownerExchangeUserId").is(exchangeUserId)
                .andOperator(Criteria.where("transactionTime").gte(starDate), Criteria.where("transactionTime").lte(endDate))
        );
        GroupOperation groupOperation = Aggregation.group("side", "baseCoin", "quoteCoin").sum(DEAL_MONEY).as(DEAL_MONEY).sum("dealVolume").as("dealVolume");
        SkipOperation skipOperation = Aggregation.skip((long) (page - 1) * size);
        LimitOperation limitOperation = Aggregation.limit(size);
        SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, DEAL_MONEY);
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation, skipOperation, limitOperation, sortOperation)
                .withOptions(Aggregation.newAggregationOptions().allowDiskUse(true).build());
        return reactiveMongoTemplate.aggregate(aggregation, COLLECTION_NAME, TradnVolumeDto.class);
    }

    @Override
    public Flux<OwnerRevenueDto> findRevenue(Long exchangeUserId, LocalDateTime starDate, LocalDateTime endDate, int page, int size) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("ownerExchangeUserId").is(exchangeUserId)
                .andOperator(Criteria.where("transactionTime").gte(starDate), Criteria.where("transactionTime").lte(endDate))
        );
        GroupOperation groupOperation = Aggregation.group("feeCoin").sum("ownerFee").as("revenue");
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation)
                .withOptions(Aggregation.newAggregationOptions().allowDiskUse(true).build());
        return reactiveMongoTemplate.aggregate(aggregation, COLLECTION_NAME, OwnerRevenueDto.class);
    }
}
