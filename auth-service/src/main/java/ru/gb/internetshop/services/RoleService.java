package ru.gb.internetshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.internetshop.entities.Role;
import ru.gb.internetshop.repositories.RoleRepository;


@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole(Long id){
        return roleRepository.findByName("ROLE_USER").get();
    }
}
