<h4>To run:</h4> 
Execute the command bellow:
```
mvn spring-boot:run
```
Open a web browser and go to url:
```
localhost:8080
```
Will open a Swagger UI. Put a filename into the Input Data and execute.
- The result will be show.

You either can use the file "implementation.jar" by command and use the web browser to test:
```
java -jar implementation.jar
```


<h4>Steps of solution</h4>
Given the package:
```
81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9)(6,46.34,€48)
```
Step 1:
- Separate the Package Weight from the Items and order the items by weight. Will be stay like bellow:
```
{81}
```
```
[{1,53.38,€45} {2,88.62,€98} {3,78.48,€3} {4,72.30,€76} {5,30.18,€9}{6,46.34,€48}] 
```
Step 2:
- Order the Items by weight:
```
[{5,30.18,€9} {6,46.34,€48} {1,53.38,€45} {4,72.30,€76} {3,78.48,€3} {2,88.62,€98}] 
```
Step 3:
- Filter the weight items lesser or equal than Weight Package and mount combinations of the items that:
>The sum of weight item must be lesser or equal of Weight Package.
```
1. [{5,30.18,€9}]
2. [{5,30.18,€9} {6,46.34,€48}]
3. [{6,46.34,€48}]
4. [{1,53.38,€45}]
5. [{4,72.30,€76}]
6. [{3,78.48,€3}] 
```
Step 4:
- The Max sum of the combination costs between the items must be the result.
```
1. [{5,30.18,€9}]                   cost = 9
2. [{5,30.18,€9} {6,46.34,€48}]     cost = 57
3. [{6,46.34,€48}]                  cost = 48
4. [{1,53.38,€45}]                  cost = 45
5. [{4,72.30,€76}]                  cost = 76
6. [{3,78.48,€3}]                   cost = 3
```
The Max cost is 76, so the answer is 4 (index of object 5).

This is repeated for each line of file.

<h4>Constraints</h4>

- If exists one invalid Input into file, the app will give error: Invalid Input of Data
- If file not found, the app will give error: (No such file or directory)
- If one of Packages exceed the weight limit, that line will be warning with the message: "Max weight that a package can take is ≤ 100"
 and the other lines will be processed normally.
- If there are in one of packages more than 15 items, that package will be warning with the message: "There might be up to 15 items you need to choose from"
and the other lines will be processed normally.
- If there is(are) item(s) with cost greater than 100, that item will be ignored.
The same is valid for weight item above of 100.
