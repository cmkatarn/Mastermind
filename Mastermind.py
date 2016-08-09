from array import array
import random

sequence = array('c', [' ', ' ', ' ', ' '])
turnsTaken = 1
combinationNotYetFound = True


def generate_random_number():
    return random.randint(0, 4)


def get_letter_from_number(number):
    switcher = {
        0: 'R',
        1: 'G',
        2: 'B',
        3: 'Y'
    }
    return switcher.get(number, 'R')


def generate_combination():
    global sequence
    for x in range(0, 4):
        sequence[x]= get_letter_from_number(generate_random_number())


def check_against_solution(guess):
    matches = 0
    fixed = guess.upper()
    for x in range(0,4):
        if fixed[x] == sequence[x]:
            matches += 1
    print("There were %d matches." % matches)
    return matches


def request_guess():
    global turnsTaken
    guess = ""
    while len(guess) != 4:
        guess = raw_input("[Turn # %d]\tPlease enter a guess (XXXX from RGBY): " % turnsTaken)
        if len(guess) != 4:
            print("ERROR: Supplied guess is in incorrect format!")
        else:
            turnsTaken += 1
            progress = check_against_solution(guess)
            if progress == 4:
                print("You found the correct combination!")
                global combinationNotYetFound
                combinationNotYetFound = False


def game():
    generate_combination()
    while combinationNotYetFound:
        request_guess()


def main():
    game()


if __name__== "__main__":
    main()