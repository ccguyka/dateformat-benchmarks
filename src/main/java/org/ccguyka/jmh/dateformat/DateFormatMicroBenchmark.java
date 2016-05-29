package org.ccguyka.jmh.dateformat;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

@Fork(5)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class DateFormatMicroBenchmark {

    private static final String DATE_FORMAT = "MMMM, yyyy";

    // We'll always use the same Date throughout the microbenchmark for formatting
    @State(Scope.Benchmark)
    public static class DateToFormat {
        final Date date = new Date();
    }

    @State(Scope.Benchmark)
    public static class DateTimeToFormat {
        final DateTime date = new DateTime(new Date());
    }

    // We need to bind SimpleDateFormat to a thread, otherwise it is broken
    @State(Scope.Thread)
    public static class JdkDateFormatHolder {
        final Format format = new SimpleDateFormat(DATE_FORMAT);

        public String format(Date date) {
            return format.format(date);
        }
    }

    @State(Scope.Benchmark)
    public static class SyncJdkDateFormatHolder {
        final Format format = new SimpleDateFormat(DATE_FORMAT);

        public synchronized String format(Date date) {
            return format.format(date);
        }
    }

    @State(Scope.Benchmark)
    public static class ThreadLocalJdkDateFormatHolder {
        private static ThreadLocal<SimpleDateFormat> inDateFormatHolder = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat(DATE_FORMAT);
            }
        };
        final Format format = inDateFormatHolder.get();

        public synchronized String format(Date date) {
            return format.format(date);
        }
    }

    // Already threadsafe
    @State(Scope.Benchmark)
    public static class CommonsDateFormatHolder {
        final Format format = FastDateFormat.getInstance(DATE_FORMAT);

        public String format(Date date) {
            return format.format(date);
        }
    }

    @State(Scope.Benchmark)
    public static class JodaTimeDateFormatHolder {
        DateTimeFormatter format = DateTimeFormat.forPattern(DATE_FORMAT);

        public String format(DateTime date) {
            return format.print(date);
        }
    }

    @Benchmark
    @Threads(1)
    public String measureJdkFormat_1(JdkDateFormatHolder formatter, DateToFormat date) {
        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(2)
    public String measureJdkFormat_2(JdkDateFormatHolder formatter, DateToFormat date) {
        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(4)
    public String measureJdkFormat_4(JdkDateFormatHolder formatter, DateToFormat date) {
        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(8)
    public String measureJdkFormat_8(JdkDateFormatHolder formatter, DateToFormat date) {
        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(1)
    public String measureSyncJdkFormat_1(SyncJdkDateFormatHolder formatter, DateToFormat date) {

        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(2)
    public String measureSyncJdkFormat_2(SyncJdkDateFormatHolder formatter, DateToFormat date) {

        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(4)
    public String measureSyncJdkFormat_4(SyncJdkDateFormatHolder formatter, DateToFormat date) {

        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(8)
    public String measureSyncJdkFormat_8(SyncJdkDateFormatHolder formatter, DateToFormat date) {
        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(1)
    public String measureThreadLocalJdkFormat_1(ThreadLocalJdkDateFormatHolder formatter, DateToFormat date) {

        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(2)
    public String measureThreadLocalJdkFormat_2(ThreadLocalJdkDateFormatHolder formatter, DateToFormat date) {

        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(4)
    public String measureThreadLocalJdkFormat_4(ThreadLocalJdkDateFormatHolder formatter, DateToFormat date) {

        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(8)
    public String measureThreadLocalJdkFormat_8(ThreadLocalJdkDateFormatHolder formatter, DateToFormat date) {
        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(1)
    public String measureCommonsFormat_1(CommonsDateFormatHolder formatter, DateToFormat date) {
        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(2)
    public String measureCommonsFormat_2(CommonsDateFormatHolder formatter, DateToFormat date) {
        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(4)
    public String measureCommonsFormat_4(CommonsDateFormatHolder formatter, DateToFormat date) {
        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(8)
    public String measureCommonsFormat_8(CommonsDateFormatHolder formatter, DateToFormat date) {
        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(1)
    public String measureJodaTimeFormat_1(JodaTimeDateFormatHolder formatter, DateTimeToFormat date) {
        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(2)
    public String measureJodaTimeFormat_2(JodaTimeDateFormatHolder formatter, DateTimeToFormat date) {
        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(4)
    public String measureJodaTimeFormat_4(JodaTimeDateFormatHolder formatter, DateTimeToFormat date) {
        return formatter.format(date.date);
    }

    @Benchmark
    @Threads(8)
    public String measureJodaTimeFormat_8(JodaTimeDateFormatHolder formatter, DateTimeToFormat date) {
        return formatter.format(date.date);
    }
}
