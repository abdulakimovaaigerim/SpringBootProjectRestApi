package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.company.CompanyResponse;
import peaksoft.entities.Company;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select new peaksoft.dto.company.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber) from Company c")
    List<CompanyResponse> getAllCompanies();

    @Query("select new peaksoft.dto.company.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber) from Company c where c.id=:companyId")
    CompanyResponse getCompanyById(Long companyId);

    boolean existsByName(String name);

}
