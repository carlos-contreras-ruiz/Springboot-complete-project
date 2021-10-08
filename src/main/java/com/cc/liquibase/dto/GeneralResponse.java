package com.cc.liquibase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse <T>{
    public String HttpCode;
    public HttpStatus status;
    public T body;
    public boolean error;
}
