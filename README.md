## Overview
- Objective: implementation of a modular utility for network traffic analysis
- Language: Java - JDK 17 (core); Python - 3.6 (data representation)
- Updated at 8 Dec 2022

## Summary
This is a network traffic analysis tool divided by several and extendable free and open source for modules for traffic-data handling and processing. This tool reads and processes CSV files that have the following content:
|    Number     |    Time (s)   |    SourceIp   | DestinationIp |   SoucePort   |DestinationPort|    Protocol   |   ICMP type   | Length (bytes)|  Flags (hex)  |
| ------------- |---------------|---------------|---------------|---------------|---------------|---------------|---------------|---------------|---------------|
|       1       |   0.0000000   | 192.168.1.202 | 172.224.42.53 |     42992     |     60002     |      UDP      |               |     1314      |     0x010     |
|       2       |   0.1111111   | 172.224.42.53 | 192.168.1.202 |     3478      |     46692     |      UDP      |               |     1214      |     0x011     |
|       3       |   0.2222222   | 192.168.1.111 | 192.168.1.108 |     8080      |     42997     |      TCP      |               |     330       |     0x012     |
|       4       |   0.3333333   | 192.168.1.108 | 192.168.1.111 |     8484      |     443       |      TLSv1    |               |     450       |     0x002     |
|       5       |   0.4444444   | 192.168.1.108 | 172.224.42.53 |     443       |     42912     |      TCP      |               |     40        |     0x002     |

## Authors
- Guilherme Marcelo - [@guilherme-marcello](https://github.com/guilherme-marcello)
- Rafael Correia - [@Rafacorreia0611](https://github.com/Rafacorreia0611)
- João Ferreira - [@JFerreira03](https://github.com/JFerreira03)

## Project structure (Java Packages and plugins)
* **[Java package]** util - Contains classes and funtions of general utility used in multiple places throughout network analysis, e.g. MathUtils, Models, ClusteringUtils, et cetera.
* **[Java package]** trace - Contains classes that represent objects that exist in a trace, e.g. Capture, Packet, TCPFlag, TCPService.
* **[Java package]** processing - Contains classes and functions that allow you to process instances of Capture for a specific end, e.g. TrafficPerIP, PacketSize, TCPPorts, et cetera. 
* **[Python plugin]** plugin - Contains plugins written in python, which allow the exported data to be visualised, e.g. Barplots.

## Installation setup (compile and install dependencies)
To setup the project, you must have a clone of the project and, being in the same working directory as the project, run the command to compile the java project:
```bash
$ javac -d . TrafficAnalysis.java
```

After this, its required to install plugins' dependencies:
```bash
$ pip install -r ./plugin/requirements.txt
```

## Tool execution (run)
Once the project setup is complete (see previous section), we can run the tool, being in the same working directory as the project:
```bash
$ java TrafficAnalysis
```

By default, when executing the tool in this way, a sample trace will be read and loaded, present in "samples/traceA.csv". In order to load your own trace, "MyTrace.csv" for example, you must indicate the path to this file as the first parameter:
```bash
$ java TrafficAnalysis path/to/my/data/MyTrace.csv
```

From that moment, an interactive menu will appear, from which you can decide which processes you want to carry out on your data, in order to extract relevant information from it.

## Plugin usage (barplot generation)
Module 5 exports data that needs to be post processed for plot generation. When module 5 is executed, as indicated by the tool logs, a corresponding csv file will be exported under "samples". This exported csv is the file to be used as input to the python plugin. To get more details on how to run the plugin, you can call it with the "-h" flag, for help:
```bash
$ python3 plugin/traffic-analyser-python-plugin.py -h
usage: traffic-analyser-python-plugin.py [-h] -i INPUT -o OUTPUT [-n NAME]

Tool to read a packets size report and generate a barplot

optional arguments:
  -h, --help            show this help message and exit
  -i INPUT, --input INPUT
                        Input file with Packets size report
  -o OUTPUT, --output OUTPUT
                        Output file (png or pdf extension)
  -n NAME, --name NAME  Report name (id of trace, for example)

Example of usage: python3 traffic-analyser-python-plugin.py -i data.csv -o out.pdf -n 'Trace X'
```
As documented by the plugin's help output, the execution, from the project root, takes the form:
```bash
$ python3 plugin/traffic-analyser-python-plugin.py -i samples/myExportedData.csv -o out.pdf -n 'Trace number 20221208'
```
After running this command, you should be able to find the file "out.pdf" with the generated chart.

## License
MIT