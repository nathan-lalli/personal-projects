#pragma once
#include <iostream>
#include <SFML/Graphics.hpp>
#include <list>
#include "Bomb.h"
#include "Ship.h"
#include <ctime>
#include <cstdlib>

using namespace std;
using namespace sf;

class BombList
{
private:
	list<Bomb*> bomblist;
	Bomb bigboomer;
	Ship goodguy;

public:
	BombList();

	void display(RenderWindow& win);

	void dropnewbomb(Texture& texture, Vector2f dropspot);

	void bomboffscreen(bool& bombdropped);

	int droptime(int minimum, int maximum);

	int dropspot(int count);

	void hitmarker(FloatRect ship, bool& hit);
};