package br.com.pismo.challenge.resource.dto;

import br.com.pismo.challenge.enumeration.OperationType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionDTO {

    private Long accountId;
    private OperationType operationTypeId;
    private BigDecimal amount;
}
