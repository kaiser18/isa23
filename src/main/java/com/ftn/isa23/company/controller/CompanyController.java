package com.ftn.isa23.company.controller;

import com.ftn.isa23.company.domain.Company;
import com.ftn.isa23.company.dto.CompanyDTO;
import com.ftn.isa23.company.mapper.CompanyMapper;
import com.ftn.isa23.company.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/company")
public class CompanyController {
    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) { this.service = service; }

    @GetMapping("/findAll")
    public ResponseEntity<?> getAll() {
        List<Company> bloodbanks = this.service.findAll();
        List<CompanyDTO> dtos = CompanyMapper.mapCompaniesToListDTOs(bloodbanks);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CompanyDTO getById(@RequestParam("id") Long id) throws ParseException {
        return CompanyMapper.mapCompanyToDTO(service.getById(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<CompanyDTO> getByName(@RequestParam("name") String name) throws ParseException{
        return service.findByName(name);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getByAddress", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<CompanyDTO> getByAddress(@RequestParam("address") String address) throws ParseException{
        return service.findByAddress(address);
    }
}
