name := "learningSpark"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq("org.apache.spark" %% "spark-core" % "1.5.1",
"javax.servlet" % "javax.servlet-api" % "3.0.1",
"org.apache.spark" %% "spark-sql" % "1.3.1",
"org.apache.spark" %% "spark-hive" % "1.3.1",
"mysql" % "mysql-connector-java" % "5.1.37")