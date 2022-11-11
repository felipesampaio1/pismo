package br.com.pismo.challenge.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum OperationTypeEnum {

    COMPRA_A_VISTA(1, true),
    COMPRA_PARCELADA(2, true),
    SAQUE(3, true),
    PAGAMENTO(4, false);

    private final int id;
    private boolean negate;

    public static Optional<OperationTypeEnum> byId(int id) {
        return Arrays.stream(values())
                .filter(type -> type.id == id)
                .findFirst();
    }
}
