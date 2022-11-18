import os
import enchant
import subbreaker

# Get input from the user
# This should be the encrypted message


secret = True
dictionary = enchant.Dict("en")

# message = input("Enter the cryptogram here: ")
message = "ILDS KXJ SDSJCK UJ VLJ ZDLC, KXJ UDLQR KJCRW KD XVTJ V ZDE VQLJVRM ZGHQK IDL GW KD IHK HCWHRJ. DGL GSZHQHNVQ NDLR CJTJL WJJSW KD ZJ WJTJLJR; UJ DCQM IHCR CJU CJJRW KD IHQQ. HI UJ RHWNDCCJNKJR VCR WJTJLJR DGL VKKVNXSJCKW, UDGQR UJ WXVKKJL DGL NDCIHCJSJCKW VCR JEAVCR ZJMDCR DGL WXJQQ? UDGQR KXJ UDLQR QDDY RHIIJLJCK? UDGQR UJ LJNDPCHBJ DGLWJQTJW? VLJ"

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
