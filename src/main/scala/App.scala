import org.apache.spark.sql.{DataFrame, SparkSession}

object App {

  def formatter(str: String): String = {
    val header = str.split("[ _]+").map(_.toLowerCase().capitalize).mkString("")
    return header
  }

  def formatDataFrame(df: DataFrame) : DataFrame = {
    val fieldNames = df.schema.fieldNames
    fieldNames.foldLeft(df){
      (df: DataFrame, name: String) =>
        df.withColumnRenamed(name, formatter(name))
    }
  }

  def formatHeader(str: String): Array[String] = {
    val headers = str.split(",").map(formatter)
    return headers
  }

  def process(spark: SparkSession, path: String) = {
    import spark.implicits._
    val df = spark.read.option("header", true).csv(path)
    val precipitationSample = formatDataFrame(df)
    precipitationSample.show(false)
  }

  def main(args: Array[String]): Unit = {
    val path = "/home/kings/Development/dsti20-challenge1/PRECIP_HLY_sample_csv.csv"
    val spark: SparkSession = SparkSession.builder().appName("CSV Parser").master("local[*]").getOrCreate()
    val df = process(spark, path)
    spark.close()

  }

}
