# import all of the network utilities that we will be using
import nmap
from pythonping import ping

# create the port scanner
scanner = nmap.PortScanner()

# get the target or range to scan from the user
target = input('Target: ')

# check to see if the target is alive/pingable
if ping(target, count=1).packet_loss > 0.0:
    print("The target is not responding to pings \n")
    response = input("Continue? Y/N: ")
    while response.upper() != "Y" and response.upper() != "N":
        response = input("\n Please enter either Y or N: ")
    if response.upper() == "N":
        exit()

# if here, the target is alive or user said to continue anyway
# ask the user if the deafult settings are okay
print("The default scan is an nmap scan, T4, Query OS, get versioning, run safe scrits, and output to a normal file \n")
response = input("Would you like to continue with the scan? Y/N: ")
while response.upper() != "Y" and response.upper() != "N":
    response = input("\n Please enter either Y or N: ")
if response.upper() == "N":
    exit()

# start the scan
print("Starting the scan, please be patient as it can take a while depending on the target range")
scanner.scan(hosts=target,arguments="-p- -T4 -O -sV -sC -oN defaultScan",sudo="true")

# output the scan results
print("Your scan results are in a file called defaultScan in the directory that you ran this from as well as printed on screen")

for host in scanner.all_hosts():
    print("----------------------------------------------------")
    print("Host: %s (%s)" % (host, scanner[host].hostname()))
    
    for proto in scanner[host].all_protocols():
        print("------------------------------")
        print("Protocol: %s \n" % proto)

        lport = scanner[host][proto].keys()
        # lport.sort()

        for port in lport:
            print("Port: %s \t State: %s" % (port, scanner[host][proto][port]["state"]))

print("\nScan has finished and output should be on screen and in your current directory")
print("Exitting... ")
