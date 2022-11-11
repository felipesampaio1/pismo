# PISMO 3.0 Desafio Técnico

## Descrição do problema

Rotina de transações
```
Cada portador de cartão (cliente) possui uma conta com seus dados.

A cada operação realizada pelo cliente uma transação é criada e associada à sua
respectiva conta.

Cada transação possui um tipo (compra a vista, compra parcelada, saque ou pagamento),
um valor e uma data de criação.

Transações de tipo compra e saque são registradas com valor negativo, enquanto
transações de pagamento são registradas com valor positivo.
```

### Seguem entrada e saída esperados:

```
POST /accounts (criação de uma conta)
Request Body:
{
    "document_number": "12345678900"
}

GET /accounts/:accountId (consulta de informações de uma conta)
Response Body:
{
    "account_id": 1,
    "document_number": "12345678900"
}

POST /transactions (criação de uma transação)
Request Body:
{
    "account_id": 1,
    "operation_type_id": 4,
    "amount": 123.45
}
```

---

# Implementação

### Documentação

Para acesso ao swagger do serviço -> [swagger](http://localhost:8080/api/swagger-ui/index.html#/)

### Build

Para criação da imagem do serviço e download da imagem do postgres executar o docker compose presente no projeto.
Realizando a modificação para o profile `container` no **application.yml**

```dockerfile
docker-compose up --build
```

