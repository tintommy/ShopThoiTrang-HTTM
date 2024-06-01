package ptithcm.DesignPattern.Strategy.Statistics;

import javax.transaction.Transactional;


public class StatisticsContext {

   public StatisticsInterface strategy;

    public void setStrategy(StatisticsInterface strategy) {
        this.strategy = strategy;
    }

    public Object executeStrategy() {
    	return strategy.collectStatistics();
    }
}
