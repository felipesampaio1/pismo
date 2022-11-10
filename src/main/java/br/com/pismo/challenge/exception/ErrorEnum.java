package br.com.pismo.challenge.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    ACCOUNT_NOT_FOUND("Conta Não Encontrada"),
    OPERATIONAL_TYPE_NOT_FOUND("Operational Type Não encontrado");

    private String message;
}
