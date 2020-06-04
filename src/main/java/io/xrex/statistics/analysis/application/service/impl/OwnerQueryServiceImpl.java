package io.xrex.statistics.analysis.application.service.impl;

import io.xrex.statistics.analysis.application.service.OwnerQueryService;
import io.xrex.statistics.analysis.infrastructure.mongo.repository.TransactionRecordRepository;
import io.xrex.statistics.analysis.mapper.OwnerRevenueMapper;
import io.xrex.statistics.analysis.mapper.TradnVolumeMapper;
import io.xrex.statistics.analysis.vo.OwnerRevenueVo;
import io.xrex.statistics.analysis.vo.TradnVolumeVo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OwnerQueryServiceImpl implements OwnerQueryService {

    private final TransactionRecordRepository transactionRecordRepository;

    private final TradnVolumeMapper tradnVolumeMapper;
    private final OwnerRevenueMapper ownerRevenueMapper;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public OwnerQueryServiceImpl(TransactionRecordRepository transactionRecordRepository, TradnVolumeMapper tradnVolumeMapper, OwnerRevenueMapper ownerRevenueMapper) {
        this.transactionRecordRepository = transactionRecordRepository;
        this.tradnVolumeMapper = tradnVolumeMapper;
        this.ownerRevenueMapper = ownerRevenueMapper;
    }

    public Flux<TradnVolumeVo> findTradnVolume(Long exchangeUserId, String starDate, String endDate, int page, int size) {
        LocalDateTime start = LocalDateTime.parse(starDate, FORMATTER);
        LocalDateTime end = LocalDateTime.parse(endDate, FORMATTER);
        return transactionRecordRepository.findTradnVolume(exchangeUserId, start, end, page, size)
                .flatMap(tradnVolumeDto -> Flux.just(tradnVolumeMapper.dto2Vo(tradnVolumeDto)));
    }

    @Override
    public Flux<OwnerRevenueVo> findRevenue(Long exchangeUserId, String starDate, String endDate, int page, int size) {
        LocalDateTime start = LocalDateTime.parse(starDate, FORMATTER);
        LocalDateTime end = LocalDateTime.parse(endDate, FORMATTER);
        return transactionRecordRepository.findRevenue(exchangeUserId, start, end, page, size)
                .flatMap(ownerRevenueDto -> Flux.just(ownerRevenueMapper.dto2Vo(ownerRevenueDto)));
    }
}
