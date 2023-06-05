package peaksoft.dto.company;

import lombok.Builder;

@Builder
public record CompanyRequest(
//        @Length(min = 2, max = 20, message = "Name's length should be between 2 and 20!")
        String name,
//        @NotNull(message = "Country shouldn't be null!")
        String country,

//        @NotNull(message = "Address shouldn't be null!")
        String address,
//        @Pattern(regexp = "\\+996\\d{9}", message = "Phone number should start with +996 and consist of 13 characters!")
        String phoneNumber
) {
}
