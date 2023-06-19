package com.feeder.feedersurvey.entity;


import com.feeder.feedersurvey.dto.FeederDetailDto;
import com.feeder.feedersurvey.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feeder_detail")
public class FeederDetail extends BaseEntity {

    @Column(name = "feeder_id",insertable = false, updatable = false)
    private Integer feederId;

    @JoinColumn(name = "feeder_id")
    @ManyToOne
    private Feeder feeder;


    @Column(name = "substation_id", insertable = false, updatable = false)
    private Integer substationId;
    @JoinColumn(name = "substation_id")
    @ManyToOne
    private Substation substation;

    @Column(name = "transformer_location_name")
    private String transformerLocationName;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "existing_tc_capacity")
    private String existingTcCapacity;
    @Column(name = "propose_tc_capacity")
    private String proposeTcCapacity;
    @Column(name = "propose_RMU_type")
    private String proposeRMUType;
    @Column(name = "remarks")
    private String remarks;


    public FeederDetailDto toDto() {
        return FeederDetailDto.builder().id(this.getId())
                .feederName(this.getFeeder().getName())
                .feederId(this.getFeederId())
                .substationName(this.getSubstation().getName())
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
