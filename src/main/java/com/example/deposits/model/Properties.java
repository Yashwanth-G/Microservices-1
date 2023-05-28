package com.example.deposits.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@Schema(
        description = "Properties Model"
)
public class Properties {
    @Schema(
            description = "Message attribute"
    )
    private String msg;

    @Schema(
            description = "Build Version"
    )
    private String buildVersion;

    @Schema(
            description = "Mail Details"
    )
    private Map<String, String> mailDetails;

    @Schema(
            description = "Active Branches"
    )
    private List<String> activeBranches;
}
