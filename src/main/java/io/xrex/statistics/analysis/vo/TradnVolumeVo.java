package io.xrex.statistics.analysis.vo;

import io.xrex.statistics.analysis.infrastructure.mongo.enums.Side;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Decimal128;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradnVolumeVo {

    Side side;

    String baseCoin;

    String quoteCoin;

    Decimal128 dealMoney;

    Decimal128 dealVolume;
}
