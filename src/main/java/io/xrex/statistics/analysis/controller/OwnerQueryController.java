package io.xrex.statistics.analysis.controller;

import io.xrex.statistics.analysis.application.service.OwnerQueryService;
import io.xrex.statistics.analysis.vo.OwnerRevenueVo;
import io.xrex.statistics.analysis.vo.TradnVolumeVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController(value = "owners")
public class OwnerQueryController {

    private final OwnerQueryService ownerQueryService;

    public OwnerQueryController(OwnerQueryService ownerQueryService) {
        this.ownerQueryService = ownerQueryService;
    }

    @GetMapping(value = "/search/tradn-volume")
    public Flux<TradnVolumeVo> findTradnVolume(@RequestParam(name = "exchange_user_id") Long exchangeUserId,
                                               @RequestParam(name = "star_date") String starDate,
                                               @RequestParam(name = "end_date") String endDate,
                                               @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                               @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return ownerQueryService.findTradnVolume(exchangeUserId, starDate, endDate, page, size);
    }

   @GetMapping(value = "search/revenue")
   public Flux<OwnerRevenueVo> findRevenue(@RequestParam(name = "exchange_user_id") Long exchangeUserId,
                                           @RequestParam(name = "star_date") String starDate,
                                           @RequestParam(name = "end_date") String endDate,
                                           @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                           @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
       return ownerQueryService.findRevenue(exchangeUserId, starDate, endDate, page, size);
   }

}
