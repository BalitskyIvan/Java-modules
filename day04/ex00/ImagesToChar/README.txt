In the directory ImagesToChar:

To run the program, follow these steps:
1. mkdir target &&  find -name "*.java" > source.txt

Compile project:
2. javac -d target @source.txt

Run the program:
3. java -cp target edu.school21.printer.app.Start  <charcter to white color> <character to black color> <path to the file in .bmp format>