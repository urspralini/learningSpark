package com.prabhu.spark.basics

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkContext, SparkConf}
/**
 * <p>
 *
 * </p>
 * @author Prabhu R Babu
 *         (11/13/15 4:27 AM)
 */
object SparkSQLJson {

  def main (args: Array[String]) {
    //initialize spark configuration
    val conf = new SparkConf().setMaster("local")
      .setAppName(SparkApp.getClass.getSimpleName)
    //initialize spark context
    val sc = new SparkContext(conf)
    //initialize hvie context to read json file
    val hiveContext = new HiveContext(sc)
    //get tweets.json file resource path
    val tweetsFile: String = getClass.getResource("/tweets.json").getPath
    //load json file into RDD of Row objects
    val tweets = hiveContext.jsonFile(tweetsFile)
    //register that RDD of Row objects as a table in order to query using sql
    tweets.registerTempTable("tweets")
    //sql query to select specific fields from that RDD(aka tweets table)
    val results = hiveContext.sql("SELECT user.name, text FROM tweets")
    results.cache()
    println("user.name:" + results.first().getString(0))
    println("text:" + results.first().getString(0))
  }
}
