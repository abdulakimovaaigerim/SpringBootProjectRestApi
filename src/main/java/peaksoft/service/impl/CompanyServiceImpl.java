package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import peaksoft.dto.company.CompanyRequest;
import peaksoft.dto.company.CompanyResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entities.Company;
import peaksoft.repository.CompanyRepository;
import peaksoft.service.CompanyService;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @SneakyThrows
    @Override
    public SimpleResponse saveCompany(CompanyRequest companyRequest) {
        try {
            Company company = new Company();
            company.setName(companyRequest.name());
            company.setCountry(companyRequest.country());
            company.setAddress(companyRequest.address());
            company.setPhoneNumber(companyRequest.phoneNumber());

            if (companyRepository.existsByName(company.getName())) {
                throw new IOException("Company name and phoneNumber must be unique");
            }

            companyRepository.save(company);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY SAVED")
                    .message("Company with id: " + company.getName() + " is saved!")
                    .build();

        } catch (IOException e) {
            return SimpleResponse.builder()
                    .status("ERROR")
                    .message("Failed to save company: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    @Override
    public SimpleResponse updateCompany(Long companyId, CompanyRequest companyRequest) {
        try {
            Company company = companyRepository.findById(companyId).orElseThrow(() ->
                    new NoSuchElementException("Company with id: " + companyId + " does not exist"));

            company.setName(companyRequest.name());
            company.setCountry(companyRequest.country());
            company.setAddress(companyRequest.address());
            company.setPhoneNumber(companyRequest.phoneNumber());
            companyRepository.save(company);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY UPDATED")
                    .message("Company with id: " + company.getName() + " is updated")
                    .build();

        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status("ERROR")
                    .message("Failed to update company: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public CompanyResponse getCompanyById(Long companyId) {
        try {
            companyRepository.findById(companyId).orElseThrow(() ->
                    new NoSuchElementException("Company with id: " + companyId + " is not found!"));
            return companyRepository.getCompanyById(companyId);

        } catch (Exception e) {
            throw new RuntimeException("Failed to get company: " + e.getMessage());
        }
    }

    @Override
    public SimpleResponse deleteCompanyById(Long companyId) {
        try {
            companyRepository.deleteById(companyId);
            return SimpleResponse.builder().status("SUCCESSFULLY DELETED!")
                    .message("Company with id: " + companyId + " is deleted!").build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete company: " + e.getMessage());
        }
    }
}
