package com.example.demo.service;

import com.example.demo.model.ApartmentUnit;
import java.util.List;

public interface ApartmentUnitService {

    List<ApartmentUnit> getByUser(Long userId);

}
