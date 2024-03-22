package com.administratorService.service;

import com.administratorService.dto.AdministratorDto;


public interface AdminService {
    AdministratorDto createOneAdmin(AdministratorDto administratorDto);

    AdministratorDto getAdminBy_Email(String email);
}
