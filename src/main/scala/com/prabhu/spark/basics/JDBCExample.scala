package com.prabhu.spark.basics

import java.sql.{ResultSet, DriverManager}

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkContext, SparkConf}

/**
 * <p>
 *
 * </p>
 * @author Prabhu R Babu
 *         (11/13/15 5:13 AM)
 */
object JDBCExample {

  def createConnection() = {
    Class.forName("com.mysql.jdbc.Driver").newInstance()
    DriverManager.getConnection("jdbc:mysql://localhost/test","root", "root")
  }

  def extractValues(r: ResultSet) = {
    (r.getInt(1), r.getString(2))
  }
  def main(args: Array[String]) {
    //initialize spark configuration
    val conf = new SparkConf().setMaster("local")
      .setAppName(JDBCExample.getClass.getSimpleName)
    //initialize spark context
    val sc = new SparkContext(conf)
    //assume we have a users table with fields (id, name)
    val data = new JdbcRDD(sc,
                           createConnection,
                           "SELECT id, name FROM users where ? <= id and id <= ?",
                           lowerBound = 1,
                           upperBound = 3,
                           numPartitions = 1,
                           extractValues)
    data.foreach(result => println(""+ result._1 + "," + result._2))
  }

}
