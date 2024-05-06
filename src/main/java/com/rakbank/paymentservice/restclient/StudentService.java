package com.rakbank.paymentservice.restclient;


import com.rakbank.paymentservice.restclient.dto.StudentDto;

import java.util.Optional;

public interface StudentService {
    Optional<StudentDto> getStudentsById(Long id);

}
