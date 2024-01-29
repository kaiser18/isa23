package com.ftn.isa23.company.controller;


import com.ftn.isa23.company.dto.StorageDTO;
import com.ftn.isa23.company.mapper.StorageMapper;
import com.ftn.isa23.company.service.impl.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/storage", produces = MediaType.APPLICATION_JSON_VALUE)
public class StorageController {
    private final StorageService service;

    @Autowired
    public StorageController(StorageService service) {
        this.service = service;
    }

    @GetMapping(value = "/getEquipmentByCompany", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<StorageDTO> getEquipmentByCompany(@RequestParam("c") Long companyId) {
        return StorageMapper.mapStoragesToListDTOs(service.getEquipmentByCompany(companyId));
    }

    @GetMapping(value = "/getAvailableEquipmentByCompany", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<StorageDTO> getAvailableEquipmentByCompany(@RequestParam("c") Long companyId) {
        return StorageMapper.mapStoragesToListDTOs(service.getAvailableEquipmentByCompany(companyId));
    }
}
