package com.helio.models.database.generated

import scalikejdbc._

case class Acronym(
  id: Int,
  acronym: String,
  definition: String) {

  def save()(implicit session: DBSession = Acronym.autoSession): Acronym = Acronym.save(this)(session)

  def destroy()(implicit session: DBSession = Acronym.autoSession): Int = Acronym.destroy(this)(session)

}


object Acronym extends SQLSyntaxSupport[Acronym] {

  override val schemaName = Some("fda")

  override val tableName = "acronym"

  override val columns = Seq("id", "acronym", "definition")

  def apply(a: SyntaxProvider[Acronym])(rs: WrappedResultSet): Acronym = apply(a.resultName)(rs)
  def apply(a: ResultName[Acronym])(rs: WrappedResultSet): Acronym = new Acronym(
    id = rs.get(a.id),
    acronym = rs.get(a.acronym),
    definition = rs.get(a.definition)
  )

  val a = Acronym.syntax("a")

  override val autoSession = AutoSession

  def find(id: Int)(implicit session: DBSession = autoSession): Option[Acronym] = {
    withSQL {
      select.from(Acronym as a).where.eq(a.id, id)
    }.map(Acronym(a.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[Acronym] = {
    withSQL(select.from(Acronym as a)).map(Acronym(a.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(Acronym as a)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[Acronym] = {
    withSQL {
      select.from(Acronym as a).where.append(where)
    }.map(Acronym(a.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[Acronym] = {
    withSQL {
      select.from(Acronym as a).where.append(where)
    }.map(Acronym(a.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(Acronym as a).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(
    id: Int,
    acronym: String,
    definition: String)(implicit session: DBSession = autoSession): Acronym = {
    withSQL {
      insert.into(Acronym).namedValues(
        column.id -> id,
        column.acronym -> acronym,
        column.definition -> definition
      )
    }.update.apply()

    Acronym(
      id = id,
      acronym = acronym,
      definition = definition)
  }

  def batchInsert(entities: collection.Seq[Acronym])(implicit session: DBSession = autoSession): List[Int] = {
    val params: collection.Seq[Seq[(Symbol, Any)]] = entities.map(entity =>
      Seq(
        Symbol("id") -> entity.id,
        Symbol("acronym") -> entity.acronym,
        Symbol("definition") -> entity.definition))
    SQL("""insert into acronym(
      id,
      acronym,
      definition
    ) values (
      {id},
      {acronym},
      {definition}
    )""").batchByName(params.toSeq: _*).apply[List]()
  }

  def save(entity: Acronym)(implicit session: DBSession = autoSession): Acronym = {
    withSQL {
      update(Acronym).set(
        column.id -> entity.id,
        column.acronym -> entity.acronym,
        column.definition -> entity.definition
      ).where.eq(column.id, entity.id)
    }.update.apply()
    entity
  }

  def destroy(entity: Acronym)(implicit session: DBSession = autoSession): Int = {
    withSQL { delete.from(Acronym).where.eq(column.id, entity.id) }.update.apply()
  }

}
