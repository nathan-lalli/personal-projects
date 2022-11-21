#pragma once
#include <iostream>
#include <SFML/Graphics.hpp>
#include <list>
#include "Missile.h"

using namespace std;
using namespace sf;

class MissileList
{
private:
	list<Missile*> missilelist;
	Missile missile;

public:
	MissileList();

	void display(RenderWindow& win);

	void createmissile(Texture& texture, Vector2f pos);

	void missileoffscreen();

	FloatRect enemyhit(FloatRect enemybounds);
};