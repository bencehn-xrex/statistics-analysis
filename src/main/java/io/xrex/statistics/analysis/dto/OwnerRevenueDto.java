package io.xrex.statistics.analysis.dto;

import io.xrex.statistics.analysis.infrastructure.mongo.enums.Side;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.Decimal128;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRevenueDto {

    @Id
    RevenueId id;

    Decimal128 revenue;

    @Data
    @AllArgsConstructor
    public static class RevenueId {
        String feeCoin;
    }
}
