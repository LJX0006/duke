@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -d ..\bin C:\Users\ljx\Desktop\Sem3\CS2113\Project\duke\src\main\java\Deadline.java C:\Users\ljx\Desktop\Sem3\CS2113\Project\duke\src\main\java\Duke.java C:\Users\ljx\Desktop\Sem3\CS2113\Project\duke\src\main\java\DukeException.java C:\Users\ljx\Desktop\Sem3\CS2113\Project\duke\src\main\java\Event.java C:\Users\ljx\Desktop\Sem3\CS2113\Project\duke\src\main\java\Save.java C:\Users\ljx\Desktop\Sem3\CS2113\Project\duke\src\main\java\Task.java C:\Users\ljx\Desktop\Sem3\CS2113\Project\duke\src\main\java\Todo.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input.txt > ACTUAL.txt

REM compare the output to the expected output
FC ACTUAL.txt EXPECTED.txt