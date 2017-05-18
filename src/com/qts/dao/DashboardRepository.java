package com.qts.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.qts.model.Statistics;
 
@Repository
public class DashboardRepository {
  private static final Logger logger = LoggerFactory.getLogger(DashboardRepository.class);
  public static final String COLLECTION_NAME = "statistics";
 
  @Autowired
  private MongoTemplate mongoTemplate;
 
  public void addStatistics(Statistics stats) {
    if (!mongoTemplate.collectionExists(Statistics.class)) {
      mongoTemplate.createCollection(Statistics.class);
    }
    
   	logger.info("Inside addStatistics()...");
   	mongoTemplate.insert(stats, COLLECTION_NAME);
  }
 
  public Statistics getStatisticsByID(String id) {
	  Statistics stats =  mongoTemplate.findOne(
		Query.query(Criteria.where("_id").is(id)), Statistics.class, COLLECTION_NAME);
	  
	  logger.info("Inside getStatisticsByID (" + id +"), stats=" + stats);
	  return stats;
  }
  
  public Statistics deleteStatistics(String id) {
	Statistics stats = mongoTemplate.findOne(
      Query.query(Criteria.where("_id").is(id)), Statistics.class, COLLECTION_NAME);
	
    mongoTemplate.remove(stats, COLLECTION_NAME);
 
    return stats;
  }
 
  public Statistics updateStatistics(String id, Statistics stats) {
	Statistics myStats = mongoTemplate.findOne(
	  Query.query(Criteria.where("_id").is(id)), Statistics.class, COLLECTION_NAME);
    
    if (myStats != null) {
    	logger.info("Inside updateStatistics(), updating record...");
    	myStats.setId(id);
    	myStats.setTodayHeading(stats.getTodayHeading());
    	myStats.setTodayCount(stats.getTodayCount());
    	myStats.setTodayAverage(stats.getTodayAverage());
    	myStats.setTodayAverageSubheading(stats.getTodayAverageSubheading());
    	myStats.setOnboardedHeading(stats.getOnboardedHeading());
    	myStats.setOnboardedCount(stats.getOnboardedCount());
    	myStats.setOnboardedSubheading(stats.getOnboardedSubheading());
    	myStats.setSignupsHeading(stats.getSignupsHeading());
    	myStats.setSignupsCount(stats.getSignupsCount());
    	myStats.setSignupsSubheading(stats.getSignupsSubheading());

        mongoTemplate.save(myStats, "Statistics");    
        return stats;
        
    } else {
    	logger.info("Inside updateStatistics(), unable to update record...");
        return null;
    } 
  }
}