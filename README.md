
# Instructions to run the project
Make sure you have installed Java11

Run following commands to execute the main java class 
or else you can run the "GameMain.java" through your IDE
```
cd src
javac com/chargepoint/game/GameMain.java
java com.chargepoint.game.GameMain
```

# Assumptions Made
1) The dead cells in grid is represented by digit '0'
2) The alive cells in grid is represented by digit '1'
3) Modify the following variable in the java class to change the number of ticks (have not made it interactive by accepting an integer from user)
```java
int numberOfTicks = 3;

```
4) Every cell is grid is filled with either '0' or '1' and no other value
5) Not adding any unit testing frameworks (like Junit) for testing the ConwayGameOfLife class for time being 