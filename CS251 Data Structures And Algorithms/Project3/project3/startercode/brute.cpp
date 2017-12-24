#include <cstdlib>
#include <fstream>
#include <iostream>
#include <string>

#include "key.hpp"
#include "brute.hpp"
#include "timer.hpp"

std::string me;
std::string encrypted;
std::string table_filename;
bool verbose = false;

Brute::Brute(const std::string& filename) {
	T.resize(N);
	std::string buffer;
    std::fstream input(filename.c_str(), std::ios::in);
    for (int i = 0; i < N; i++) {
        std::getline(input, buffer);
        T[i].set_string(buffer);
    }
    input.close();
}

void Brute::decrypt(const std::string& encrypted){
	std::string tmp = "";

	if (tmp.empty()) {
		tmp = "a";
		int length = C - 1;
		for (int i = 0; i < length; i++) {
			tmp = tmp + "a";
		}
	}
	
	Key encryptedKey(encrypted);
	createPassword(tmp, 0, encryptedKey);
}

void Brute::checkPassword(std::string& pw, Key& encryptedKey) {
	Key passwordKey(pw);
	Key ssum = passwordKey.subset_sum(T, verbose);

	if (ssum == encryptedKey) {
		std::cout << pw << "\n";
	}
}

void Brute::createPassword(std::string& pw, int index, Key& encryptedKey) {
	
	for (int i = 0; i < R; i++) {
		pw[index] = ALPHABET[i];
		int length = C - 1;
		if (index == length) {
			checkPassword(pw, encryptedKey);
		}
		else {
			createPassword(pw, index + 1, encryptedKey);
		}
	}
}

void usage(const std::string& error_msg="") {
	if (!error_msg.empty()) std::cout << "ERROR: " << error_msg << '\n';
	std::cout << me << ": Brute force cracking of Subset-sum password with " 
		<< B << " bits precision\n"
	    << "USAGE: " << me << " <encrypted> <table file> [options]\n"
		<< "\nArguments:\n"
		<< " <encrypted>:   encrypted password to crack\n"
		<< " <table file>:  name of file containing the table to use\n"
		<< "\nOptions:\n"
		<< " -h|--help:     print this message\n"
		<< " -v|--verbose:  select verbose mode\n\n";
	exit(0);
}

void initialize(int argc, char* argv[]) {
	me = argv[0];
	if (argc < 3) usage("Missing arguments");
	encrypted = argv[1];
	table_filename = argv[2];
	for (int i=3; i<argc; ++i) {
		std::string arg = argv[i];
		if (arg == "-h" || arg == "--help") usage();
		else if (arg == "-v" || arg == "--verbose") verbose = true;
		else usage("Unrecognized argument: " + arg);
	}
}


int main(int argc, char *argv[]){
	CPU_timer t;
	initialize(argc, argv);
	
	t.tic();
	Brute brute(table_filename);
	brute.decrypt(encrypted);
	t.toc();

	//std::cout << "My blazingly fast algorithm took " 
		//	  << t.elapsed() 
		  //    << " milliseconds to complete\n";

	return 0;
}
