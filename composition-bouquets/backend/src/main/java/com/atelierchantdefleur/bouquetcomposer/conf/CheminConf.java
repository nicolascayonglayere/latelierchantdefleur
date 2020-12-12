package com.atelierchantdefleur.bouquetcomposer.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "modele")
@Data
public class CheminConf {

    private String pdfTemplate;
}
