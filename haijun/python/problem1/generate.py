#!/usr/bin/env python3
import string
import random
import re

class Generator:
    """
    Generate random words or digits
    """

    def __init__(self, filename, maxLen, total, dataType="all"):
        """
        Construct Generator.
        Params:
            filename: file name which is used to store result in to file.
            maxLen: words maxLen
            total: how many words will be in the result file
            dataType: How to generate data
                        - "all": includes string and number
                        - "digit": digit only
                        - "words": string only
                        - "pureDigit": generate digit without non-letter-non-digit characters
                        - "pureWords": generate words without non-letter-non-digit characters
                        - "pureMixed": generate dataset without non-letter-non-digit characters
        """
        self.filename = filename
        self.maxLen = maxLen
        self.total = total
        self.dataType = dataType

    def generate(self):
        """
        Generate random words or digits
        """
        numStr = "0123456789"
        strStr = re.sub('[^a-zA-Z]+', "", string.printable)
        badChars = re.sub('[a-zA-Z\\d\\s\-]', "", string.printable)
        with open(self.filename, 'w') as output:
            if self.dataType == "digit":
                strs = numStr + badChars
            elif self.dataType == "words":
                strs = strStr + badChars
            elif self.dataType == "pureDigit":
                strs = numStr
            elif self.dataType == "pureWords":
                strs = strStr
            elif self.dataType == "pureMixed":
                strs = "mixed" # change it during processing
            elif self.dataType == "all": # "all"
                strs = numStr + strStr + badChars
            else:
                print("Invalid input: %s" % (self.dataType))
                return
            for x in range(int(self.total)):
                len = random.randint(1, int(self.maxLen))
                pre = ""
                if self.dataType == "pureDigit" or self.dataType == "digit":
                    pre = "-" if (x % 3 == 0) else ""
                if self.dataType == "pureMixed":
                    strs = numStr if (random.randint(0, 2) == 0) else strStr
                output.write(pre + "".join(random.choice(strs) for _ in range(len)) + " ")
            output.write("\n")


if __name__=="__main__":
    #import sys

    dataTypes = ["digit", "words", "pureDigit", "pureWords", "pureMixed", "all"]
    for i, t in enumerate(dataTypes, start=1):
        for x in range(i * 10, (i + 1) * 10):
            ge = Generator("test/input%d.txt" % (x,), x, random.randint(10, x * 100), t)
            ge.generate()
        ge = Generator("test/input%d.txt" % (70 + i - 1,), 100, 100000, t)
        ge.generate()

    #if len(sys.argv) != 5:
    #    print("Usage: " + sys.argv[0] + " <filename> <max length of word> <total> <data type>"
    #        + "data type: all, word, digit, pure")
    #    sys.exit(2)
    #ge = Generator(sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4])
    #ge.generate()
