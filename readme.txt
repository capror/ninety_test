
CommonDataSource is the class that unites two data streams.

What should be done in a real world?

1. Data joining rules. 
What is the maximum distance between position and temperature measurements is to claim it the one measurement point. What if several measurements exist in maximum time range, etc.
I assumed that temperature point should be after the position, the earliest temprature point after position point in max time range is the match.

2. Multythreading.
If real asynchronous threads were used we must store some data frame to search data to match.
Callbacks will fit better to get the joined data.

3. Tests
Data consistency must be covered with the tests first of all:
 - matching two points if it's in range and failing if time distance is greater
 - all matching rules 
 
NB. I didn't write any because of time lack. Too many things to recall in a short time. As we discussed on F2F interview my technology stack is rather different than what we need here. 