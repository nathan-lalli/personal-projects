# ----------------------------------------------------------------------
# This program will get a ciphertext from the user and then decrypt it
# and return the plaintext to the user.
# This is all run in the console.
#
# To be able to use this file you will need to run the command
#
# pip install pyenchant
# pip install subbreaker
# ----------------------------------------------------------------------

import os
import enchant
import subbreaker


secret = True
dictionary = enchant.Dict("en")

message = input("Enter the cryptogram here: ")

with open(os.getcwd() + "/EN.json") as fh:
    solver = subbreaker.Breaker(fh)

while secret:
    solved = solver.break_cipher(message)
    list = solved.plaintext.split()

    for i in list:
        punc = '''!()-[]{};:'"\,<>./?@#$%^&*_~'''

        # Removing punctuations in string
        # Using loop + punctuation string
        for ele in i:
            if ele in punc:
                i = i.replace(ele, " ")
        if dictionary.check(i.strip()):
            secret = False
        else:
            secret = True
            break


print("Your message was: " + solved.plaintext)
