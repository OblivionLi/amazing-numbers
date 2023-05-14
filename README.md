# amazing-numbers
### A simple program that shows properties for a given number or until a number has a property N times
---
#### The user is prompted with a set of instructions:
---
Supported requests:
- enter a natural number to know its properties;
- enter two natural numbers to obtain the properties of the list:
  * the first parameter represents a starting number;
  * the second parameter shows how many consecutive numbers are to be printed;
- two natural numbers and properties to search for;
- a property preceded by minus must not be present in numbers;
- separate the parameters with one space;
- enter 0 to exit.
---
#### Then the program accepts an input of format (*starting number* *iterator* *...properties*) divided by whitespaces
> While you might notice that in all the examples listed below the iterator is always `5`; both starting number and iterator can be of long type; the number `5` is just for presentation purposes
---
###### for example: "1 10" => input values are divided by whitespaces and the first two values are numbers and then the input can have N properties
###### accepted properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]
---
* if the first 2 values aren't numbers the program will inform the user that they need to provide a natural number (>0) in order to continue and then the program asks them again for a new input
* also if the user adds a non-existing property as input the program will inform them that the property is wrong
* there are also checks in place where the user can't provide mutually exclusive properties like odd/even e.g. a number can't be both odd and even
---
###### this input will return a 10 element list of numbers starting from 1 with their respective properties
               1 is odd, palindromic, spy, square, jumping, happy
               2 is even, palindromic, spy, jumping, sad
               3 is odd, palindromic, spy, sunny, jumping, sad
               4 is even, palindromic, spy, square, jumping, sad
               5 is odd, palindromic, spy, jumping, sad
               6 is even, palindromic, spy, jumping, sad
               7 is odd, buzz, palindromic, spy, jumping, happy
               8 is even, palindromic, spy, sunny, jumping, sad
               9 is odd, palindromic, spy, square, jumping, sad
              10 is even, duck, jumping, happy
---
#### Another thing that the program allows is to negate properties
###### for example: "1 5 -even"
---
* this input will start from 1 and will return 5 numbers that are not even
---
               1 is odd, palindromic, spy, square, jumping, happy
               3 is odd, palindromic, spy, sunny, jumping, sad
               5 is odd, palindromic, spy, jumping, sad
               7 is odd, buzz, palindromic, spy, jumping, happy
               9 is odd, palindromic, spy, square, jumping, sad
---
#### Also the program allows a combination of properties (including negation properties)
###### for example: "1 5 even sunny happy -duck -gapful"
---
* this input will start from 1 and will return 5 numbers that are even, sunny, happy but not duck and gapful
---
           3,968 is even, sunny, happy
          34,224 is even, sunny, happy
          75,624 is even, sunny, happy
         134,688 is even, sunny, happy
         178,928 is even, sunny, happy
---
#### A few more examples:
---
* input 1 5 -sunny -square (return first 5 numbers starting from 1 that are not sunny and square)
---
               2 is even, palindromic, spy, jumping, sad
               5 is odd, palindromic, spy, jumping, sad
               6 is even, palindromic, spy, jumping, sad
               7 is odd, buzz, palindromic, spy, jumping, happy
              10 is even, duck, jumping, happy
---
* input 2000 5 happy (return first 5 numbers starting from 2000 that are happy)
---
           2,003 is odd, duck, happy
           2,008 is even, duck, happy
           2,019 is odd, duck, happy
           2,026 is even, duck, happy
           2,030 is even, buzz, duck, happy
