DeepestBlueOpenMP
==============

Chess Engine (parallelized with OpenMP)

To run this version of Deepest Blue, exacute the command:

java -Djomp.threads=n runChess

where n is the number of threads to be used.

Here the AlphaBeta algorithm has been parallelized with OpenMP.  In order to avoid the race condition whereby each processor may attempt to access and/or update the values of alpha and/or beta concurrently, a critical block has been implemented.
