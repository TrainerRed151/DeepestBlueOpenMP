//Brian Pomerantz

import java.util.*;

public class AI {

	private boolean side;
	private int depth;
	
	public AI(boolean side, int depth) {
		this.side = side;
		this.depth = depth;
	}
	
	//Calculates the best move using a MinMax algorithm with ALphaBeta Pruning
	//Input: ArrayList of legal moves
	//Output: The particular legal move which maximizes (or minimizes) the engine's score
	public String move(Board bd) {
		ArrayList moves = bd.legalMoves(side);
		
		//Stalemate
		if (moves.size() == 0) {
			return '\u00ab' + "-" + '\u00ab';
		}

		byte alpha = Byte.MIN_VALUE, beta = Byte.MAX_VALUE;
		
		byte best = -1, val, bestVal;
		if (side) {bestVal = Byte.MIN_VALUE;}
		else {bestVal = Byte.MAX_VALUE;}

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.best = best;
  __omp_Object0.bestVal = bestVal;
  __omp_Object0.alpha = alpha;
  __omp_Object0.beta = beta;
  __omp_Object0.moves = moves;
  __omp_Object0.bd = bd;
  __omp_Object0.depth = depth;
  __omp_Object0.side = side;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object0);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  best = __omp_Object0.best;
  val = __omp_Object0.val;
  bestVal = __omp_Object0.bestVal;
  alpha = __omp_Object0.alpha;
  beta = __omp_Object0.beta;
  moves = __omp_Object0.moves;
  bd = __omp_Object0.bd;
  depth = __omp_Object0.depth;
  side = __omp_Object0.side;
}
// OMP PARALLEL BLOCK ENDS

		
		//Resign if opponent has forced mate within depth
		//Alternatively, the engine could chose a random move
		if (best == -1) {
			if (side){return "0-1";}
			else{return "1-0";}
		}
		
		return (String) moves.get(best);
	}
		
	//AlphaBeta function
	//Recursively determines best possible move assuming perfect play within given depth
	//Discontinues branch of search tree if necessarily worse than previously calculated branch
	//More efficient than MinMax
	private byte alphabeta(Board bd, int dep, byte alpha, byte beta, boolean maxPlayer) {
		if (dep == 0) {
        	return bd.score();
		}
		
    if (maxPlayer) {
    	ArrayList list = bd.legalMoves(true);
    	
    	//Checkmate	
    	if (bd.checkmate(false)) {
    		return Byte.MIN_VALUE;
    	}
    	
    	//Stalemate
    	if (list.size() == 0) {
    		return 0;
    	}
    	
   		for (int s = 0; s < list.size(); s++) {
   			Board nBD = new Board(bd.getBoard());
   			
				byte tempVal = alphabeta(nBD.move((String) list.get(s), true), dep-1, alpha, beta, false);
				
				if (tempVal > alpha) {
						alpha = tempVal;
				}

    		if (beta <= alpha) {
   				break;
   			}
   		}		
	
			return alpha;
		}
    	
  	else {
  		ArrayList list = bd.legalMoves(false);
  		
  		//Checkmate
  		if (bd.checkmate(true)) {
  			return Byte.MAX_VALUE;
  		}
  		
  		//Stalemate
  		if (list.size() == 0) {
  			return 0;
  		}
  		
			for (int s = 0; s < list.size(); s++) {
				Board nBD = new Board(bd.getBoard());
  				
				byte tempVal = alphabeta(nBD.move((String) list.get(s), false), dep-1, alpha, beta, true);
				
				if (tempVal < beta) {
						beta = tempVal;
				}

  			if (beta <= alpha) {
  				break;
  			}
  		}
								
			return beta;
		}
	}

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private class __omp_Class0 extends jomp.runtime.BusyTask {
  // shared variables
  byte best;
  byte val;
  byte bestVal;
  byte alpha;
  byte beta;
  ArrayList moves;
  Board bd;
  int depth;
  boolean side;
  // firstprivate variables
  // variables to hold results of reduction

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  // reduction variables, init to default
    // OMP USER CODE BEGINS
 {
                         { // OMP FOR BLOCK BEGINS
                         // copy of firstprivate variables, initialized
                         // copy of lastprivate variables
                         // variables to hold result of reduction
                         boolean amLast=false;
                         {
                           // firstprivate variables + init
                           // [last]private variables
                           // reduction variables + init to default
                           // -------------------------------------
                           jomp.runtime.LoopData __omp_WholeData2 = new jomp.runtime.LoopData();
                           jomp.runtime.LoopData __omp_ChunkData1 = new jomp.runtime.LoopData();
                           __omp_WholeData2.start = (long)( 0);
                           __omp_WholeData2.stop = (long)( moves.size());
                           __omp_WholeData2.step = (long)(1);
                           jomp.runtime.OMP.setChunkStatic(__omp_WholeData2);
                           while(!__omp_ChunkData1.isLast && jomp.runtime.OMP.getLoopStatic(__omp_me, __omp_WholeData2, __omp_ChunkData1)) {
                           for(;;) {
                             if(__omp_WholeData2.step > 0) {
                                if(__omp_ChunkData1.stop > __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                                if(__omp_ChunkData1.start >= __omp_WholeData2.stop) break;
                             } else {
                                if(__omp_ChunkData1.stop < __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                                if(__omp_ChunkData1.start > __omp_WholeData2.stop) break;
                             }
                             for(byte i = (byte)__omp_ChunkData1.start; i < __omp_ChunkData1.stop; i += __omp_ChunkData1.step) {
                               // OMP USER CODE BEGINS
 {
				Board nBD = new Board(bd.getBoard());
				val = alphabeta(nBD.move((String) moves.get(i), side), depth, alpha, beta, !side);
				//System.out.println(moves.get(i) + ": " + val);
				
				if (side && val > bestVal) {
                                         // OMP CRITICAL BLOCK BEGINS
                                         synchronized (jomp.runtime.OMP.getLockByName("")) {
                                         // OMP USER CODE BEGINS
 {
						bestVal = val;
						best = i;
					}
                                         // OMP USER CODE ENDS
                                         }
                                         // OMP CRITICAL BLOCK ENDS

					
					if (val > alpha) {
                                                 // OMP CRITICAL BLOCK BEGINS
                                                 synchronized (jomp.runtime.OMP.getLockByName("")) {
                                                 // OMP USER CODE BEGINS
 {
							alpha = val;
						}
                                                 // OMP USER CODE ENDS
                                                 }
                                                 // OMP CRITICAL BLOCK ENDS

					}	
				}
				
				if (!side && val < bestVal) {
                                         // OMP CRITICAL BLOCK BEGINS
                                         synchronized (jomp.runtime.OMP.getLockByName("")) {
                                         // OMP USER CODE BEGINS
 {
						bestVal = val;
						best = i;
					}
                                         // OMP USER CODE ENDS
                                         }
                                         // OMP CRITICAL BLOCK ENDS

					
					if (val < beta) {
                                                 // OMP CRITICAL BLOCK BEGINS
                                                 synchronized (jomp.runtime.OMP.getLockByName("")) {
                                                 // OMP USER CODE BEGINS
 {
							beta = val;
						}
                                                 // OMP USER CODE ENDS
                                                 }
                                                 // OMP CRITICAL BLOCK ENDS

					}
				}
				
				if (beta <= alpha) {					
					return;
	    	}
			}
                               // OMP USER CODE ENDS
                               if (i == (__omp_WholeData2.stop-1)) amLast = true;
                             } // of for 
                             if(__omp_ChunkData1.startStep == 0)
                               break;
                             __omp_ChunkData1.start += __omp_ChunkData1.startStep;
                             __omp_ChunkData1.stop += __omp_ChunkData1.startStep;
                           } // of for(;;)
                           } // of while
                           // call reducer
                           jomp.runtime.OMP.doBarrier(__omp_me);
                           // copy lastprivate variables out
                           if (amLast) {
                           }
                         }
                         // set global from lastprivate variables
                         if (amLast) {
                         }
                         // set global from reduction variables
                         if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                         }
                         } // OMP FOR BLOCK ENDS

		}
    // OMP USER CODE ENDS
  // call reducer
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS

}

