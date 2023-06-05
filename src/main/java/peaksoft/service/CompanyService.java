package peaksoft.service;


import peaksoft.dto.company.CompanyRequest;
import peaksoft.dto.company.CompanyResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;

public interface CompanyService {

    SimpleResponse saveCompany(CompanyRequest companyRequest);

    List<CompanyResponse> getAllCompanies();

    SimpleResponse updateCompany(Long companyId, CompanyRequest companyRequest);

    CompanyResponse getCompanyById(Long companyId);

    SimpleResponse deleteCompanyById(Long companyId);

}
