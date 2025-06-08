# Grade Management System 

#### By: Stan Bychkar, Sohail Chutani, Ilham Mohamed, Justin Rabung, and Alan Vu

## Brief Description 
This is our final project for CSS 360 - Software Engineering. This project involved developing a program from start to finish following the software development life cycle.  

## Instructions for running the program

#### Prerequisites 
Java JDK 11 or higher is installed
*Mac version:* MacOS 14.7.5+ The program should run on older versions of macOS given that the correct Java version is installed however the Generate Charts function may not work
#### Download: 
From the main [repository page](https://github.com/stasbychkar/GradeManagementSystem360) click on the ```Releases``` text (right side of the page) and navigate to Release ```v1.0.0```

*MacOS:* Download the zip ``` project3_v1.0.0-macOS ```

*Windows:* Download the zip ``` project3_v1.0.0-windows ```

Once downloaded, unzip the file and double click the .jar file to run the program. This will open up the main GUI that you can use to interact with the program. 

#### Troubleshooting:

**Nothing happening when double-clicking?** Try navigating to the folder where the files are stored in the terminal and running:
``` java -jar Project3_Release-1.0-SNAPSHOT-jar-with-dependencies.jar ```
If that still doesn't work and you get an error message please share the the error message output with us.

**Apple not allowing the program to run? / Program not opening on MacOS?** Go to Settings > Privacy & Security, scroll down and click "Open Anyways" You will have to do this again when clicking ```Generate Chart``` Here is a video showing the process: https://www.youtube.com/watch?v=8ue6KgoBXaI 

**Error: LinkageError occurred while loading main class program.MainFrame:** This may be because you are running an older version of Java. This program requires having Java 11 or higher installed

**Nothing happening when clicking Generate Chart?** Try running the python program separately. Go to where the files are stored on your computer with the terminal and enter the following (System-dependent):
MacOS: ```./charts GenerateHist GPA ```
Windows: ``` charts.exe GenerateHist GPA ```
If that still doesn't work and you get an error message please share the the error message output with us.

#### Known Issues:

* Generate Chart takes a long time to run (10-20 seconds)
* Generate Chart does not work on MacOS 14.6.1 or older
* The program does not run on Intel Macs

