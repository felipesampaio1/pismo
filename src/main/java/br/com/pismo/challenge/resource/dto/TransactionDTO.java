package br.com.pismo.challenge.resource.dto;

import br.com.pismo.challenge.model.Transaction;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class TransactionDTO {

    @NotNull
    private Long accountId;

    @NotNull
    private Integer operationTypeId;

    @NotNull
    private BigDecimal amount;

    private BigDecimal availableCreditLimit;

    public static TransactionDTO fill(Transaction transaction) {
        return TransactionDTO.builder()
                .accountId(transaction.getAccount().getId())
                .operationTypeId(transaction.getOperationType().getId())
                .amount(transaction.getAmount())
                .availableCreditLimit(transaction.getAccount().getAvailableCreditLimit())
                .build();
    }
}
