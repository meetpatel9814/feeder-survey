package com.feeder.feedersurvey.dto;

import com.feeder.feedersurvey.dto.base.BaseDto;
import com.feeder.feedersurvey.entity.FeederDetail;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FeederDetailDto extends BaseDto {

    private String feederName;
    private Integer feederId;
    private String substationName;
    private Integer substationId;
    private String transformerLocationName;
    private String latitude;
    private String longitude;
    private String existingTcCapacity;
    private String proposeTcCapacity;
    private String proposeRMUType;
    private String remarks;


    public FeederDetail toEntity() {
        return FeederDetail.builder().id(this.getId())
                .feederId(this.getFeederId())
                .substationId(this.getSubstationId())
                .transformerLocationName(this.getTransformerLocationName())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .existingTcCapacity(this.getExistingTcCapacity())
                .proposeTcCapacity(this.getProposeTcCapacity())
                .proposeRMUType(this.getProposeRMUType())
                .remarks(this.getRemarks()).build();
    }
}
