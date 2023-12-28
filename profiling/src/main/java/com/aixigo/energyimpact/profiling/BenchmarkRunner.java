package com.aixigo.energyimpact.profiling;

import org.openjdk.jmh.profile.LinuxPerfProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner
{
   public static void main( String[] args ) throws Exception
   {
      var opt = new OptionsBuilder().include( "com\\.aixigo\\.energyimpact\\.profiling\\..*" )
         // .addProfiler( LinuxPerfProfiler.class, "events=power/energy-cores/,power/energy-ram" )
         .build();

      new Runner( opt ).run();
   }
}
