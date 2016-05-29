DateFormat benchmarks
=====================

Measure the performance of data formatting in java using [JMH](http://openjdk.java.net/projects/code-tools/jmh). Inspired by project of [danielmitterdorfer](https://github.com/danielmitterdorfer/benchmarking-experiments).

# Usage

```
git clone https://github.com/ccguyka/dateformat-benchmarks.git
cd benchmarking-experiments
mvn clean package
java -jar target/dateformat-benchmarks.jar
```

# Benchmarks

Uses following implementations to format dates:

* JDK SimpleDateFormat (is not thread safe)
** initialized per thread
** initialized for whole benchmark
** using ThreadLocal
* Apache Commons FastDateFormat
* Joda Time DateTimeFormat

These are the settings for the benchmark: 

* Fork count 5
* Warmup iterations 5
* Measurement iterations 5
* Thread count 1, 2, 4 and 8

# Output

Measured on my machine (~17min).
```
# Run complete. Total time: 00:17:35

Benchmark                                               Mode  Cnt  Score   Error  Units
DateFormatMicroBenchmark.measureCommonsFormat_1         avgt   25  1.169 ± 0.011  us/op
DateFormatMicroBenchmark.measureCommonsFormat_2         avgt   25  1.242 ± 0.038  us/op
DateFormatMicroBenchmark.measureCommonsFormat_4         avgt   25  2.344 ± 0.059  us/op
DateFormatMicroBenchmark.measureCommonsFormat_8         avgt   25  4.692 ± 0.080  us/op
DateFormatMicroBenchmark.measureJdkFormat_1             avgt   25  0.281 ± 0.009  us/op
DateFormatMicroBenchmark.measureJdkFormat_2             avgt   25  0.316 ± 0.014  us/op
DateFormatMicroBenchmark.measureJdkFormat_4             avgt   25  0.491 ± 0.032  us/op
DateFormatMicroBenchmark.measureJdkFormat_8             avgt   25  0.936 ± 0.046  us/op
DateFormatMicroBenchmark.measureJodaTimeFormat_1        avgt   25  0.147 ± 0.005  us/op
DateFormatMicroBenchmark.measureJodaTimeFormat_2        avgt   25  0.148 ± 0.004  us/op
DateFormatMicroBenchmark.measureJodaTimeFormat_4        avgt   25  0.229 ± 0.013  us/op
DateFormatMicroBenchmark.measureJodaTimeFormat_8        avgt   25  0.437 ± 0.013  us/op
DateFormatMicroBenchmark.measureSyncJdkFormat_1         avgt   25  0.319 ± 0.007  us/op
DateFormatMicroBenchmark.measureSyncJdkFormat_2         avgt   25  1.195 ± 0.036  us/op
DateFormatMicroBenchmark.measureSyncJdkFormat_4         avgt   25  2.612 ± 0.174  us/op
DateFormatMicroBenchmark.measureSyncJdkFormat_8         avgt   25  4.936 ± 0.489  us/op
DateFormatMicroBenchmark.measureThreadLocalJdkFormat_1  avgt   25  0.329 ± 0.018  us/op
DateFormatMicroBenchmark.measureThreadLocalJdkFormat_2  avgt   25  1.183 ± 0.046  us/op
DateFormatMicroBenchmark.measureThreadLocalJdkFormat_4  avgt   25  1.987 ± 0.209  us/op
DateFormatMicroBenchmark.measureThreadLocalJdkFormat_8  avgt   25  4.573 ± 0.553  us/op
```

# Result

Joda Time is by far the fastest (0.437 us/op) compared to e.g. not thread-safe JDK SimpleDateFormat with 0.936 us/op with 8 threads. Even the FastDateFormat is not that fast (4.692 us/op) as indicated by the class name.
