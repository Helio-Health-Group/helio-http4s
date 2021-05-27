package com.helio

import org.log4s.{Logger, getLogger}
import scalikejdbc.ConnectionPool

import java.util.Properties

object Database {
  private val log: Logger = getLogger
  private val CONFIG = "config.properties"

  def connect(): Either[Throwable, Unit] = {
    val inputOpt = Option(classOf[Database.type].getClassLoader.getResourceAsStream(CONFIG))
    inputOpt match {
      case Some(input) =>
        try {
          val prop = new Properties()
          //load a properties file from class path, inside static method
          prop.load(input)
          //get the property value and print it out
          val url = prop.getProperty("db.url")
          val user = prop.getProperty("db.user")
          val password = prop.getProperty("db.password")
          val driver = prop.getProperty("db.driver")
          log.info(s"Connecting to ${url} using ${user}")
          try {
            Class.forName(driver)
            ConnectionPool.singleton(url, user, password)
            Right()
          } catch {
            case e: Throwable => Left(e)
          }
        } catch {
          case e: Throwable => Left(e)
        } finally if (input != null) input.close()
      case None => Left(new RuntimeException(s"Unable to find $CONFIG"))
    }
  }
}
