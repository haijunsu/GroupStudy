#!/usr/bin/env python3
import itertools


def mydiff():
    """
    Show different between isdigit, isnumeric, isdecimal
    Source: https://stackoverflow.com/questions/22789392/str-isdecimal-and-str-isdigit-difference-example
    """
    line = "-" * 50
    print(line)
    print("|   No.  | isdigit | isdecimal | isnumeric | char")
    print(line)
    for number in itertools.chain(range(1000), range(4969, 4978), range(8304, 11000)):
        char = chr(number)
        if (char.isdigit() or char.isdecimal() or char.isnumeric()):
            print('| {0:>6} | {1:^7} | {2:^9} | {3:^9} | {4:^3}'.format(
                    number,
                    "+" if char.isdigit() else "-",
                    "+" if char.isdecimal() else "-",
                    "+" if char.isnumeric() else "-",
                    char
                )
            )


if __name__=="__main__":
    mydiff()
