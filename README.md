MarkMillerKeene
===============
SocialPacketeer IP Matcher:

For this software you will need:
  jNETpcap install on your computer. Download link: http://jnetpcap.com/download , this also required the installation of 
winPCAP on windows, and libcap on Linux. 
  Java/MySQL connector : download link found here: https://dev.mysql.com/downloads/connector/j/
  MySQL database called "packet" with the "known" table (available in this repo) imported into it.

Installation & use:

To use this software, load "known" table into your newly created MySQL database called "packet". Next, load the all three classes into your favorite java IDE. If the mySQL connector /j and jnetpcap are installed properly, you should be able to run the program with no errors. Please a pcap file, in the modified tcdump format. Change the name of the pcap file on line 88 of ipMatcher.java When the program is run, you will be prompted to input a name. This name will correspond to a table that will be created in your "packet" database. This will contain all of the output from the program. 
  
