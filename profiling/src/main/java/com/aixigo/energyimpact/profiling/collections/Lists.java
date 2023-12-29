package com.aixigo.energyimpact.profiling.collections;

import java.util.List;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode( Mode.SingleShotTime )
public class Lists
{
   @Benchmark
   public void traversal( Blackhole blackhole, ListState state )
   {
      for( Integer i : state.list ) {
         blackhole.consume( i );
      }
   }

   @Benchmark
   public void sequentialGet( Blackhole blackhole, ListState state )
   {
      for( int i = 0; i < state.n; ++i ) {
         blackhole.consume( state.list.get( i ) );
      }
   }

   @State( Scope.Benchmark )
   public static class ListState
   {

      /**
       * see: https://github.com/ros3cin/CTplus-profiler/blob/032542d6efdd9d00c0b999fc31ba6688456daa5a/src/main/java/ListTest.java#L105-L111
       */
      @Param( {
         "java.util.ArrayList",
         "java.util.LinkedList",
         "org.eclipse.collections.impl.list.mutable.FastList",
         "org.apache.commons.collections4.list.NodeCachingLinkedList",
         "org.apache.commons.collections4.list.CursorableLinkedList",
         "org.apache.commons.collections4.list.TreeList"
      } )
      public String listClass;

      // TODO: What are the values CT+ profiler uses?
      @Param( { "100" } )
      public int capacity;

      /**
       * From the paper (<a href="https://doi.org/10.1007/s10664-021-09950-y">10.1007/s10664-021-09950-y</a>).
       * <quote>
       * To explore the impact of different energy profiles on the energy-efficiency
       * of Java collection implementations, six energy profiles were created for asus,
       * namely N1, N2, N4, N8, N16, and N32. Starting with N1 (the profile used on
       * Section 6.1), created using a specific load size for each API i.e., 15,000 for
       * Lists, 18,750 for Sets, and 50,000 for Maps. We then proceeded to multiplying960
       * this load size by a factor of two and then used it to create a new profile,
       * with N2 having two times the load size of N1, N4 having four times, until the
       * maximum of 32 times the load size of N1 with N32. These profiles were created
       * to simulate different workload sizes a collection may face, with the smaller
       * ones representing lightweight applications that do not depend too much on965
       * collections and the bigger ones representing data-structure heavy applications
       * that make more intensive usage of collections.
       * </quote>
       */
      @Param( {
         // N1
         "15000",
         // N2
         "30000",
         // N4
         "60000",
         // // N8
         // "120000",
         // // N16
         // "240000",
         // // N32
         // "512000"
      } )
      public int n;

      List<Integer> list;

      @Setup( Level.Invocation )
      @SuppressWarnings( "unchecked" )
      public void setUp() throws Exception
      {
         var clazz = Class.forName( listClass );
         List<Integer> newList;
         try {
            newList = (List<Integer>) clazz.getDeclaredConstructor( int.class ).newInstance( capacity );
         } catch( NoSuchMethodException e ) {
            newList = (List<Integer>) clazz.getDeclaredConstructor().newInstance();
         }

         for( int i = 0; i < n; ++i ) {
            newList.add( i );
         }

         this.list = newList;
      }
   }
}
