package br.com.pismo.challenge.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum OperationTypeEnum {

    COMPRA_A_VISTA(1),
    COMPRA_PARCELADA(2),
    SAQUE(3),
    PAGAMENTO(4);


    private final int id;

    public static Optional<OperationTypeEnum> byId(int id) {
        return Arrays.stream(values())
                .filter(type -> type.id == id)
                .findFirst();
    }
}
