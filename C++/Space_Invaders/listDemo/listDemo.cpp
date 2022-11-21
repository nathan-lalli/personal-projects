#include <iostream>
#include <list>		// need this to access the library list class
using namespace std;

//===============================================================
// This file contains code to illustrate common operations on a linked
// list, using the library list<> class. This uses a list of ints, but just like
// a vector, a list can hold any data type (including pointers). 

// The main() function below doesn't do anything useful; 
// it is just calling functions to illustrate
// how to use the list<> class.

// A new thing that we need is the concept of an "iterator".
// Most of the functions below will declare one, like:
// list<int>::iterator it;
// An iterator is an object that wraps a pointer into the 
// list. The library doesn't want to
// let us literally have a pointer into its nodes (because they would have to expose implementation detail).).
// So, they give us an iterator class that simulates a ptr (with overloaded operators)
// that gives us the same functionality as a pointer without the danger of 
// fouling up the internals of their list data structure. You'll see iterators
// used in most of the functions called below.

// The text book briefly discusses the list class in section 17.5, but with limited examples.
// A complete reference for the list class is here: http://www.cplusplus.com/reference/list/list/
// But, this file illustrates all of the operations you could need for Prog II.

// Suggest understanding the code by looking at main first, and then working
// through the code for each function it calls


// list is passed by reference just for efficiency (to avoid passing a copy)
void display(list<int>& nums)
{
	// an iterator is a class that acts like a pointer into the
	// list. *iter gives the value "pointed" at. iter++ advances
	// the pointer to point to the next element in the list
	// begin() returns an iterator to the first element, end() 
	// returns the invalid iterator past the end of the vector (analagous
	// to a null pointer)
	list<int>::iterator iter;
	for (iter = nums.begin(); iter != nums.end(); iter++)
	{
		cout << (*iter) << " ";
	}
	cout << endl;

}

// list is passed by reference because this function will change its contents
// This is just an example of a function that removes stuff from the list.
// You have to be careful when erasing in a loop, because erasing changes the
// list, and therefore ++ won't work after an erase (because you'd be advancing
// an iterator that currently refers to a node that's no longer there...it would
// be a run-time error)
void removeOdds(list<int>& nums)
{
	list<int>::iterator iter;
	for (iter = nums.begin(); iter != nums.end(); /* note no ++ here*/)
	{
		if ((*iter) % 2 != 0)
		{
			iter = nums.erase(iter);
			// the above works because erase returns a new iterator 
			// that points to next thing in list after what we just deleted
		}
		else
			iter++; // only advance using ++ if we didn't erase the node iter is referencing
	}

}

// just to illustrate an easy way to get to the ith element of a list
// [] isn't supported on a list iterator, you have to use the advance()
// function. Internally, advance is traversing the list until it gets
// to the specified node (eg. if index == 2 we're wanting to get an iterator
// that refers to the 3rd node). If you were doing this all the time, then
// you probably wanted a vector, not a list (a vector is way faster at getting to
// the nth thing, because it doesn't have to follow a bunch of links to get to the
// nth thing).
void displayNthValue(list<int>& nums, int index)
{
	list<int>::iterator it;
	it = nums.begin();
	advance(it, index); // advance is a library function that advances iterator
	cout << "value at index " << index << " is " << *it << endl;
}

// the library list class doesn't assume/force things to be in any order
// in the list (they will exist in whatever order you push_back in)
// This function illustrates how we could write a function to do our own
// insert in such a way as to maintain an ordered list
void insertInOrder(list<int>& nums, int value)
{
	list<int>::iterator iter;
	// keep working through the list as long as iterator isn't off the end, 
	// and it refers to a value that's smaller than the one we're inserting
	for (iter = nums.begin(); iter != nums.end() && (*iter) < value; iter++)
	{
		// nothing to do in body, iter++ advances iterator to next element
		// of list. The only point of this loop is to move our iterator
		// to the first node that has a value bigger than what we're inserting
	}
	// at this point, the iterator either points to the right spot to insert the value
	// or it's at the end of the list

	// insert() inserts value in front of the position referenced by iterator
	// Note that this works even if iter == end(), in this case the value
	// is "inserted" at the end of the list (just like doing a push_back)
	nums.insert(iter, value);

}


int main()
{
	// note linked list instead of vector
	list<int> nums;

	// adding stuff to end of a list is just like adding to a vector
	for (int i = 0; i < 10; i++)
		nums.push_back(i);

	cout << "Initial list: " << endl;
	display(nums);

	cout << "4th value: " << endl;
	displayNthValue(nums, 4);

	removeOdds(nums);
	cout << "After odds removed: " << endl;
	display(nums);

	nums.clear(); // erase the contents of the list
	nums.push_back(2);
	nums.push_back(3);
	nums.push_back(5);
	nums.push_back(6);
	nums.push_back(7);

	cout << "Testing with different list: " << endl;
	display(nums);
	removeOdds(nums);
	cout << "After odds removed: " << endl;
	display(nums);

	nums.clear();
	insertInOrder(nums, 20);
	insertInOrder(nums, 15);
	insertInOrder(nums, 17);
	insertInOrder(nums, 80);
	cout << "After inserting series of values in order: " << endl;
	display(nums);

	return 0;
}