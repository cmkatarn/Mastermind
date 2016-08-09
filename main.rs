extern crate rand;

use std::io;
use rand::Rng;

fn main() {
    let mut turns_taken = 1;
    let mut combination = generate_combination();
    let mut combination_not_yet_found = true;

    while combination_not_yet_found {
        println!("[Turn # {}] Please enter a guess: ", turns_taken);
        let mut guess = String::new();
        io::stdin().read_line(&mut guess).expect("Failed to read line!");

        guess = guess.to_uppercase();
        let mut matches = 0;
        for x in 0..4 {
            if combination.chars().nth(x).unwrap() == guess.chars().nth(x).unwrap() {
                matches +=1;
            }
        }
        if matches == 4 {
            combination_not_yet_found = false;
            println!("You found the correct combination on turn {}!", turns_taken);
        }else{
            println!("That guess had {} matches.", matches);
        }
        turns_taken+=1;
    }
}


fn generate_combination() -> String {
    let mut combination = String::new();
    for x in 0..4 {
        let num: i32 = rand::thread_rng().gen_range(0, 4);
        combination.push(number_to_letter(num));
    }
    return combination
}


fn number_to_letter(a: i32) -> char{
    match a {
        0 => return 'R',
        1 => return 'G',
        2 => return 'B',
        3 => return 'Y',
        _ => return 'R'
    }
}
