package com.kairosds.cursospb2.biblioteca.kafka.producer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrestamoTopicDTO {

    private String bibliotca;
    private Integer creditos;
}
