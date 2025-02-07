package com.example.internportfoliotask2.controller;

import com.example.internportfoliotask2.model.payload.EducationPayload;
import com.example.internportfoliotask2.model.response.EducationResponse;
import com.example.internportfoliotask2.service.education.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationController {
    private final EducationService educationService;

    @GetMapping("/getEducation")
    public List<EducationResponse> getAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return educationService.getAll(token);
    }

    @GetMapping
    public EducationResponse getById(@RequestParam(value = "id") Long id, String token) {
        return educationService.getById(id, token);
    }

    @PutMapping
    public void updateEducation(@RequestParam(value = "id") Long id,
                                @RequestBody EducationPayload educationPayload,
                                @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        educationService.updateEducation(id, educationPayload, token);
    }

    @PostMapping
    public EducationResponse save(@RequestBody EducationPayload educationPayload,
                                  @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return educationService.save(educationPayload, token);
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "id") Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        educationService.delete(id, token);
    }

}
