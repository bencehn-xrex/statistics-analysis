package io.xrex.statistics.analysis.infrastructure.mongo.repository;

import io.xrex.statistics.analysis.dto.TradnVolumeDto;
import io.xrex.statistics.analysis.infrastructure.mongo.doucment.TransactionRecord;
import io.xrex.statistics.analysis.infrastructure.mongo.repository.custom.TransactionRecordRepositoryCustom;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Meta;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionRecordRepository extends ReactiveMongoRepository<TransactionRecord, String>, TransactionRecordRepositoryCustom {

    @Meta(allowDiskUse = true)
    @Aggregation(" { $group : {  _id : { side : $side, baseCoin : $baseCoin,  quoteCoin : $quoteCoin },  dealMoney : { $sum : $dealMoney }, dealVolume : { $sum : $dealMoney } } }")
    Flux<TradnVolumeDto> groupBySideAndBaseCoinAndQuoteCoin();
}

