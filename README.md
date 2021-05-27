# helio-http4s

This is a basic Http Service using http4s a popular functional Scala library https://http4s.org/v0.21/. 
Within the HttpService are some basic functions that should give a developer a good starting point for how to implement
a basic endpoint. For additional documentation see https://http4s.org/v0.21/ which covers a wide variety of options
that may or may not be included in this challenge

Beyond the basic HTTP library there are 2 more key libraries:

circe - a functional Scala JSON serializer/deserializer found here: https://circe.github.io/circe/

ScalikeJDBC - a Scala DB access library found here: http://scalikejdbc.org/

Challenge:

Using http4s, circe, and ScalikeJDBC and the table/data used in the first step, to create the following:

# Tasks
1. A single get endpoint to retrieve a single HCP by an ID
2. A second get endpoint to retrieve a list/collection of HCPs by some sort of property/attribute (or multiple ones)
3. A post/create endpoint which adds an HCP entry to the database.

### Project Notes:
The existing endpoints should provide a good context/example of how to read from a database and/or
serialize JSON, as well as access a post body. Some other items may be found in the library or documentation itself.

ScalikeJDBC has a Model Generate function that produces a full DAO like the one Acronym one found in
com.helio.models.database.generated. To use this run this projects sbt shell and run:

`scalikejdbcGen <tableName>` and it will generate a similar DAO for you. It defaults to the `fda` schema, and the 
database properties can be found under `project/scalaikejdbc.properties` - one might need to update these based
on the database info supplied

`config.properties` contains all the runtime database config, and will also need to be updated based on the db config.
