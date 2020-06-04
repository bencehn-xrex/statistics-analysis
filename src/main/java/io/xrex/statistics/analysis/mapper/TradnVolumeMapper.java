package io.xrex.statistics.analysis.mapper;

import io.xrex.statistics.analysis.dto.TradnVolumeDto;
import io.xrex.statistics.analysis.vo.TradnVolumeVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel="spring")
public interface TradnVolumeMapper {

    @Mappings({
            @Mapping(source = "id.side", target = "side"),
            @Mapping(source = "id.baseCoin", target = "baseCoin"),
            @Mapping(source = "id.quoteCoin", target = "quoteCoin"),
            @Mapping(source = "dealMoney", target = "dealMoney"),
            @Mapping(source = "dealVolume", target = "dealVolume")
    })
    TradnVolumeVo dto2Vo(TradnVolumeDto tradnVolumeDto);
}
