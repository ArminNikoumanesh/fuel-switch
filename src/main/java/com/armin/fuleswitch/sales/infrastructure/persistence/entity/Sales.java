package com.armin.fuleswitch.sales.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ir.niopdc.quota.fuelswitch.common.basics.entity.AbstractMiddleEntity;
import ir.niopdc.quota.fuelswitch.common.basics.entity.listener.SignIgnore;
import ir.niopdc.quota.fuelswitch.common.convertors.StatusConvertor;
import ir.niopdc.quota.fuelswitch.common.enums.Status;
import ir.niopdc.quota.fuelswitch.modules.sales.infrastructure.persistence.converter.SalesTypeConvertor;
import ir.niopdc.quota.fuelswitch.modules.sales.infrastructure.persistence.enums.SalesType;
import ir.niopdc.quota.fuelswitch.modules.salesrule.infrastructure.persistence.entity.SalesRule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "fsw_cr_sales",
		indexes = { @Index(name = "IDX_FSW_SALES_TRACK_CODE", columnList = "tracking_code"),
				@Index(name = "IDX_FSW_SALES_TYPE", columnList = "type"),
				@Index(name = "IDX_FSW_SALES_STATUS", columnList = "status") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NoArgsConstructor
public class Sales extends AbstractMiddleEntity {

	@Column(name = "title")
	private String title;

	@Column(name = "type")
	@Convert(converter = SalesTypeConvertor.class)
	private SalesType type;

	@Column(name = "value")
	private String value;

	@Column(name = "status")
	@Convert(converter = StatusConvertor.class)
	private Status status;

	@Column(name = "is_deleted")
	private Boolean isDeleted = false;

	@Column(name = "sales_rule_id")
	private long salesRuleId;

	@SignIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sales_rule_id", nullable = false, updatable = false, insertable = false)
	private SalesRule salesRule;

}
