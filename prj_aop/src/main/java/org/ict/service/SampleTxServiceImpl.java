package org.ict.service;

import org.ict.mapper.SampleMapper;
import org.ict.mapper.SampleMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;


@Service
@Log4j
public class SampleTxServiceImpl implements SampleTxService {

	@Autowired
	private SampleMapper mapper1;
	
	@Autowired
	private SampleMapper2 mapper2;
	
	@Override
	public void addData(String value) {
		log.info("mapper1...............");
		mapper1.insertCol1(value);
		log.info("mapper2...............");
		mapper2.insertCol1(value);
		

	}

}
