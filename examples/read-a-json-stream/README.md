# Read a JSON Stream

Read a JSON stream using the Data Pipeline Library.

## How to run the example
1. Clone the repo.
2. Download the Data Pipeline jar at [North Concepts Inc.][1]
3. Place the **NorthConcepts-DataPipeline-x.x.x.jar** under the */libs* folder.
4. Place the license file under the */src/main/resources* folder.
5. Execute **mvn clean** and then **mvn compile** at the project root directory.
6. Run **mvn exec:java**.

[1]: https://northconcepts.com/pricing/ "Data Pipeline Download"

## Output
Looks something like this:
```sh
-----------------------------------------------
0 - Record (MODIFIED) {
    0:[symbol]:STRING=[MSFT]:String
    1:[exchange]:STRING=[NASDAQ]:String
    2:[price]:DOUBLE=[72.7]:Double
    3:[change]:DOUBLE=[0.0]:Double
}

-----------------------------------------------
1 - Record (MODIFIED) {
    0:[symbol]:STRING=[ORCL]:String
    1:[exchange]:STRING=[NYSE]:String
    2:[price]:DOUBLE=[49.93]:Double
    3:[change]:DOUBLE=[0.0]:Double
}

-----------------------------------------------
2 - Record (MODIFIED) {
    0:[symbol]:STRING=[ADBE]:String
    1:[exchange]:STRING=[NASDAQ]:String
    2:[price]:DOUBLE=[146.49]:Double
    3:[change]:DOUBLE=[0.0]:Double
}

-----------------------------------------------
3 records
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2.563 s
[INFO] Finished at: 2017-08-01T14:11:41+01:00
[INFO] Final Memory: 11M/164M
[INFO] ------------------------------------------------------------------------
```