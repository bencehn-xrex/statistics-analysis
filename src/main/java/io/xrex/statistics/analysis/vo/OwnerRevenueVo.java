package io.xrex.statistics.analysis.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.Decimal128;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRevenueVo {

    String feeCoin;

    Decimal128 revenue;
}
