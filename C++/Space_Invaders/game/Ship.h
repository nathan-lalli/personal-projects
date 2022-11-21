#pragma once
#include <iostream>
#include <SFML/Graphics.hpp>

using namespace std;
using namespace sf;

class Ship
{
private:
	Sprite ship;
	Texture shiptexture;
public:
	Ship();

	void draw(RenderWindow& win);

	void setposition(RenderWindow& win);

	FloatRect bounds();

	Sprite getsprite();

	void setText();

	void movement();
};