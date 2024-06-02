package ptithcm.designpattern.Strategy.Statistics;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ptithcm.entity.NguoiDungEntity;
import ptithcm.service.nguoiDungService;


public class StatisticsContext {
	
	@Autowired
	private nguoiDungService nguoiDungService;
	

   public StatisticsInterface strategy;
   
   

    public void setStrategy(StatisticsInterface strategy) {
        this.strategy = strategy;
    }

    public Object executeStrategy() {
    	return strategy.collectStatistics();
    }
    
  
}
