#ifndef _SYMBOL_HPP_
#define _SYMBOL_HPP_

#include <fstream>
#include <iostream>
#include <string>
#include <vector>

#include "key.hpp"

class Symbol {
public:
	Symbol(const std::string&);
	void decrypt(const std::string&);

	std::vector<Key> T;
	void table_construct(const std::vector<Key> &, int, int);
};

#endif
