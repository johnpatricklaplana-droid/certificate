package john.patrick.laplana.dto;

import java.util.List;

public record SchoolWithAdminDto(
    SchoolDto school,
    List<SchoolAdminDto> schoolAdmin
) {}
