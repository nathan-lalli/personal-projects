# import all of the network utilities that we will be using
import nmap
from pythonping import ping

# get the target or range to scan from the user
target = input('Target: ')

# check to see if the target is alive/pingable
if ping(target, count=1).packet_loss > 0.0:
    print("The target is not responding to pings")
    response = input("Continue? Y/N: ")
        if response == N:
            exit()

