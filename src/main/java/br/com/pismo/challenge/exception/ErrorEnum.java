package br.com.pismo.challenge.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    ENTITY_NOT_FOUND("Entidade Não encontrada");

    private String title;
}
