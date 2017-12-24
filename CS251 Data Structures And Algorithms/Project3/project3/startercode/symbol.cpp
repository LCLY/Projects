#include <cstdlib>
#include <fstream>
#include <iostream>
#include <string>
#include <vector>
#include <cmath>
#include <unordered_map>

#include "key.hpp"
#include "symbol.hpp"
#include "timer.hpp"

std::string me;
std::string encrypted;
std::string table_filename;
std::unordered_map<Key, int> symbol_t;
bool verbose = false;
int X;
Symbol::Symbol(const std::string& filename) {
	T.resize(N);
	std::string buffer;
    std::fstream input(filename.c_str(), std::ios::in);
    for (int i = 0; i < N; i++) {
        std::getline(input, buffer);
        T[i].set_string(buffer);
    }
    X=N/2;
    input.close();
	table_construct(T,0,X);
	
}

void Symbol::decrypt(const std::string& encrypted){
	Key encrypted_key(encrypted);
	int first = X;
	int last = N;
	int length = last - first;
	for(int i = 0 ; i < pow(2, length); i ++){
		Key k;
		for(int j = 0 ; j < length; j++){
			if((i>>j)&1){
				k += T[last - 1 - j];
			}
		}
		Key search = encrypted_key - k;
		if(symbol_t.count(search) != 0){
			int temp = 0;
			std::string s;
			for(int j = 0 ; j < N ; j++){
				if(j < (N-X)){
					if((i>>j)&1){
						temp += 1 << (j % 5);
					}
				}
				else{
					if((symbol_t[search] >> (j - (N-X)))& 1){
						temp += 1 <<(j % 5);
					}
				}
				if(j % 5 == 4){
					s = ALPHABET[temp] + s;
					temp = 0;
				}
			}
			std::cout << s << std::endl;
		}	
	}
}

void Symbol::table_construct(const std::vector<Key> & T, int first, int last){
	int diff = last - first;
	for(int i = 0 ;i < pow(2 , diff); i ++){
		Key k;
		for(int j = 0 ; j < diff ; j++){
			if((i >> j) & 1){
				k += T[last - 1 - j];
			}
		}
		symbol_t[k] = i;
	}
}

void usage(const std::string& error_msg="") {
	if (!error_msg.empty()) std::cout << "ERROR: " << error_msg << '\n';
	std::cout << me << ": Symbol table-based cracking of Subset-sum password"
		<< " with " << B << " bits precision\n"
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
	Symbol s(table_filename);
	s.decrypt(encrypted);
	t.toc();

	//std::cout << "My blazingly fast algorithm took "
		//	  << t.elapsed()
		  //    << " milliseconds to complete\n";

	return 0;
}

