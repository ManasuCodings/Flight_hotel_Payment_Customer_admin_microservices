package com.administratorService.service;

import com.administratorService.dto.AdministratorDto;
import com.administratorService.entity.Administrator;
import com.administratorService.exception.CustomException;
import com.administratorService.repository.AdministratorRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdministratorRepo administratorRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public AdministratorDto createOneAdmin(AdministratorDto administratorDto) {

        Administrator map = modelMapper.map(administratorDto, Administrator.class);
        map.setPassword(passwordEncoder.encode(map.getPassword()));
        Administrator save = administratorRepo.save(map);
        AdministratorDto map1 = modelMapper.map(save, AdministratorDto.class);
        return map1;
    }

    @Override
    public AdministratorDto getAdminBy_Email(String email) {
        Administrator admin = administratorRepo.findAdminByEmail(email).orElseThrow(() -> new CustomException("no such Admin with email "+email));
        AdministratorDto map = modelMapper.map(admin, AdministratorDto.class);
        return map;
    }
}
