package com.helio.models.database.generated

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import scalikejdbc._


class AcronymSpec extends Specification {

  "Acronym" should {

    val a = Acronym.syntax("a")

    "find by primary keys" in new AutoRollback {
      val maybeFound = Acronym.find("MyString", "MyString")
      maybeFound.isDefined should beTrue
    }
    "find by where clauses" in new AutoRollback {
      val maybeFound = Acronym.findBy(sqls.eq(a.acronym, "MyString"))
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = Acronym.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = Acronym.countAll()
      count should be_>(0L)
    }
    "find all by where clauses" in new AutoRollback {
      val results = Acronym.findAllBy(sqls.eq(a.acronym, "MyString"))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = Acronym.countBy(sqls.eq(a.acronym, "MyString"))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = Acronym.create(acronym = "MyString", definition = "MyString")
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = Acronym.findAll().head
      // TODO modify something
      val modified = entity
      val updated = Acronym.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = Acronym.findAll().head
      val deleted = Acronym.destroy(entity) == 1
      deleted should beTrue
      val shouldBeNone = Acronym.find("MyString", "MyString")
      shouldBeNone.isDefined should beFalse
    }
    "perform batch insert" in new AutoRollback {
      val entities = Acronym.findAll()
      entities.foreach(e => Acronym.destroy(e))
      val batchInserted = Acronym.batchInsert(entities)
      batchInserted.size should be_>(0)
    }
  }

}
