# helio-http4s

This is a basic Http Service using http4s, a popular functional Scala library https://http4s.org/v0.21/. 
Within the HttpService are some basic functions that should give a developer a good starting point for how to implement
simple endpoints. For additional documentation see https://http4s.org/v0.21/, which covers a wide variety of options
that may or may not be included in this challenge

Beyond the HTTP library there are 2 more key libraries:

circe - a functional Scala JSON serializer/deserializer found here: https://circe.github.io/circe/

ScalikeJDBC - a Scala DB access library found here: http://scalikejdbc.org/

Challenge:

The purpose of this challenge is to cover the basic tools and methodologies of Scala employed at Helio Argos.
These are some of the more simple tasks, but they can be an added challenge if the developer is 
not familiar with Scala or FP in general. It's also a good baseline as well as exposure, as production code can/will 
often be more complex than this.

Using http4s, circe, and ScalikeJDBC, and the tables/data used in the first step, create the following:

### Project Notes:
The existing endpoints should provide some good context/examples of how to read from a database, serialize JSON, 
as well as access a post body. Some other items may be found in the library or documentation itself.

ScalikeJDBC has a Model Generate function that produces a full DAO like the one Acronym one found in
com.helio.models.database.generated. To use this run this projects sbt shell and run:

`scalikejdbcGen <tableName>` will generate a similar DAO class for you. It defaults to the `fda` schema, and the 
database properties can be found under `project/scalaikejdbc.properties` - one might need to update these based
on the database info supplied

`config.properties` contains all the runtime database config, and will also need to be updated based on the db config.
