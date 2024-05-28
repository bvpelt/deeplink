# Documentation

This application saves a toestand from a webapplication. And makes it possible to retrieve the toestand based on a known key.

Additionally when saving the toestand a timestamp created is set to be able to remove old entries after a specific time.

When a toestand is already known, the identification of that entry is returned and the created timestamp is updated.

# Execution
## Build
```bash
cd <root>
mvn clean package
```

## Run
```bash
mvn spring-boot:run
```
After startup the service is available at http://localhost:8082/toestanden

A swagger page is available at http://localhost:8082/swagger-ui/index.html

## Add content
As an example in the scripts directory there is a file [sendtoestand.bash](../scripts/sendtoestand.bash) which uses curl to send an entry into the deeplink database.
When sending the same request multiple times, the same identification will be returned.

Another way is to start intellij and from within the httptests directory there is a file [test.http](../httptests/test.http) with some entries.

## Database
After adding some content to the database the information can be viewed using (see https://www.postgresql.org/docs/current/functions-json.html)

```sql
-- get content from the _embedded json element for all records
select content->>'_embedded' from deeplink;

-- get id and content from the _embedded json element for all records
select id, content->>'_embedded' from deeplink;

-- get id en plan from json content for all records
select id, content#>'{_embedded,plan}' from deeplink;

-- get id, plan id from json content for all records
select id, content#>'{_embedded,plan,id}' from deeplink;
```