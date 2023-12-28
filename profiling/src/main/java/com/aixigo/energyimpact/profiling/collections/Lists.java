package com.aixigo.energyimpact.profiling.collections;

import java.util.List;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class Lists
{
   @Fork( value = 1, warmups = 1 )
   @Benchmark
   public void traversal( Blackhole blackhole, ListState state )
   {
      for( Integer i : state.list ) {
         blackhole.consume( i );
      }
   }

   @Fork( value = 1, warmups = 1 )
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

      // TODO: Add more list implementations
      @Param( { "java.util.ArrayList" } )
      public String listClass;

      // TODO: What are the values CT+ profiler uses?
      @Param( { "100" } )
      public int capacity;

      // TODO: What are the values CT+ profiler uses?
      @Param( { "50" } )
      public int n;

      List<Integer> list;

      @Setup( Level.Invocation )
      public void setUp() throws Exception
      {
         @SuppressWarnings( "unchecked" )
         var newList = (List<Integer>) Class.forName( listClass )
            .getDeclaredConstructor( int.class )
            .newInstance( capacity );

         for( int i = 0; i < n; ++i ) {
            newList.add( i );
         }

         this.list = newList;
      }
   }
}
