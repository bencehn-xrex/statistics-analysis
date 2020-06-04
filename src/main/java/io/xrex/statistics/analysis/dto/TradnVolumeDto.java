package io.xrex.statistics.analysis.dto;

import io.xrex.statistics.analysis.infrastructure.mongo.enums.Side;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradnVolumeDto {
    @Id
    GroupId id;

    Decimal128 dealMoney;

    Decimal128 dealVolume;

    @Data
    @AllArgsConstructor
    public static class GroupId {
        Side side;

        String baseCoin;

        String quoteCoin;
    }
}
