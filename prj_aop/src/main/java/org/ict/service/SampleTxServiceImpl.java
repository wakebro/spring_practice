package org.ict.service;

import org.ict.mapper.SampleMapper;
import org.ict.mapper.SampleMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;


@Service
@Log4j
public class SampleTxServiceImpl implements SampleTxService {

	@Autowired
	private SampleMapper mapper1;
	
	@Autowired
	private SampleMapper2 mapper2;
	
	@Transactional		
	// 이걸 붙이면 메서드 내 모든 쿼리문이 처리되었을 때만 최종 처리된다.
	// 하나라도 처리가 안될 경우 메서드 실행 실패
	@Override
	public void addData(String value) {
		log.info("mapper1...............");
		mapper1.insertCol1(value);
		log.info("mapper2...............");
		mapper2.insertCol1(value);
		

	}

}
