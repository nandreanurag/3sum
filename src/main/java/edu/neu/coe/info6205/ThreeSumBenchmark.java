package edu.neu.coe.info6205;

import java.util.function.Consumer;
import java.util.function.Supplier;

import edu.neu.coe.info6205.util.TimeLogger;
import edu.neu.coe.info6205.util.Utilities;

public class ThreeSumBenchmark {
    public ThreeSumBenchmark(int runs, int n, int m) {
        this.runs = runs;
        this.supplier = new Source(n, m).intsSupplier(10);
        this.n = n;
    }

    public void runBenchmarks() {
        benchmarkThreeSum("ThreeSumQuadratic", (xs) -> new ThreeSumQuadratic(xs).getTriples(), n, timeLoggersQuadratic);
        benchmarkThreeSum("ThreeSumQuadrithmic", (xs) -> new ThreeSumQuadrithmic(xs).getTriples(), n, timeLoggersQuadrithmic);
        benchmarkThreeSum("ThreeSumCubic", (xs) -> new ThreeSumCubic(xs).getTriples(), n, timeLoggersCubic);
    }

    public static void main(String[] args) {
        new ThreeSumBenchmark(100, 250, 250).runBenchmarks();
        new ThreeSumBenchmark(50, 500, 500).runBenchmarks();
        new ThreeSumBenchmark(20, 1000, 1000).runBenchmarks();
        new ThreeSumBenchmark(10, 2000, 2000).runBenchmarks();
        new ThreeSumBenchmark(5, 4000, 4000).runBenchmarks();
        new ThreeSumBenchmark(3, 8000, 8000).runBenchmarks();
        new ThreeSumBenchmark(2, 16000, 16000).runBenchmarks();
    }

    private void benchmarkThreeSum(final String description, final Consumer<int[]> function, int n, final TimeLogger[] timeLoggers) {
        if (description.equals("ThreeSumCubic") && n <17000) {
        	long startTime=System.currentTimeMillis();
        	function.accept(supplier.get());
        	long endTime=System.currentTimeMillis();
        	System.out.println("Raw time per run (mSec): "+n+" is "+ (endTime-startTime));
        	System.out.println("Normalized time per run (n^3): "+n+" is "+ (endTime-startTime)*1.0/ n / n / n * 1e6);
        	
        }
        // FIXME
        else if(description.equals("ThreeSumQuadratic") && n < 17000) {
        	long startTime=System.currentTimeMillis();
        	function.accept(supplier.get());
        	long endTime=System.currentTimeMillis();
        	System.out.println("Raw time per run (mSec): "+n+" is "+ (endTime-startTime));
        	System.out.println("Normalized time per run (n^2): "+n+" is "+ (endTime-startTime)*1.0/ n / n * 1e6);
        	
        }
        else if(description.equals("ThreeSumQuadrithmic") && n < 17000) {
        	long startTime=System.currentTimeMillis();
        	function.accept(supplier.get());
        	long endTime=System.currentTimeMillis();
        	System.out.println("Raw time per run (mSec): "+n+" is "+ (endTime-startTime));
        	System.out.println("Normalized time per run (n^2 log n): "+n+" is "+ (endTime-startTime)*1.0/ n / n / Utilities.lg(n) * 1e6);
        }
        
        System.out.println("*********************************************************************************************");
        // END 
    }

    private final static TimeLogger[] timeLoggersCubic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^3): ", (time, n) -> time / n / n / n * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadrithmic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2 log n): ", (time, n) -> time / n / n / Utilities.lg(n) * 1e6)
    };
    private final static TimeLogger[] timeLoggersQuadratic = {
            new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
            new TimeLogger("Normalized time per run (n^2): ", (time, n) -> time / n / n * 1e6)
    };

    private final int runs;
    private final Supplier<int[]> supplier;
    private final int n;
}
