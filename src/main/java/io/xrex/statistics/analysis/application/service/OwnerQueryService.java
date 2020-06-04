package io.xrex.statistics.analysis.application.service;

import io.xrex.statistics.analysis.vo.OwnerRevenueVo;
import io.xrex.statistics.analysis.vo.TradnVolumeVo;
import reactor.core.publisher.Flux;

public interface OwnerQueryService {
    Flux<TradnVolumeVo> findTradnVolume(Long exchangeUserId, String starDate, String endDate, int page, int size);

    Flux<OwnerRevenueVo> findRevenue(Long exchangeUserId, String starDate, String endDate, int page, int size);
}
