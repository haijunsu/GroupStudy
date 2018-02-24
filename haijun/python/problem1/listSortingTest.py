#!/usr/bin/env python3
import unittest
import filecmp
import logging
import os

from listSorting import ContentProcessor

class TestContentProcessor(unittest.TestCase):

    def setUp(self):
        """ init file list """
        # test random generated files
        self.inputs = ["test/input" + str(i) + ".txt" for i in range(10, 76)]
        self.outputs = ["test/output" + str(i) + ".txt" for i in range(10, 76)]
        # mannual files:
        self.inputs2 = ["test/in" + str(i) + ".txt" for i in range(1, 10)]
        self.outputs2 = ["test/out" + str(i) + ".txt" for i in range(1, 10)]
        self.ans2 = ["test/ans" + str(i) + ".txt" for i in range(1, 10)]

    def test_cleaning_up(self):
        badChars = "!\"#$%&'()*+,.\/:;<=>?@[\\]^_`{|}~";
        self.inputs.extend(self.inputs2)
        for filename in self.inputs:
            # process by replace function
            with open(filename, 'r') as input:
                line = input.readline().strip()
                for badchar in badChars:
                    line = line.replace(badchar, "")
                cpr = ContentProcessor(filename, None) # we don't care out put file here
                self.assertEqual(line.strip(), cpr.cleaning_up())

    def test_sorting_and_save(self):
        filenames = zip(self.inputs, self.outputs)
        for infile, outfile in filenames:
            cpr = ContentProcessor(infile, outfile)
            content = cpr.cleaning_up()
            cpr.sorting_and_save(content)
            with open(outfile, 'r') as resultFile:
                line = resultFile.readline()[:-1]
                self.assertNotEqual(" ", line[0])
                self.assertNotEqual(" ", line[-1])
                # check type by position
                origArr = content.split();
                resuArr = line.split();
                for item in resuArr:
                    test = ""
                    try:
                        val = int(item)
                        test ="int"
                    except ValueError:
                        test = "str"
                    val = origArr.pop(0)
                    if test == "int":
                        try:
                            val1 = int(val)
                        except ValueError:
                            self.assertTrue(False)
                    else:
                        try:
                            val1 = int(val)
                            self.assertTrue(False)
                        except ValueError:
                            self.assertTrue(True)
                # check sorted function
                digits = []
                words = []
                for item in resuArr:
                    try:
                        val = int(item)
                        digits.append(val)
                    except ValueError:
                        words.append(item)
                self.assertEqual(words, sorted(words, key=str.lower))
                self.assertEqual(digits, sorted(digits))
            os.unlink(outfile)
        filenames2 = zip(self.inputs2, self.outputs2, self.ans2)
        for infile, outfile, ansfile in filenames2:
            cpr = ContentProcessor(infile, outfile)
            cpr.sorting_and_save(cpr.cleaning_up())
            self.assertTrue(filecmp.cmp(outfile, ansfile, shallow=True))
            os.unlink(outfile)

    def tearDown(self):
        pass

if __name__ == '__main__':
    unittest.main()
