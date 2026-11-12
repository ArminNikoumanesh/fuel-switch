package com.armin.fuleswitch.sales.application.service;

import ir.niopdc.quota.fuelswitch.modules.sales.infrastructure.persistence.entity.Sales;
import ir.niopdc.quota.fuelswitch.modules.sales.infrastructure.persistence.enums.SalesType;
import ir.niopdc.quota.fuelswitch.modules.sales.infrastructure.persistence.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalesService {

	private final SalesRepository repository;

	@Transactional(readOnly = true)
	public Optional<Integer> findRefuelingIntervalSecond(Long stationId) {

		return repository.findByStationId(stationId, SalesType.REFUELING_INTERVAL_SECOND)
			.map(Sales::getValue)
			.map(Integer::parseInt);
	}

	@Transactional(readOnly = true)
	public Optional<Sales> findBySalesRuleIdAndType(long salesRuleId, SalesType type) {
		return repository.findBySalesRuleIdAndType(salesRuleId, type);
	}

	public Sales save(Sales sales) {
		return this.repository.save(sales);
	}

}
