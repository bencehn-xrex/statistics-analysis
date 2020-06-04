package io.xrex.statistics.analysis.mapper;

import io.xrex.statistics.analysis.dto.OwnerRevenueDto;
import io.xrex.statistics.analysis.vo.OwnerRevenueVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel="spring")
public interface OwnerRevenueMapper {

    @Mappings({
            @Mapping(source = "id.feeCoin", target = "feeCoin"),
            @Mapping(source = "revenue", target = "revenue")
    })
    OwnerRevenueVo dto2Vo(OwnerRevenueDto ownerRevenueDto);
}
