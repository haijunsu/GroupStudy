#!/usr/bin/env python3
import re
import string
import logging

logging.basicConfig(level=logging.ERROR, format="%(asctime)s\t%(levelname)s\t%(message)s")

class ContentProcessor:
    """
    This class is used to clean up text and sort contents from input file and
    write result to output file. The result of contents only have words and
    numbers. There is no non-letter-non-digit in the result.
    """

    def __init__(self, input, output):
        """
        Initial class with input file name and output file name.

        Params:
            input - the input file name. The contents of file may include
                    non-letter-non-digit characters.
            output - the output file name. Result will be store in this file.
        """
        # init classes
        self.input = input
        self.output = output

    def cleaning_up(self):
        """
        Read content from input file and cleaning up non-letter and non-digit

        Return: content of input file without non-letter and non-digit
        """
        # find all non-letter-no-digit except whitespace and "-"
        try:
            pattern = re.compile("[a-zA-Z0-9\\s\\-]")
            badChars = re.sub(pattern, '', string.printable)
            logging.debug("Bad chars: {}".format(badChars))
            # define translate table
            remap = dict.fromkeys(badChars)
            logging.debug(remap)
            table = str.maketrans(remap)
            result = ""
            with open(self.input) as infile:
                lines = (line.strip() for line in infile)
                for line in lines:
                    if len(line) == 0:
                        continue
                    else:
                        logging.debug(line)
                        result = result + " " + line.translate(table)
                        # Since the input file only has one line, we can use the following
                        # code. For general use, I kept above code.
                        #   result = line.translate(remap)
                        #   break;
        except LookupError as e:
            logging.exception("Lookup Error: {}".format(e.strerror))
        except IOError as e:
            logging.exception("IO Error: {}".format(e.strerror))
        except:
            logging.exception("Unknown Error")
        return result.strip()

    def sorting_and_save(self, content):
        """
        Sorting list and combine words and numbers and save result to output file

        Params:
            content: content which needs be sorted by words and digits.
        """
        if content is None:
            logging.warn("Content is null")
            return
        try:
            arr = content.split()
            with open(self.output, 'w') as outfile:
                if len(arr) > 0:
                    words = []
                    integers = []
                    posTypes = [] # Position types: True: integer, False: word
                    for item in arr:
                        try:
                            val = int(item)
                            integers.append(val)
                            posTypes.append(True)
                        except ValueError:
                            words.append(item)
                            posTypes.append(False)
                    words.sort(key=str.lower)
                    logging.debug(words)
                    integers.sort()
                    logging.debug(integers)
                    outfile.write(str(integers.pop(0)) if posTypes[0] else words.pop(0))
                    if len(posTypes) > 1:
                        for pos in posTypes[1:]:
                            outfile.write(" " + str(integers.pop(0)) if pos else " " + words.pop(0))
                outfile.write("\n") # end the line
        except IOError as e:
            logging.exception("IO Error: {}".format(e.strerror))
        except:
            logging.exception("Unknown Error")

if __name__=='__main__':
    import sys

    if len(sys.argv) != 3:
        print("Usage: %s <input file name> <output file name>" % (sys.argv[0]))
        sys.exit(2)
    inFileName, outFileName = sys.argv[1], sys.argv[2]
    logging.debug("input = %s, output = %s" % (inFileName, outFileName))
    processor = ContentProcessor(inFileName, outFileName)
    processor.sorting_and_save(processor.cleaning_up());
    logging.info("Done")
