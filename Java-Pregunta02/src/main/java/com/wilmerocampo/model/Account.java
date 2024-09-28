package com.wilmerocampo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    @JsonProperty("estado")
    private Boolean status;

    @JsonProperty("nro_cuenta")
    private Integer accountNumber;

    @JsonProperty("saldo")
    private Double balance;

    @JsonProperty("banco")
    private String bankName;
}
