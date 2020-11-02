package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataTablesMateriau {

    private Integer draw;
    private Integer recordsFiltered;
    private Integer recordsTotal;
    private List<MateriauRest> data;
}
