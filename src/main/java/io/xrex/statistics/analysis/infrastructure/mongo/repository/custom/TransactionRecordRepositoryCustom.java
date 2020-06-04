package io.xrex.statistics.analysis.infrastructure.mongo.repository.custom;

import io.xrex.statistics.analysis.dto.OwnerRevenueDto;
import io.xrex.statistics.analysis.dto.TradnVolumeDto;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface TransactionRecordRepositoryCustom {

    Flux<TradnVolumeDto> findTradnVolume(Long exchangeUserId, LocalDateTime starDate, LocalDateTime endDate, int page, int size);

    Flux<OwnerRevenueDto> findRevenue(Long exchangeUserId, LocalDateTime starDate, LocalDateTime endDate, int page, int size);
}
