package com.docker.spring;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dto implements Serializable {
    private Long id;
    private String name;
    private String family;
}
